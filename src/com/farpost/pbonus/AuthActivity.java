package com.farpost.pbonus;

import com.vk.sdk.VKUIHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
	}

	public void auth_function(View view) {
		Intent myIntent = new Intent(this, com.farpost.pbonus.MainActivity.class);
		startActivity(myIntent);
	}
}
