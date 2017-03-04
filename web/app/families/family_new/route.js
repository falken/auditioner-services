
import Ember from 'ember';

export default Ember.Route.extend({
  familyService: Ember.inject.service('family-service'),
  productionService: Ember.inject.service('production-service'),
  model(){
    const familyService = this.get('familyService');
    const model =  {
      family:familyService.createFamily(),
    };
    return model;
  },
  setupController: function (controller,model) {
    this._super(controller,model);
    const productionService = this.get('productionService');
    controller.set('newFamilyMembers',Ember.A());
    controller.addFamilyMember();
    controller.set('productions',productionService.searchProductions());
  }

});
