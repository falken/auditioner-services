import Ember from 'ember';


export default Ember.Controller.extend({
  familyService: Ember.inject.service('family-service'),
  actions:{
    choosePreferredContactMethod(choice) {
      const editingFamily = this.get('editingFamily');
      editingFamily.set('preferredContactMethod', choice);
    },
    addFamily:function(){
      const familyService = this.get('familyService');
      const editingFamily = familyService.createFamily();
      editingFamily.set('preferredContactMethod','Email');
      this.set('isEditing',true);
      this.set('isAdd',true);

      this.set('editingFamily',editingFamily);
    },
    saveEditingFamily:function(){

      const familyService = this.get('familyService');
      const editingFamily = this.get('editingFamily');
      const list = this.get('model');
      const controller = this;

      familyService.saveFamily(editingFamily)
        .then(function(){
          controller.set('isEditing',false);
          controller.set('editingFamily',null);
          list.reload();
        });
    },

    editFamily: function(family) {
      this.set('isEditing',true);
      this.set('isAdd',false);
      if(!family.get('preferredContactMethod')){
        family.set('preferredContactMethod','Email');
      }
      this.set('editingFamily',family);
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
