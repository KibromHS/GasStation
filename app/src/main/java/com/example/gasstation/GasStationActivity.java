package com.example.gasstation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gasstation.models.GasStationModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GasStationActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Toolbar toolbar;
    private TextView openIndicator, closedIndicator, stationName;

    private GasStationModel gasStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station);
        initView();

        toolbar.setTitle(gasStation.getStationName());
        stationName.setText(gasStation.getStationName());
        setIndicator(gasStation.isOpen());
        initMap();
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @NonNull
    private MarkerOptions getMarkerOptions(LatLng latLng, String title) {
        final int height = 130;
        final int width = 88;
        BitmapDrawable drawable = (BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.app_icon, null);
        assert drawable != null;
        Bitmap stationMarker = Bitmap.createScaledBitmap(drawable.getBitmap(), width, height, false);
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(stationMarker);

        return new MarkerOptions().position(latLng).title(title).icon(icon);
    }

    private void setIndicator(boolean open) {
        if (open) {
            openIndicator.setVisibility(View.VISIBLE);
            closedIndicator.setVisibility(View.INVISIBLE);
        } else {
            openIndicator.setVisibility(View.INVISIBLE);
            closedIndicator.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        openIndicator = findViewById(R.id.openIndicator);
        closedIndicator = findViewById(R.id.closeIndicator);
        stationName = findViewById(R.id.stationName);

        gasStation = (GasStationModel) getIntent().getSerializableExtra("station");
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap.addMarker(getMarkerOptions(gasStation.getLocation(), gasStation.getStationName()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gasStation.getLocation(), 16));
    }
}