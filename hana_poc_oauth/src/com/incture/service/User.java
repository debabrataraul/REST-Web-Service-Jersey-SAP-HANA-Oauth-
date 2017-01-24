package com.incture.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class User {
	@GET
	@Path("/login/{userName}/{passWord}")
	@Produces(MediaType.APPLICATION_JSON)
	public String validateUser(@PathParam("userName") String userName,
			@PathParam("passWord") String passWord){
		
		Data d=Data.getInstance();
		System.out.println("userName : "+userName+ " passWord : "+passWord);
		String response=null;
		
		for(int i=0;i<d.db.length;i++){
			//System.out.println(d.db[i][0] +"  "+d.db[i][1]+" "+d.db[i][2]+"  "+d.db[i][3]);
			if(d.db[i][0].equals(userName) && d.db[i][1].equals(passWord)){
				response=d.success;
				response=response.replace("FName", d.db[i][2]);
				response=response.replace("LName", d.db[i][3]);
				response=response.replace("MNo", d.db[i][4]);
				break;
			}
		}
		if (response==null) response=d.failure;
		//System.out.println(response);
		return response;
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public String saysHello(){
		String response=
					"<html> "
				+"<title>  Debabrata Raul's Web Site </title>"
				+"Hello there \n How are you"
					+ " </html>";
		
		
		return response;
	}

}
