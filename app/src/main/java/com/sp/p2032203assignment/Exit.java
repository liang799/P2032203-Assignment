package com.sp.p2032203assignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Exit extends Fragment implements View.OnClickListener {
    private Button buttonYes;
    private Button buttonNo;

    public Exit() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Exit Confirmation");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, //usually is super.onCreate --> inflate layout --> Button initialisation
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exit, container, false);
        buttonYes = (Button) view.findViewById(R.id.exit_me);
        buttonYes.setOnClickListener(this);
        buttonNo = (Button) view.findViewById(R.id.no_exit);
        buttonNo.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit_me:
                getActivity().finish();
                System.exit(0);
                break;
            case R.id.no_exit:
                FragmentManager fm = getParentFragmentManager();
                fm.popBackStack();
                break;
        }

    }
}