package com.example.testapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity implements LocationListener, OnClickListener {

	LocationManager mLocationManager;
	Button btnQeleNiBrowser;
	ImageView fotografia;
	EditText teksti;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        fotografia = (ImageView) findViewById(R.id.fotografia);
        
        btnQeleNiBrowser = (Button) 
        		findViewById(R.id.btnQeleBrowserin);
        
        btnQeleNiBrowser.setOnClickListener(this);
        
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(mLocationManager.GPS_PROVIDER, 0, 10, this);
        mLocationManager.requestLocationUpdates(mLocationManager.NETWORK_PROVIDER, 0, 10, this);
        
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    		// TODO Auto-generated method stub
    		super.onActivityResult(requestCode, resultCode, data);
    		
    		
				InputStream istream;
				try {
					istream = getContentResolver().openInputStream(data.getData());
					Bitmap bmp = BitmapFactory.decodeStream(istream);
					istream.close();
					fotografia.setImageBitmap(bmp);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
    		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onLocationChanged(Location location) {
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+
					location.getLatitude()+","+location.getLongitude()));
		startActivity(i);
		// geo:12.34343,42.234334
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		if (v == btnQeleNiBrowser) {
			Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i, 0);
		}
		
	}
}
