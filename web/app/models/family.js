import Ember from 'ember';

export default Ember.Object.extend({
  path: null,
  name: null,
  /*
  email: null,
  phone: null,
  address01: null,
  address02: null,
  city: null,
  state: null,
  zipCode: null,
  preferredContactMethod: null,
  */
    loadFromData: function (client, data) {
    this.set('client', client);

    this.set('id', data.location.substring(data.location.lastIndexOf("/") + 1));
    this.set('location', data.location);
    this.set('name', data.name);
    this.set('email', data.email);
    this.set('phone', data.phone);
    this.set('address01', data.address01);
    this.set('address02', data.address02);
    this.set('city', data.city);
    this.set('state', data.state);
    this.set('zipCode', data.zipCode);
    this.set('preferredContactMethod', data.preferredContactMethod);


  },
  toJson: function () {
    return JSON.stringify({
      name: this.get('name'),
      email: this.get('email'),
      phone: this.get('phone'),
      address01: this.get('address01'),
      address02: this.get('address02'),
      city: this.get('city'),
      state: this.get('state'),
      zipCode: this.get('zipCode'),
      preferredContactMethod: this.get('preferredContactMethod'),

    });
  }
});
