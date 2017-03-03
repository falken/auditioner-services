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

      familyService.saveFamilyMember(editingFamilyMember)
        .then(function(){
          this.set('isEditing',false);
          this.set('editingFamilyMember',null);
        });
    }
  }

});
