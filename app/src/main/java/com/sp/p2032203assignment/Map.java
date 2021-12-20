package com.sp.p2032203assignment;

import android.os.Bundle;

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

public class Map extends Fragment implements View.OnClickListener {
    private TextView photo_status;
    private TextView parcel_textview;
    private TextView location_textview;
    private Button buttonBack;

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
        Bundle bundle = getArguments();
        String status = bundle.getString("status");
        photo_status = (TextView) view.findViewById(R.id.map_text_status);
        String location = bundle.getString("location");
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

}