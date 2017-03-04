import Ember from 'ember';

export default Ember.Object.extend({
  parent:null,
  init: function() {
    this._super.apply(this, arguments);

    if (!this.get('results')) {
      this.set('results', Ember.A());
    }
  },
  loadFromData:function(client,data){
    this.set('client',client);


    let resultObject = this;
    let resultList = Ember.A();
    let childType = this.get('childType');

    data.forEach(function(item){
      let child = childType.create();

      child.set('parent',resultObject.get('parent'));
      child.loadFromData(client,item);

      resultList.addObject(child);
    });
    this.set('results',resultList);
    this.set('hasItems',data.length > 0);
  },
  reload:function(){
    let client = this.get('client');

    console.log(this);
    client.getResource(this.get('location'),this);
  }
});
