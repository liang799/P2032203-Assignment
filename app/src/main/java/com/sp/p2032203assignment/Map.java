package com.sp.p2032203assignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Map extends Fragment {
    private TextView photo_status;
    private TextView parcel_textview;
    private TextView location_textview;

    public Map() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        Bundle bundle = getArguments();
        String status = bundle.getString("status");
        String location = bundle.getString("location");
        String pacakageId = bundle.getString("packageId");
        Toast.makeText(getContext(), status, Toast.LENGTH_SHORT).show();

        return v;
    }
}