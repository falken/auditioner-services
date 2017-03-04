
import Ember from 'ember';

export default Ember.Route.extend({
    productionService: Ember.inject.service('production-service'),
    model(param){
        const productionService = this.get('productionService');

        return {
            production: productionService.loadById(param.production_id),
            productionMembers: productionService.loadProductionMembersById(param.production_id)
        };
    },
    setupController: function (controller,model) {
        this._super(controller,model);

        controller.set('isEditing',false);
        controller.set('editingFamilyMember',null);
    }
});
