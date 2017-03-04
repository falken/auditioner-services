import Ember from "ember";

export default Ember.Route.extend({
	productionService: Ember.inject.service('production-service'),
	model(){
		const productionService = this.get('productionService');
		return productionService.searchProductions();
	}
});
