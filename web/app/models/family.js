import Ember from 'ember';

export default Ember.Object.extend({
  path: null,
  name: null,
  email: null,
  phone: null,
  address01: null,
  address02: null,
  city: null,
  state: null,
  zipCode: null,
    loadFromData: function (client, data) {
    this.set('client', client);

    this.set('id', data.location.substring(data.location.lastIndexOf("/") + 1));
    this.set('location', data.location);
    this.set('name', data.name);
    this.set('contactEmail', data.email);
    this.set('contactPhone', data.phone);
    this.set('mailingAddressLine1', data.address01);
    this.set('mailingAddressLine2', data.address02);
    this.set('mailingAddressCity', data.city);
    this.set('mailingAddressState', data.state);
    this.set('mailingAddressZip', data.zipCode);


  },
  toJson: function () {
    return JSON.stringify({
      name: this.get('name'),
      email: this.get('contactEmail'),
      phone: this.get('contactPhone'),
      address01: this.get('mailingAddressLine1'),
      address02: this.get('mailingAddressLine2'),
      city: this.get('mailingAddressCity'),
      state: this.get('mailingAddressState'),
      zipCode: this.get('mailingAddressZip'),
    });
  }
});
