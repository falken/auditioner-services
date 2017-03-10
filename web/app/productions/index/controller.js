import Ember from 'ember';
import moment from 'moment';

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
      this.set('isEditing',false);
      this.set('editingProduction',null);
    },
    updateAuditionDate: function(newDate) {
      const editingProduction= this.get('editingProduction');

      const formattedDate = moment(newDate).utc().format('YYYY-MM-DD');

      editingProduction.set('auditionDate',formattedDate);
		},
    updateAgeCutoffDate: function(newDate) {
      const editingProduction= this.get('editingProduction');

      const formattedDate = moment(newDate).utc().format('YYYY-MM-DD');

      editingProduction.set('ageCutoffDate',formattedDate);
    },
		saveEditingProduction: function() {
			const productionService = this.get('productionService');
			const editingProduction= this.get('editingProduction');
			const list = this.get('model');

			var that = this;

			productionService.saveProduction(editingProduction)
				.then(function() {
					that.set('isEditing',false);
					that.set('editingProduction',null);
					list.reload();
				});
		}
	}
});
