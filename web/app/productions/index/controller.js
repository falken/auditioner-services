import Ember from 'ember';

export default Ember.Controller.extend({
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
      const productionService = this.get('productionService');
      const list = this.get('model');
      productionService.deleteProduction(production)
        .then(function(){
          list.reload();
        });

		},
		cancelEditingProduction: function() {

		},
		saveEditingProduction: function() {

		}
	}
});
