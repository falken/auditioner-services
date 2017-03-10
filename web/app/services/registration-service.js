import Ember from 'ember';
import ProductionMember from 'web/models/productionMember';


export default Ember.Service.extend({
	client: Ember.inject.service('client'),

	createProductionMember: function() {
		const client = this.get('client');
		const productionMember = ProductionMember.create();
		productionMember.set('client',client);
		return productionMember;
	},

	registerForProduction: function(productionId,productionMembers) {
		console.log("registering");
		const client = this.get('client');
		const url = '/auditioner/productions/' + productionId + '/production-members';

		return client.postResource(url,productionMembers[0]);
	}

});
