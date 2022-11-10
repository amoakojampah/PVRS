package com.kstu.perrys_project.PVRS.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kstu.perrys_project.PVRS.Constants;
import com.kstu.perrys_project.PVRS.DrugDetailsActivity;
import com.kstu.perrys_project.PVRS.R;
import com.kstu.perrys_project.PVRS.ScanActivity;
import com.kstu.perrys_project.PVRS.model.Drug;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class VerifyFragment extends Fragment {

    // final static String REFERENCE_CHILD = "products";
    Button mSearchNafdacNumberBtn;
    EditText mNafdacNumberEditText;
    CoordinatorLayout layout;
    DatabaseReference myRef;
    Drug drug;
    ProgressDialog progressDialog;


    public VerifyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify, container, false);

        layout = view.findViewById(R.id.coordinator_layout);

        myRef = FirebaseDatabase.getInstance().getReference(Constants.REFERENCE_CHILD_PRODUCTS);
        mNafdacNumberEditText = view.findViewById(R.id.nafdac_number_edittext);


        mSearchNafdacNumberBtn = view.findViewById(R.id.nafdac_number_search_button);
        mSearchNafdacNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = mNafdacNumberEditText.getText().toString().trim();
                // close the activity in case of empty barcode
                if (validate(mNafdacNumberEditText)) {
                    FindItem(code);
                }
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ScanActivity.class));
            }
        });

        return view;
    }

    private boolean validate(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returs true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }

    public void FindItem(String value) {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Searching..."); // Setting Message
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        //  Toast.makeText(getContext(), "Searching", Toast.LENGTH_LONG).show();
        String child = "fdaNumber";

        Query query = myRef.orderByChild(child).equalTo(value);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                drug = dataSnapshot.getValue(Drug.class);

                progressDialog.dismiss();
                if (drug != null) {

                    Toast.makeText(getContext(), drug.getManufacturerName(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getContext(), DrugDetailsActivity.class);
                    intent.putExtra("drugName", drug.getDrugName());
                    intent.putExtra("drugType", drug.getDrugType());
                    intent.putExtra("imageUrl", drug.getImageUrl());
                    intent.putExtra("fdaNumber", drug.getFdaNumber());
                    intent.putExtra("manufacturingName", drug.getManufacturerName());
                    intent.putExtra("batchNumber", drug.getBatchNumber());
                    intent.putExtra("manufacturingDate", drug.getManufacturingDate());
                    intent.putExtra("expiringDate", drug.getExpiringDate());
                    startActivity(intent);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Item not found: " + databaseError, Toast.LENGTH_LONG).show();
            }
        });

        progressDialog.dismiss();
    }


}
