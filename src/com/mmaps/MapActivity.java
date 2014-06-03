package com.mmaps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MapActivity extends ActionBarActivity implements OnMapClickListener, OnMapLongClickListener {

	private GoogleMap map;		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.map, new PlaceholderFragment()).commit();
		}			
		initMap();
	}
	
	void initMap() {
		
		map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.setOnMapClickListener(this);
		CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(50.4429, 30.5119)).zoom(12).build();
		map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	}
	
	@Override
	public void onMapClick(LatLng position) {
		TextView textEdit = (TextView)findViewById(R.id.mapText);
		textEdit.setText(position.toString());
		
		MarkerOptions marker = new MarkerOptions().position(position).title("label");
		map.addMarker(marker);
		
		CircleOptions circleOptions = new CircleOptions()
		.center(position)
		.radius(MmapsConstants.MAP_CYCLE_SIZE)
		.fillColor(Color.TRANSPARENT)		
		.strokeWidth(2);	
		Circle circle = map.addCircle(circleOptions);
//		Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_map, container,
					false);			
			return rootView;
		}
	}

	@Override
	public void onMapLongClick(LatLng arg0) {
		// TODO Auto-generated method stub
		
	}

}
