import Ember from 'ember';

export default Ember.Controller.extend({
	productionService: Ember.inject.service('production-service'),
	actions:{
		addProduction: function() {
			const productionService = this.get('productionService');
			this.set('isEditing',true);
			this.set('isAdd',true);
			this.set('editingFamilyMember',productionService.createProduction());
		},
		editProduction: function(production) {
			this.set('isEditing',true);
			this.set('isAdd',false);
			this.set('editingProduction',production);
		},
		deleteProduction: function(production) {

		},
		cancelEditingProduction: function() {

		},
		saveEditingProduction: function() {

		}
	}
});
