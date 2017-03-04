
import Ember from 'ember';

export default Ember.Route.extend({
  familyService: Ember.inject.service('family-service'),
  productionService: Ember.inject.service('production-service'),
  model(param){
    const familyService = this.get('familyService');
    const productionService = this.get('productionService');
		// console.log(familyService.loadFamilyMemberById(param.family_id,param.family_member_id));
		// familyService.loadFamilyMemberById(param.family_id,param.family_member_id)
    return {
      family: familyService.loadFamilyById(param.family_id),
			familyMember:familyService.loadFamilyMemberById(param.family_id,param.family_member_id),
      productions: productionService.searchProductions()
    };
  }
});
