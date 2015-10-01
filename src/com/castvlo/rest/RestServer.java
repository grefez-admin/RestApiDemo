package com.castvlo.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is a showcase of Jersey RESTFul Web Services Library
 * @author gofa
 *
 */
@Path("/")
public class RestServer {

	private String DEFAULT_MSG = "Server up and running";

	@POST
	@Path("/service")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crunchifyREST(InputStream incomingData) {
		StringBuilder crunchifyBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				crunchifyBuilder.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data Received By Admin (Cambio 8): " + crunchifyBuilder.toString());
		System.out.println("CAmbio 6");
		System.out.println("Cambio 7");
		System.out.println("Cambio 9");

		// return HTTP response 200 in case of success
		return Response.status(200).entity(crunchifyBuilder.toString()).build();
	}
	
	
	/**
	 * GET No params
	 * @return
	 */
	@GET
	@Path("/getNoParam")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getNoParam() {
		// return HTTP response 200 in case of success
		return Response.status(200).entity(DEFAULT_MSG).build();
	}

	/**
	 * GET with params in the request
	 * @param temperatura
	 * @return
	 */
	@GET
	@Path("/getReqParam")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getReqParam(@QueryParam("t") Integer temperatura) {
		String result = DEFAULT_MSG;
		if (temperatura!= null && temperatura >0) {
			try {
				result = new JSONObject().put("temperatura", temperatura).toString();
			} catch (JSONException e) {
				result = "{temperatura:error}";
			}
		}

		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}

	/**
	 * GET with params in the URL
	 * @param temperatura
	 * @return
	 */
	@GET
	@Path("/getPathParam/{t}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getPathParam(@PathParam("t") Integer temperatura) {
		String result = DEFAULT_MSG;
		if (temperatura!= null && temperatura >0) {
			try {
				result = new JSONObject().put("temperatura", temperatura).toString();
			} catch (JSONException e) {
				result = "{temperatura:error}";
			}
		}

		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}


}
