package com.incture.security;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;

@Path("/callback")
public class DefaultCallbackResource {
	public static String tokenURL = 
			"https://oauthasservices-p1942162623trial.hanatrial.ondemand.com/oauth2/api/v1/token";

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("code") String authCode) {

		final MultivaluedMap<String, String> formData = new MultivaluedStringMap();

		formData.add("grant_type", "authorization_code");
		formData.add("code", authCode);
		formData.add("redirect_uri", "https://hanapocp1942162623trial.hanatrial.ondemand.com/hana_poc_oauth/callback");
		formData.add("client_id", "1846196f-98d1-382d-b47e-7a884e2c5d07");
		formData.add("client_secret", "p1942162623trial-asdf-123-zxcv");

		final Response response = ClientBuilder.newClient()
				.target(tokenURL).request()
				.accept(MediaType.APPLICATION_JSON).post(Entity.form(formData));

		return response;

	}
}
