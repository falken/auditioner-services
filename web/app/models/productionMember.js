import Ember from 'ember';

export default Ember.Object.extend({
	path: null,
	name: null,
	loadFromData: function (client, data) {
		this.set('client', client);

		this.set('id', data.location.substring(data.location.lastIndexOf("/") + 1));
		this.set('location', data.location);

		this.set('requestedRoles', data.requestedRoles);
		this.set('rehearsalConflicts', data.rehearsalConflicts);
		this.set('auditionNumber', data.auditionNumber);
		this.set('familyMemberFirstName', data.familyMemberFirstName);
		this.set('familyMemberLastName', data.familyMemberLastName);
	},

	toJson: function() {
		return JSON.stringify({
			familyMemberId: this.get('familyMemberId'),
			requestedRoles: this.get('requestedRoles'),
			rehearsalConflicts: this.get('rehearsalConflicts')
		});
	}
});

