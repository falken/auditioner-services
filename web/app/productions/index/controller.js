import Ember from 'ember';

export default Ember.Controller.extend({
	productionService: Ember.inject.service('production-service'),
	actions:{
		addProduction: function() {
			const productionService = this.get('productionService');
			this.set('isEditing',true);
			this.set('isAdd',true);
			this.set('editingProduction',productionService.createProduction());
		},
		editProduction: function(production) {
			this.set('isEditing',true);
			this.set('isAdd',false);
			this.set('editingProduction',production);
		},
		deleteProduction: function(production) {
			const productionService = this.get('productionService');
			const list = this.get('model');
			productionService.deleteProduction(production).then(function(){
				list.reload();
			});
		},
		cancelEditingProduction: function() {

		},
		saveEditingProduction: function() {
			const productionService = this.get('productionService');
			const editingProduction= this.get('editingProduction');

			console.log(editingProduction);
			productionService.saveProduction(editingProduction)
				.then(function() {
					this.set('isEditing',false);
					this.set('editingProduction',null);

				});
		}
	}
});
