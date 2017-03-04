
import Ember from 'ember';

export default Ember.Route.extend({
  familyService: Ember.inject.service('family-service'),
  productionService: Ember.inject.service('production-service'),
  model(param){
    const familyService = this.get('familyService');
    const productionService = this.get('productionService');


    console.log(familyService.loadFamilyById(param.family_id));

    return {
      family: familyService.loadFamilyById(param.family_id),
      productions: productionService.searchProductions(),
      familyMembers: familyService.loadFamilyMembersByFamilyId(param.family_id)
    };
  },
  setupController: function (controller,model) {
    this._super(controller,model);

    controller.set('isEditing',false);
    controller.set('editingFamilyMember',null);
  }
});
