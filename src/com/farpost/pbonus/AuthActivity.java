package com.farpost.pbonus;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCaptchaDialog;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.util.VKUtil;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;

public class AuthActivity extends Activity {
	@Override 
	protected void onResume() { 
	super.onResume(); 
	VKUIHelper.onResume(this); 
	} 

	@Override 
	protected void onDestroy() { 
	super.onDestroy(); 
	VKUIHelper.onDestroy(this); 
	} 

	@Override 
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
	VKUIHelper.onActivityResult(requestCode, resultCode, data); 
	} 
	
	
    private static String sTokenKey = "UIM9Hwu4AucKdVXYLqpt";
    private static String[] sMyScope = new String[]{VKScope.FRIENDS, VKScope.WALL, VKScope.PHOTOS, VKScope.NOHTTPS};
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  VKSdk.initialize(sdkListener, "4286713", VKAccessToken.tokenFromSharedPreferences(this, sTokenKey));
		setContentView(R.layout.activity_auth);

	}

	public void auth_function(View view) {
		Intent myIntent = new Intent(this, com.farpost.pbonus.MainActivity.class);
		startActivity(myIntent);
        finish();
	}
	
	public void act_oauth_twitter(View view){
		Log.d("App","clicked twitter oAuth");	
	}
	public void act_oauth_fb(View view){
		Log.d("App","clicked facebook oAuth");	
	}
	public void act_oauth_google(View view){
		Log.d("App","clicked google oAuth");	
	}
	
	public void act_oauth_vk(View view){
		String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName()); 
		
	    Log.d("App", fingerprints[0]);
	    VKSdk.authorize(sMyScope, true, false);
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
	            /* Intent i = new Intent(AuthActivity.this, MainActivity.class);
	            startActivity(i); 
	             */
	             
	            Log.d("onReceiveNewToken ACCESS_TOKEN:",newToken.accessToken);
	        	Log.d("onReceiveNewToken SECRET:",newToken.secret);
	        	Log.d("onReceiveNewToken USER_ID:",newToken.userId);
	        }

	        @Override
	        public void onAcceptUserToken(VKAccessToken token) {
	           /* Intent i = new Intent(AuthActivity.this, MainActivity.class);
	            startActivity(i);
	        	VKRequest request = VKApi.users().get();  */
	        	
	            Log.d("onReceiveNewToken ACCESS_TOKEN:",token.accessToken);
	        	Log.d("onReceiveNewToken SECRET:",token.secret);
	        	Log.d("onReceiveNewToken USER_ID:",token.userId);
	        }
 
	 };
	 
	 
}
