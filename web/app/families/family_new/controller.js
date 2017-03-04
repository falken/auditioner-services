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
      const familyService = this.get('familyService');
      const familyMemberList = this.get('newFamilyMembers');

      familyMemberList.addObject({
        familyMember:familyService.createFamilyMember(),
        memberRegistration:Ember.Object.create()
      });
    },
    deleteFamilyMember:function(familyMember){
      const familyMemberList = this.get('newFamilyMembers');

      familyMemberList.removeObject(familyMember);
    },
    cancelFamilyMember:function(){
      const familyService = this.get('familyService');

      this.set('newFamilyMember',familyService.createFamilyMember());
    }
  }

});
