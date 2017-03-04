
import Ember from 'ember';

export default Ember.Route.extend({
  familyService: Ember.inject.service('family-service'),
  model(){
    const familyService = this.get('familyService');
    return familyService.searchFamilies();
  },
  setupController: function (controller,model) {
    this._super(controller,model);

    controller.set('isEditing',false);
    controller.set('editingFamily',null);
  }
});
