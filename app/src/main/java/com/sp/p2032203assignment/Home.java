package com.sp.p2032203assignment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Home extends Fragment {
    private Cursor model = null;
    private DeliveryHelper helper = null;
    private TextView completed_text;
    private TextView first;
    private TextView second;
    private TextView third;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Home");
        helper = new DeliveryHelper(getContext());
        model = helper.getAll();
        model.moveToLast();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        completed_text = (TextView) v.findViewById(R.id.completed_per);
        completed_text.setText(helper.getCompletedPercent() + "% Done");
        model.moveToLast();
        first = (TextView) v.findViewById(R.id.first);
        first.setText(helper.getAddress(model));
        model.moveToPrevious();
        second = (TextView) v.findViewById(R.id.second);
        second.setText(helper.getAddress(model));
        model.moveToPrevious();
        third = (TextView) v.findViewById(R.id.third);
        third.setText(helper.getAddress(model));


        return v;
    }
}