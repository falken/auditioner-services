package org.auditioner.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.SecureRandom;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.dropwizard.testing.junit.ResourceTestRule;

public abstract class TestResourceBase {



    protected static ResourceTestRule wrapResource(Object resource)
    {
    	return ResourceTestRule
	        .builder()
            .addResource(resource)
            .build();
    }
    

    private static SecureRandom random = new SecureRandom();
    private ResourceTestRule resources;
    public void setUp(ResourceTestRule resources){
    	this.resources = resources;
    }


    protected String getResponseBody(Response response){
    	try {
			return IOUtils.toString((ByteArrayInputStream) response.getEntity(), "UTF-8");
		} catch (IOException e) {
			return null;
		}
    }
    
    protected Response simpleGet(String uri) {
        return resources.client().target(uri)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer Dummy")
                .get();
    }
    
    protected <TM> TM simpleGet(String uri,Class<TM> returnType) {
        return resources.client().target(uri)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer Dummy")
                .get(returnType);
    }
    
    protected Response simplePost(String uri,Object entity) {
        return resources.client().target(uri)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer Dummy")
                .post(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));

    }
    
    protected Response simpleDelete(String uri) {
        return resources.client().target(uri)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer Dummy")
                .delete();

    }
    
    protected Response simplePut(String uri,Object entity) {
        return resources.client().target(uri)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer Dummy")
                .put(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));

    }

    protected <TM> TM objectFromResponseEntity(Response response,Class<TM> valueType){

        String body = getResponseBody(response);
    	try {
			return resources.getObjectMapper().readValue(body,valueType);
		} catch (IOException e) {

			throw new RuntimeException("Invalid Body:\n" + body ,e);
		}
    }

    protected String asJsonString(Object object){
    	try {
			return resources.getObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
    }
    
}
