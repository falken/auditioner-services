
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

    return client.putResource(family);
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
  saveFamilyMember:function(saveFamilyMember)
  {
    const client = this.get('client');

    return client.putResource(saveFamilyMember);
  },

});
