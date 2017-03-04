
import Ember from 'ember';

export default Ember.Route.extend({
  familyService: Ember.inject.service('family-service'),
  model(param){
    const familyService = this.get('familyService');
    return {
      family: familyService.loadFamilyById(param.family_id),
      familyMembers:Ember.A([
        Ember.Object.create({
          "id": 1,
          "firstName":"Tammy",
          "lastName":"Smith Jr",
          "age":14
        }),
        Ember.Object.create({
          "id": 2,
          "firstName":"Lisa",
          "lastName":"Smith",
          "age":12
        })
      ])
    };
  }
});
