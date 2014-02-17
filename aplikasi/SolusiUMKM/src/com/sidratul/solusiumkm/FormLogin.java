package com.sidratul.solusiumkm;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class FormLogin extends Activity {
	
	private EditText tfUsername;
	private EditText pfPassword;
	private Button bLogin;
	private Button bKeluar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); 
//		StrictMode.setThreadPolicy(policy);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_login);
		
		//db = new DatabaseHandler(this);
		
		tfUsername = (EditText) findViewById(R.id.tfUsername);
		pfPassword = (EditText) findViewById(R.id.pfPassword);
		bLogin = (Button) findViewById(R.id.blogin);
		bKeluar = (Button) findViewById(R.id.bexit);
		
	}
	
	public void cekLogin(View view){
//		String username = tfUsername.getText().toString();
//		String password = pfPassword.getText().toString();
//		
//		User user = this.cekLoginFromServer(username, password);
//		if(user==null){
//			Toast.makeText(getApplicationContext(), "login gagal, kombinasi username dan password salah",Toast.LENGTH_LONG).show();
//			
//			tfUsername.setText("");
//			pfPassword.setText("");
//		}else{
//			db.saveUser(user);
//			Toast.makeText(getApplicationContext(), "login berhasil",Toast.LENGTH_LONG).show();
//			
//			Log.d("hasil",user.getPeran()+" "+user.getUsername());
//			
//			Intent intent = new Intent(FormLogin.this,Main.class);
//			startActivity(intent);
//		}
	}
	
	public void keluar(View view){
//		Toast.makeText(getApplicationContext(),"keluar",Toast.LENGTH_LONG).show();
		finish();
		System.exit(0);
	}
	
	//private User cekLoginFromServer(String username, String password){
//		User user = new User();
//		
//		List<NameValuePair> postParameter = new ArrayList<NameValuePair>();
//		postParameter.add(new BasicNameValuePair("username", username));
//		postParameter.add(new BasicNameValuePair("password", password));
//		
//		String respon = "";
//		
//		try{
//			CustomHttpClient client = new CustomHttpClient();
//			
//			respon = client.executeHttpPost("http://10.0.2.2/koperasi-android/login-user.php",postParameter);
//			
//			String result=respon.toString();
//			
//			Log.i("result",result);
//			
//			try{
//				JSONObject json_data = new JSONObject(result);
//                
//                user.setId(json_data.getInt("id"));
//                user.setUsername(json_data.getString("username"));
//                user.setPassword(json_data.getString("password"));
//                user.setPeran(json_data.getString("peran"));
//                
//                Log.i("hasil","nama :"+user.getUsername());
//                
//                return user;
//			}catch(JSONException e){
//				Log.e("log_tag", "Error parsing data "+e.toString());
//				return null;
//			}
//		}catch(Exception e){
//			Log.e("log_tag","Error in http connection!!" + e.toString());     
//		}
//		return null;
	//}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item){
//		Intent intent;
//		switch (item.getItemId()) {
//		case R.id.keluar:
//			finish();
//			System.exit(0);
//			break;
//
//		default:
//			return super.onOptionsItemSelected(item);			
//		}
//		
//		return true;
//	}
	
}
