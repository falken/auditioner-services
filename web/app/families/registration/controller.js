import Ember from 'ember';

export default Ember.Controller.extend({
	familyService: Ember.inject.service('family-service'),
	productionService: Ember.inject.service('production-service'),
	actions:{

		registerMembers: function() {

			const productionService = this.get("productionService");

			const productionId = this.get("selectedProductionId");

			const members = this.get("model.members");
			console.log("members",members);

			var pms = [];

			for(var i =0; i< members.length; i++) {
				var member = members[i];

				var pm = productionService.createProductionMember();
				pm.set("requestedRoles",member.requestedRoles);
				pm.set("rehearsalConflicts",member.rehearsalConflicts);
				pm.set("familyMemberId",member.id);

				console.log(member);
				console.log(pm);

				pms.push(pm);
			}


			productionService.registerForProduction(productionId,pms);

		},
		selectProduction: function(param) {
			this.set("selectedProductionId",param);
		}
	}
});
