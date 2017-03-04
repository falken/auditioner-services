
import Ember from 'ember';

export default Ember.Route.extend({
  familyService: Ember.inject.service('family-service'),
  productionService: Ember.inject.service('production-service'),
  model(){
    const familyService = this.get('familyService');
    const family = familyService.createFamily();
    family.set('preferredContactMethod','Email');
    return {
      family:family,
    };
  },
  setupController: function (controller,model) {
    this._super(controller,model);
    const productionService = this.get('productionService');
    controller.set('newFamilyMembers',Ember.A());
    controller.addFamilyMemberToMemberList();
    controller.set('productions',productionService.searchProductions());
    controller.set('canDeleteFamilyMamber',false);
  }

});
