
import Ember from 'ember';
import Family from 'web/models/family';
import FamilyMember from 'web/models/familyMember';
import ListResult from 'web/models/listResult';


export default Ember.Service.extend({
  client: Ember.inject.service('client'),
  searchFamilies:function(){
    const client = this.get('client');
    let results = ListResult.create({childType:Family});

    let searchUrl = '/auditioner/families';

    client.getResource(searchUrl,results);

    return results;
  },
  loadFamilyById:function(familyId)
  {
    const client = this.get('client');
    const url = '/auditioner/families/' + familyId;

    const family = Family.create();

    family.set('client',client);
    family.set('path',url);

    client.getResource(url,family);

    return family;
  },
  saveFamily:function(family)
  {
    const client = this.get('client');
    if(family.get('location')) {
      return client.putResource(family);
    }
    else {
      return client.postResource("/auditioner/families", family);
    }
  },
  reloadFamily:function(family)
  {
    const client = this.get('client');

    client.getResource(family.get('location'),family);

    return family;
  },
  deleteFamily:function(family)
  {
    const client = this.get('client');

    return client.deleteResource(family);
  },
  createFamily:function()
  {
    const client = this.get('client');
    const family = Family.create();
    family.set('client',client);
    return family;
  },
  loadFamilyMemberById:function(familyId,familyMemberId)
  {
    const client = this.get('client');
    const url = '/auditioner/families/' + familyId + '/family_member/' + familyMemberId;

    const familyMember = FamilyMember.create();

    familyMember.set('client',client);
    familyMember.set('path',url);

    client.getResource(url,familyMember);

    return familyMember;
  },
  loadFamilyMembersByFamilyId: function(familyId) {
    const client = this.get('client');
    let results = ListResult.create({childType:FamilyMember});

    let searchUrl = '/auditioner/families/' + familyId + '/family_member/';

    client.getResource(searchUrl,results);

    return results;
  },
  deleteFamilyMember:function(familyMember)
  {
    const client = this.get('client');

    return client.deleteResource(familyMember);
  },
  createFamilyMember:function()
  {
    const client = this.get('client');
    const familyMember = FamilyMember.create();
    familyMember.set('client',client);
    return familyMember;
  },
  saveFamilyMember:function(familyId,saveFamilyMember)
  {
    const client = this.get('client');

    if (saveFamilyMember.get("location")) {
      return client.putResource(saveFamilyMember);
    }
    else {
      return client.postResource("/auditioner/families/" + familyId + "/family_member",saveFamilyMember);
    }
  },

});
