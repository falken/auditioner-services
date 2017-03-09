import Ember from 'ember';

export default Ember.Object.extend({
  path: null,
  name: null,
  loadFromData: function (client, data) {
    this.set('client', client);

    this.set('id', data.location.substring(data.location.lastIndexOf("/") + 1));
    this.set('location', data.location);
    this.set('name', data.name);
    this.set('email', data.email);

    let phone = data.phone;
    if (phone && phone.length === 10) {
      phone = "(" + phone.substr(0, 3) + ") " + phone.substr(3, 3) + "-" + phone.substr(6, 4);
    }

    this.set('phone', phone);
    this.set('address01', data.address01);
    this.set('address02', data.address02);
    this.set('city', data.city);
    this.set('state', data.state);
    this.set('zipCode', data.zipCode);
    this.set('preferredContactMethod', data.preferredContactMethod);


  },
  toJson: function () {

    let phone = this.get('phone');
    console.log(phone);
    if (phone) {
      phone = phone.replace(/[^0-9.]/g, "").substr(0, 10);
    }
    console.log(phone);

    return JSON.stringify({
      name: this.get('name'),
      email: this.get('email'),
      phone: phone,
      address01: this.get('address01'),
      address02: this.get('address02'),
      city: this.get('city'),
      state: this.get('state'),
      zipCode: this.get('zipCode'),
      preferredContactMethod: this.get('preferredContactMethod'),

    });
  }
});
