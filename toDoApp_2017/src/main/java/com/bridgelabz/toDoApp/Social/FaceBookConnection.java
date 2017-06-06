package com.bridgelabz.toDoApp.Social;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.bridgelabz.toDoApp.model.FaceBookAccessToken;
import com.bridgelabz.toDoApp.model.FaceBookProfile;

public class FaceBookConnection {

	public static final String App_Id = "458817991138556";
	public static final String Secret_Id = "99f17048302c0eef0ed9ca2b99c2c73c";
	public static final String Redirect_URI = "http://localhost:8080/toDoApp_2017/fbConnection";
	
   
	
	static String accessToken = "";
	
	public String getFbAuthURL(String unid) {
		
		String fbLoginURL = "";
		
		try{
			
			fbLoginURL = "http://www.facebook.com/dialog/oauth?"+"client_id="+App_Id+"&redirect_uri="+URLEncoder.encode(Redirect_URI, "UTF-8")+"&state="+unid+"&response_type=code"+"&scope=public_profile,email";
			
		}
		catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		return fbLoginURL;
	}
	
	
	
	
	public String getAccessToken (String code) throws IOException {
		
		String AccessToken_URL = "https://graph.facebook.com/v2.9/oauth/access_token?"+"client_id="+App_Id+"&redirect_uri="+URLEncoder.encode(Redirect_URI, "UTF-8")+"&client_secret="+Secret_Id+ "&code="+code;
		
		ResteasyClient restCall = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = restCall.target(AccessToken_URL);
		
		Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
		
		FaceBookAccessToken accessToken = response.readEntity(FaceBookAccessToken.class);
		
		restCall.close();
		
		return accessToken.getAccess_token();
		
	}
	
	
	public FaceBookProfile getUserProfile (String accessToken) {
		
		String getUserURL = "https://graph.facebook.com/v2.9/me?access_token="+accessToken+"&fields=id,name,email";
		
		ResteasyClient restCall = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = restCall.target(getUserURL);
		
		Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
		
		FaceBookProfile profile = response.readEntity(FaceBookProfile.class);
		
		restCall.close();
		
		return profile;
		
	}
	
	
	
/*	public String getAccessToken(String code) {
		
		if( "".equals(accessToken) ) {
			
			URL fbGraphURL = null;
			
			try {
				fbGraphURL = new URL( getFbGraphURL(code) );
			}
			catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			
			URLConnection fbConnection ;
			StringBuffer buffer = new StringBuffer();
			
			try {
				fbConnection = fbGraphURL.openConnection();
				
				BufferedReader reader = new BufferedReader( new InputStreamReader(fbConnection.getInputStream() ));
				
				String inputLine ;
				
				
				while ( (inputLine = reader.readLine()) != null ) {
					
					buffer.append(inputLine + "\n");
				}
				reader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			accessToken = buffer.toString();
			if( accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid:" + accessToken);
			}
		}
		
		return accessToken;
		
	}
	
	
	
	public String getFbGraphURL(String code) {
		
		String fbgraphURL = "";
		try{
			
			fbgraphURL = "https://graph.facebook.com/oauth/access_token?" + "client_id" + App_Id +
					"&redirect_uri="+URLEncoder.encode(Redirect_URI, "UTF-8") +
					"&client_secret=" + Secret_Id + "&code=" + "code";
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return fbgraphURL;
	}*/
	
	
}
