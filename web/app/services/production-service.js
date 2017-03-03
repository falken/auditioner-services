
import Ember from 'ember';
import Production from 'web/models/production';
import ListResult from 'web/models/listResult';


export default Ember.Service.extend({
  client: Ember.inject.service('client'),
  search:function(){
    const client = this.get('client');
    let results = ListResult.create({childType:Family});

    let searchUrl = '/auditioner/productions';

    client.getResource(searchUrl,results);

    return results;
  },
  loadById:function(productionId)
  {
    const client = this.get('client');
    const url = '/auditioner/productions/' + productionId;

    const production = Production.create();

    production.set('client',client);
    production.set('path',url);

    client.getResource(url,production);

    return production;
  },
  reload:function(production)
  {
    const client = this.get('client');

    client.getResource(production.get('location'),production);

    return production;
  },
  deleteProduction:function(production)
  {
    const client = this.get('client');

    return client.deleteResource(production);
  },
  createProduction:function()
  {
    const client = this.get('client');
    const production = Production.create();
    production.set('client',client);
    return production;
  }

});
