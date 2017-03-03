
import Ember from 'ember';

export default Ember.Route.extend({
	model(param){
	return {
		productions:Ember.A([
			Ember.Object.create({
				"name":"Nutcracker",
				"auditionDate":"09/01/2017"
			}),
			Ember.Object.create({
				"name":"Dracula",
				"auditionDate":"03/18/2018"
			})
		])
	};
	}
})
