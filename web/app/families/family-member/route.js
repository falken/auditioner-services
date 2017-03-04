import Ember from 'ember';

export default Ember.Route.extend({
	familyService: Ember.inject.service('family-service'),
	productionService: Ember.inject.service('production-service'),

	model(param){
		console.log(param);

		const familyService = this.get('familyService');
		const productionService = this.get('productionService');

		return {
			productions: productionService.searchProductions()
		};
	}
});
