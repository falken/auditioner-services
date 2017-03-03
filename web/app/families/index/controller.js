import Ember from 'ember';



export default Ember.Controller.extend({
  familyService: Ember.inject.service('family-service'),
  actions:{
    deleteFamily:function(family){
      const familyService = this.get('familyService');
      const model = this.get('model');


      familyService.deleteFamily(family)
        .then(function(){
          model.reload();
        });
    },
    saveEditingFamily: function(family) {

    },
    cancelEditingFamily: function() {

    }
  }

});
