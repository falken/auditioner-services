import Ember from 'ember';


export default Ember.Controller.extend({
  familyService: Ember.inject.service('family-service'),
  actions:{
    choosePreferredContactMethod:function(method){
      const family = this.get('model.family');
      family.set('preferredContactMethod',method);
    },
    selectProduction:function(production){
      this.set('selectedProduction',production);
    },
    addFamilyMember:function(){
      this.addFamilyMemberToMemberList();
      this.set('canDeleteFamilyMamber',true);
    },
    deleteFamilyMember:function(familyMember){
      const familyMemberList = this.get('newFamilyMembers');

      familyMemberList.removeObject(familyMember);

      if(familyMemberList.length < 2){
        this.set('canDeleteFamilyMamber',false);
      }
    },
    registerFamily:function(){

    }
  },
  addFamilyMemberToMemberList:function(){
    const familyService = this.get('familyService');
    const familyMemberList = this.get('newFamilyMembers');

    familyMemberList.addObject({
      familyMember:familyService.createFamilyMember(),
      memberRegistration:Ember.Object.create()
    });
  }

});
