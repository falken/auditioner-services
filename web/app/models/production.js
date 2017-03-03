import Ember from 'ember';

export default Ember.Object.extend({
	path: null,
	name: null,
	auditionDate: null,
	loadFromData: function (client, data) {
		this.set('client', client);

		this.set('id', data.location.substring(data.location.lastIndexOf("/") + 1));
		this.set('location', data.location);
		this.set('name', data.name);
		this.set('auditionDate', data.name);
	}
});
