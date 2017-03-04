import Ember from 'ember';



export default Ember.Controller.extend({
  familyService: Ember.inject.service('family-service'),
  actions:{
    editFamilyMember:function(familyMember) {
      this.set('isEditing',true);
      this.set('isAdd',false);
      this.set('editingFamilyMember',familyMember);
    },
    addFamilyMember:function(){
      const familyService = this.get('familyService');
      this.set('isEditing',true);
      this.set('isAdd',true);
      this.set('editingFamilyMember',familyService.createFamilyMember());
    },
    cancelEditingFamilyMember:function(){
      // const isAdd = this.get('isAdd');
      // const editingFamilyMember = this.get('editingFamilyMember');
      // if(!isAdd)
      // {
      //   const familyService = this.get('familyService');
      //   //familyService.reload(editingFamilyMember);
      // }
      this.set('isEditing',false);
      this.set('editingFamilyMember',null);
    },
    saveEditingFamilyMember:function(){
      const familyService = this.get('familyService');
      const editingFamilyMember = this.get('editingFamilyMember');
      const list = this.get('model');
      const familyId = this.get('model.family.id');

      const controller = this;

      familyService.saveFamilyMember(familyId,editingFamilyMember)
        .then(function(){
          controller.set('isEditing',false);
          controller.set('editingFamilyMember',null);
          list.familyMembers.reload();
        });
    },
    deleteFamilyMember: function(familyMember) {
      const familyService = this.get('familyService');
      const model = this.get('model');

      familyService.deleteFamilyMember(familyMember)
          .then(function(){
            model.familyMembers.reload();
          });
    },
    registerFamilyMembers: function() {
      const selectedProductionId = this.get("selectedProductionId");
      console.log(this.model.familyMembers);
      console.log(selectedProductionId);
    },
    selectProduction: function(param) {
      this.set("selectedProductionId",param);
    }

  }

});
