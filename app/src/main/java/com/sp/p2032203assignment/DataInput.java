package com.sp.p2032203assignment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataInput#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataInput extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button buttonSave;
    private Button buttonCancel;
    private ImageButton buttonPhoto;

    private TextView Photo_status;

    public DataInput() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataInput.
     */
    // TODO: Rename and change types and number of parameters
    public static DataInput newInstance(String param1, String param2) {
        DataInput fragment = new DataInput();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getActivity().setTitle("Data Input");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_input, container, false);
        Photo_status = (TextView) view.findViewById(R.id.photo_status);
        buttonSave = (Button) view.findViewById(R.id.save_button);
        buttonSave.setOnClickListener(this);
        buttonCancel = (Button) view.findViewById(R.id.kancel_button);
        buttonCancel.setOnClickListener(this);
        buttonPhoto = (ImageButton) view.findViewById(R.id.add_photo);
        buttonPhoto.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_button:
                Toast.makeText(getContext(), "has been saved", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigate(R.id.action_dataInput_to_display); //safe args is better but i lazy
                break;
            case R.id.kancel_button:
                Navigation.findNavController(v).navigate(R.id.action_dataInput_to_home); //safe args is better but i lazy
                break;
            case R.id.add_photo:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                break;
        }
    }

    public int PICK_IMAGE = 1; //request code:     int: If >= 0, this code will be returned in onActivityResult() when the activity exits.

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri)
                    Photo_status.setText("Photo has been saved");
            }
        }
    }
}