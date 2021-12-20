package com.sp.p2032203assignment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class Map extends Fragment implements View.OnClickListener, OnMapReadyCallback {
    private TextView photo_status;
    private TextView parcel_textview;
    private TextView location_textview;
    private Button buttonBack;
    private String[] latlong;
    private double latitude;
    private double longitude;
    private String location;
    private MapView mapView;
    private GoogleMap map;

    public Map() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        Bundle bundle = getArguments();
        String status = bundle.getString("status");
        photo_status = (TextView) view.findViewById(R.id.map_text_status);
        location = bundle.getString("location");
        location_textview = (TextView) view.findViewById(R.id.map_text_location);
        String pacakageId = bundle.getString("packageId");
        parcel_textview = (TextView) view.findViewById(R.id.map_text_parcel);

        parcel_textview.setText(pacakageId);
        location_textview.setText(location);
        photo_status.setText(status);

        buttonBack = (Button) view.findViewById(R.id.back_button);
        buttonBack.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Navigation.findNavController(v).navigate(R.id.action_map_to_display);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.edit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), "Error! Location Permission needed!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (location.lastIndexOf(',') != -1) {
            latlong = location.split(",");
            latitude = Double.parseDouble(latlong[0]);
            longitude = Double.parseDouble(latlong[1]);
            // Updates the location and zoom of the MapView
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15);
            map.animateCamera(cameraUpdate);
        } else {
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(getLocationFromAddress(getContext(), location), 15);
            map.animateCamera(cameraUpdate);
        }

//        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(43.1, -87.9)));
    }


    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}