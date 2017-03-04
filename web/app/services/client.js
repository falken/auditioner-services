
import Ember from 'ember';
import config from '../config/environment';

var client = Ember.Service.extend({
  apiUrl:null,
  buildUrl:function(urlPattern)
  {
    var apiUrl = this.get('apiUrl');

    if(Ember.isEmpty(apiUrl))
    {
      apiUrl = config.APP.API_URL;
      this.set('apiUrl',apiUrl);
    }
    return apiUrl + urlPattern;
  },
  getResource: function(urlPattern,result){
    var client = this;

    return new Ember.RSVP.Promise(function (resolve, reject) {

      result.set('client',client);

      if(!urlPattern)
      {
        reject();
        return;
      }

      Ember.$.ajax({
        url:client.buildUrl(urlPattern),
        async:true,
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        dataType:"json"
      }).done(function(data)
      {
        result.set('location',urlPattern);
        result.loadFromData(client,data);
        resolve(result);
      }).fail(function(){
        reject();
      });
    });
  },
  putResource: function(resource){
    var client = this;

    return new Ember.RSVP.Promise(function (resolve, reject) {

      Ember.$.ajax({
        url:client.buildUrl(resource.get('location')),
        async:true,
        type: 'PUT',
        data: resource.toJson(),
        contentType: "application/json; charset=utf-8"
      }).done(function()
      {
        resolve();
      }).fail(function(){
        reject();
      });
    });
  },

  deleteResource: function(resource){
    var client = this;

    return new Ember.RSVP.Promise(function (resolve, reject) {

      Ember.$.ajax({
        url:client.buildUrl(resource.get('location')),
        async:true,
        type: 'DELETE',
        contentType: "application/json; charset=utf-8"
      }).done(function()
      {
        resolve();
      }).fail(function(){
        reject();
      });
    });
  },
  postResource: function(urlPattern,resource){
    var client = this;

    return new Ember.RSVP.Promise(function (resolve, reject) {

      Ember.$.ajax({
        url:client.buildUrl(urlPattern),
        async:true,
        type: 'POST',
        data: resource.toJson(),
        contentType: "application/json; charset=utf-8",
        dataType:"json"
      }).done(function(data)
      {
        resource.set('location',data.url);
        resource.set('id',data.url.substring(data.url.lastIndexOf("/") + 1));
        resolve();
      }).fail(function(){
        reject();
      });
    });
  }
});

export default client;
