import Ember from 'ember';


export default Ember.Controller.extend({
  familyService: Ember.inject.service('family-service'),
  actions:{
    addFamily:function(){
      const familyService = this.get('familyService');

      this.set('isEditing',true);
      this.set('isAdd',true);
      this.set('editingFamily',familyService.createFamily());
    },
    saveEditingFamily:function(){

      const familyService = this.get('familyService');
      const editingFamily = this.get('editingFamily');
      const list = this.get('model');
      var controller = this;

      familyService.saveFamily(editingFamily)
        .then(function(){
          controller.set('isEditing',false);
          controller.set('editingFamily',null);
          list.reload();
        });
    },
    cancelEditingFamily:function(){

      this.set('isEditing',false);
      this.set('editingFamily',null);
    },
    deleteFamily:function(family){
      const familyService = this.get('familyService');
      const model = this.get('model');


      familyService.deleteFamily(family)
        .then(function(){
          model.reload();
        });
    }
  }

});
