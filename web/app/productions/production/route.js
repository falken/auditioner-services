import Ember from 'ember';

export default Ember.Route.extend({
	productionService: Ember.inject.service('production-service'),
	model(param){
		const productionService = this.get('productionService');

		return {
			production: productionService.loadById(param.production_id),

			// TODO:  This has to be replaced with actual code, by adding a method to production-service that allows us the REST endpoint to get that data.

			productionMembers:Ember.A([
				Ember.Object.create({
					"id": 1,
					"firstName":"Tammy",
					"lastName":"Smith Jr",
					"age":14
				}),
				Ember.Object.create({
					"id": 2,
					"firstName":"Lisa",
					"lastName":"Smith",
					"age":12
				})
			])
		};
	}
});
