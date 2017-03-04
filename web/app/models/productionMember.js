import Ember from 'ember';

export default Ember.Object.extend({
    path:null,
    family_member_id: null,
    production_id: null,
    requestedRoles:null,
    familyMemberLastName: null,
    familyMemberFirstName: null,
    rehearsaConflicts: null,
    auditionNumber: null,
    loadFromData: function (client, data) {

        console.log(data);
        this.set('client', client);

        this.set('id', data.location.substring(data.location.lastIndexof("/") + 1));
        this.set('familyMemberId', data.family_member_id);
        this.set('productionId', data.production_id);
        this.set('familyMemberFirstName', data.familyMemberFirstName);
        this.set('familyMemberLastName', data.familyMemberLastName);
        this.set('rehearsalConflicts', data.rehearsalConflicts);
        this.set('auditionNumber', data.auditionNumber);
        this.set('requestedRoles', requestedRoles);

        this.set('location', data.location);
    },
    toJson: function() {
        return JSON.stringify({
            id: this.get('productionMemberId'),
            family_member_id: this.get('familyMemberId'),
            productionId: this.get('productionId'),
            requestedRoles: this.get('requestedRoles'),
            rehearsalConflicts: this.get('rehearsalConflicts'),
        })
    }
})
