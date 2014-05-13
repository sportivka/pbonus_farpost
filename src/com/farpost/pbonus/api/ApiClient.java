
package com.farpost.pbonus.api;

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


public class ApiClient {
	/*
	public String GetRequest(){
		InputStream instream = null;
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
	
	
	  private static String read(InputStream instream) {
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

     } */
}