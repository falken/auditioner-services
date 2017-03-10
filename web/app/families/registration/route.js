import Ember from 'ember';

export default Ember.Route.extend({
	familyService: Ember.inject.service('family-service'),
	productionService: Ember.inject.service('production-service'),

	model(param){
		console.log(param);

		const familyService = this.get('familyService');
		const productionService = this.get('productionService');


		const members = Ember.A([]);

    const memberIds = param.family_member_ids.split("-");

		console.log(memberIds);

		for(let i = 0; i < memberIds.length; i++) {
      const familyMemberId = memberIds[i];
      const member = familyService.loadFamilyMemberById(param.family_id,familyMemberId);

			members.push(member);
		}

		// refactor this into list processing
		return {
			familyId: param.family_id,
			productions: productionService.searchProductions(),
			members: members
		};
	}
});
