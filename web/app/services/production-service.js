import Ember from "ember";
import Production from "web/models/production";
import ProductionMember from 'web/models/productionMember';
import ListResult from "web/models/listResult";


export default Ember.Service.extend({
  client: Ember.inject.service('client'),
  searchProductions:function(){
    const client = this.get('client');
    let results = ListResult.create({childType:Production});

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
  loadProductionMembersById:function(productionId)
  {
      const client = this.get('client');
      let results = ListResult.create({childType:ProductionMember});
      const url = '/auditioner/productions/' + productionId +'/production-members/';

      client.getResource(url,results);

      return results;
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
  },
  saveProduction:function(production) {
    const client = this.get('client');

    if (production.get('location')) {
      return client.putResource(production);
    } else {
      return client.postResource('/auditioner/productions',production);
    }

  },

  createProductionMember: function() {
    const client = this.get('client');
    const productionMember = ProductionMember.create();
    productionMember.set('client',client);
    return productionMember;
  },
  registerForProduction: function(productionId,productionMembers) {
    console.log("registering");
    const client = this.get('client');
    const url = '/auditioner/productions/' + productionId + '/production-members';


    for(var i=0; i< productionMembers.length; i++) {
      var productionMember = productionMembers[i];
      client.postResource(url,productionMember);
    }


  }

});
