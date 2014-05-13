package com.farpost.pbonus.Activities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.farpost.pbonus.ConnectionDetector;
import com.farpost.pbonus.R;
import com.farpost.pbonus.api.ApiClient;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCaptchaDialog;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKRequest.VKRequestListener;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.util.VKUtil;



public class AuthActivity extends Activity {

	  
	@Override 
	protected void onResume() { 
	super.onResume(); 
	VKUIHelper.onResume(this);  /*VK Auth*/
	} 

	@Override 
	protected void onDestroy() { 
	super.onDestroy(); 
	VKUIHelper.onDestroy(this); /*VK Auth*/
	} 

	@Override 
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
	VKUIHelper.onActivityResult(requestCode, resultCode, data); /*VK Auth*/
	} 

	/*Shared Preferences*/
	 SharedPreferences sPref;
	 
	/*check internet connection*/
    Boolean isInternetPresent = false; 
	InputStream instream;
    /*Create Connect detector*/
    ConnectionDetector cd;
    String api ;
 	/*Secret token vk app*/
    private static String sTokenKey = "UIM9Hwu4AucKdVXYLqpt";
    
    /*id vk app*/
    private static String AppId = "4286713";
    
    /*Scope for vk api*/
    private static String[] sMyScope = new String[]{VKScope.NOTIFICATIONS, VKScope.PHOTOS,  VKScope.OFFLINE};
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* show Status Bar*/
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		setContentView(R.layout.activity_auth);
		 /*Check internet connection*/
		cd = new ConnectionDetector(getApplicationContext());
		
		 isInternetPresent = cd.isConnectingToInternet();
         if (!isInternetPresent) {
        	 new AlertDialog.Builder(AuthActivity.this)
        	 .setTitle(R.string.chk_cnt)
             .setMessage(R.string.alert_chk_cnt)
             .show();
         }
	}

    
    
    
	public void auth_function(View view) {
		 isInternetPresent = cd.isConnectingToInternet();
         if (!isInternetPresent) {
        	 new AlertDialog.Builder(AuthActivity.this)
        	 .setTitle(R.string.chk_cnt)
             .setMessage(R.string.alert_chk_cnt)
             .show();
        	 
         }else{
        	 
	Intent myIntent = new Intent(this, MainActivity.class);
		startActivity(myIntent);
        finish();
	}
         
	}


	
	
	
	public void act_oauth_twitter(View view){
		Log.d("App","clicked twitter oAuth");	
	}
	
	
	
	
	public void act_oauth_fb(View view){
		Log.d("App","clicked facebook oAuth");	
	}
	
	
	
	public void act_oauth_google(View view){
		Log.d("App","clicked google oAuth");
		
		//ApiClient apiClient = new ApiClient();
		
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {
		        try {
		        	Log.d("App"," " + GetRequest());
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		});

		thread.start();
	}
		
	
    public String GetRequest(){
	
		 try {
             HttpClient httpclient = new DefaultHttpClient();
             HttpGet request = new HttpGet(
                "http://urls.api.twitter.com/1/urls/count.json");

             request.addHeader("Accept", "application/json");
             HttpResponse response = httpclient.execute(request);
             HttpEntity entity = response.getEntity();
           instream = entity.getContent();
         
        } catch (ClientProtocolException e) {
             e.printStackTrace();
        } catch (IOException e) {
             e.printStackTrace();
        }
		
		return  read(instream);	
	}
	
	
	  private  String read(InputStream instream) {
          StringBuilder sb = null;
          try {
               sb = new StringBuilder();
               BufferedReader r = new BufferedReader(new InputStreamReader(
                         instream));
          for (String line = r.readLine(); line != null; line = r.readLine()) {
               	sb.append(line);
			}

			instream.close();

          } catch (IOException e) {
          }
          return sb.toString();

     }
	

	public void act_oauth_vk(View view){
		String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName()); 
		
	    Log.d("App", fingerprints[0]);
	    
	    /*emulate new auth*/
	   /* VKAccessToken.removeTokenAtKey(this, sTokenKey); */
	    
	    /*init vksdk*/ 
		VKSdk.initialize(sdkListener,AppId, VKAccessToken.tokenFromSharedPreferences(this, sTokenKey));
		VKSdk.authorize(sMyScope, false, false);
	    
		Log.d("App","clicked vk oAuth");	
	}
	
	
	 private VKSdkListener sdkListener = new VKSdkListener() {
	        @Override
	        public void onCaptchaError(VKError captchaError) {
	            new VKCaptchaDialog(captchaError).show();
	        }

	        @Override
	        public void onTokenExpired(VKAccessToken expiredToken) {
	            VKSdk.authorize(sMyScope);
	        }

	        @Override
	        public void onAccessDenied(VKError authorizationError) {
	            new AlertDialog.Builder(AuthActivity.this)
	                    .setMessage(authorizationError.errorMessage)
	                    .show();
	        }

	        @Override
	        public void onReceiveNewToken(VKAccessToken newToken) {
	            newToken.saveTokenToSharedPreferences(AuthActivity.this, sTokenKey);
	            GetUserInfo(newToken);
	        }

	        @Override
	        public void onAcceptUserToken(VKAccessToken token) { 	  
	        	GetUserInfo(token);
	        }
	        
	        
	        
	    
	        
	        
	        
	        
	        public void GetUserInfo(final VKAccessToken token) { 
	        	
	        	VKRequest request = VKApi.users().get();
	        	 request.executeWithListener(new VKRequestListener() {		 
	        		 JSONObject params;
	     		 
					public void onComplete(VKResponse response) {
						
							try {
								params = response.json.getJSONArray("response").getJSONObject(0);
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						    					
	     		    	try {
						Log.d("FIRSTNAME:",params.getString("first_name"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	     		    	try {
						Log.d("LASTNAME:",params.getString("last_name"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    	            Log.d("ACCESS_TOKEN:",token.accessToken);
	    	        	//Log.d("SECRET:",token.secret);
	    	        	Log.d("USER_ID:",token.userId);
	     		    	  Intent i = new Intent(AuthActivity.this, MainActivity.class);
	     		            startActivity(i); 
	     		    }
	     
	     		    public void onError(VKError error) {
	     		       new AlertDialog.Builder(AuthActivity.this)
	                    .setMessage(error.errorMessage)
	                    .show();
	     		    }
		    
	     		}); 	
	        }
 
	 };

	


	 
	 
}
