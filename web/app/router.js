import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  this.route('families',function(){
    this.route('family_new',{path:'/new'});
    this.route('family',{path:'/:family_id'});
    this.route('familyMember',{path:'/:family_id/family-members/:family_member_id'});
    this.route('registration',{path:'/:family_id/registration/:production_id/:family_member_ids'});
  });
  this.route('productions', function() {
    this.route('production_new',{path:'/new'});
    this.route('production',{path:'/:production_id'});
  });
});

export default Router;
