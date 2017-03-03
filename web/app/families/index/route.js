
import Ember from 'ember';

export default Ember.Route.extend({
  familyService: Ember.inject.service('family-service'),
  model(){
    const familyService = this.get('familyService');
    return familyService.searchFamilies();
  }
});
