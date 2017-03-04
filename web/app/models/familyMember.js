import Ember from 'ember';

export default Ember.Object.extend({
  path: null,
  name: null,
  loadFromData: function (client, data) {
    this.set('client', client);

    this.set('id', data.location.substring(data.location.lastIndexOf("/") + 1));
    this.set('location', data.location);
    this.set('firstName', data.firstName);
    this.set('lastName', data.lastName);
    this.set('age', data.age);
    this.set('height', data.height);
    this.set('weight', data.weight);
    this.set('pastRoles', data.pastRoles);

  },

  toJson: function() {
    return JSON.stringify({
      firstName: this.get('firstName'),
      lastName: this.get('lastName'),
      weight: this.get('weight'),
      height: this.get('height'),
      pastRoles: this.get('pastRoles'),
      age: this.get('age')
    });
  }
});

