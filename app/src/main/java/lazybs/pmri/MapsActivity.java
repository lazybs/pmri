package lazybs.pmri;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;

import android.widget.TextView;



public class MapsActivity extends FragmentActivity implements  OnMapLongClickListener{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LocationManager locationManager;
    Location location;
    TextView tvLocInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        setUpMapIfNeeded();

        mMap.setOnMapLongClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            mMap.setMyLocationEnabled(true);
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                double lat = location.getLatitude();
                double log = location.getLongitude();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,log),15));




            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(51.03803,13.69432)).title("Loebtau Socket"));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.026664, 13.725405))
                .title("UniSocket")
                .snippet("additional information"));

    }


    @Override
    public void onMapLongClick(LatLng point) {
       // tvLocInfo.setText("New marker added@" + point.toString());
        mMap.addMarker(new MarkerOptions().position(point).title("new socket").snippet(""));

    }
}