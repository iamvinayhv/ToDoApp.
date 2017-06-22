package com.bridgelabz.toDoApp.Social;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.bridgelabz.toDoApp.model.GmailProfile;
import com.bridgelabz.toDoApp.model.GoogleAccessToken;

public class GoogleConnection {

	public static final String App_Id = "895648620888-d8n6sjqpk4alg5761qdc71bl84keu8uc.apps.googleusercontent.com";
	public static final String Secret_Id = "ok-6vh5WwZuOEE5vJA_VA6WQ";
	public static final String Redirect_URI = "http://localhost:8080/toDoApp_2017/googleConnection";
	
	
	public GoogleConnection() {}
	public String getGoogleAuthURL(String unid){
		
		String googleLoginURL = "";
		
		try{
			googleLoginURL = "https://accounts.google.com/o/oauth2/v2/auth?client_id="+App_Id+"&redirect_uri="+URLEncoder.encode(Redirect_URI,"UTF-8")+
					"&state="+unid+"&response_type=code&scope=profile email&approval_prompt=force&access_type=offline";
		}
		catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		return googleLoginURL;
	}
	
	public String getAccessToken(String authCode) throws UnsupportedEncodingException{
		
		
		String accessTokenURL = "https://www.googleapis.com/oauth2/v4/token";
		
		ResteasyClient restCall = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = restCall.target(accessTokenURL);
		
		Form f = new Form();
		f.param("client_id", App_Id);
		f.param("client_secret", Secret_Id);
		f.param("redirect_uri",Redirect_URI );
		f.param("code", authCode);
		f.param("grant_type","authorization_code");
		
		Response response = target.request().accept(MediaType.APPLICATION_JSON).post(Entity.form(f) );
		
		GoogleAccessToken accessToken = response.readEntity(GoogleAccessToken.class);
		
		restCall.close();
		
		return accessToken.getAccess_token();
	}

	public GmailProfile getUserProfile(String accessToken) {
		String gmail_user_url= "https://www.googleapis.com/plus/v1/people/me";
		
	
		
		ResteasyClient restCall = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = restCall.target(gmail_user_url);
		
		String headerAuth="Bearer "+accessToken;
		Response response = target.request().header("Authorization", headerAuth).accept(MediaType.APPLICATION_JSON).get();
		
		GmailProfile profile = response.readEntity(GmailProfile.class);
		restCall.close();
		
		System.out.println(profile.getEmails().get(0).getValue());
		return profile;
	}
}
