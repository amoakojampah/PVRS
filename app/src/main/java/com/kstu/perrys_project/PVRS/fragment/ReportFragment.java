package com.kstu.perrys_project.PVRS.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Toast;

import com.kstu.perrys_project.PVRS.AppUtils;
import com.kstu.perrys_project.PVRS.Constants;
import com.kstu.perrys_project.PVRS.MainActivity;
import com.kstu.perrys_project.PVRS.R;
import com.kstu.perrys_project.PVRS.model.Report;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportFragment extends Fragment {

    // final static String REFERENCE_CHILD = "reports";
    FirebaseDatabase mReportDatabase;
    DatabaseReference mReportDatabaseReference;
    ScrollView layout;
    String drugtype = "drug";
    Calendar calender;
    SimpleDateFormat simpledateformat;
    String Date;
    private EditText drugName;
    private EditText pharmacyName_and_location;
    private EditText complainDetails;
    private EditText complaintName;
    private EditText complaintTel;
    private EditText fdaNumber;
    private Button mSendReportBtn;
    private RadioButton mDrugRB;
    private RadioButton mFoodRB;

    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        setHasOptionsMenu(true);

        layout = view.findViewById(R.id.report_frame_layout);

        mReportDatabase = FirebaseDatabase.getInstance();
        mReportDatabaseReference = mReportDatabase.getReference(Constants.REFERENCE_CHILD_REPORTS);

        drugName = view.findViewById(R.id.product_name);
        fdaNumber = view.findViewById(R.id.ET_nafdac_number);
        pharmacyName_and_location = view.findViewById(R.id.store_location);
        complainDetails = view.findViewById(R.id.complain_detail);
        complaintName = view.findViewById(R.id.complaint_name);
        complaintTel = view.findViewById(R.id.complaint_phone_no);
        mSendReportBtn = view.findViewById(R.id.BTN_send_report);
        mDrugRB = view.findViewById(R.id.RB_drug_type);
        mFoodRB = view.findViewById(R.id.RB_food_type);

        calender = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date = simpledateformat.format(calender.getTime());

        mDrugRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "clicked Tablet", Toast.LENGTH_SHORT).show();
                drugtype = "tablet";
            }
        });

        mFoodRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "clicked Syrup ;", Toast.LENGTH_SHORT).show();
                drugtype = "syrup";
            }
        });

        mSendReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate(drugName) && validate(pharmacyName_and_location) && validate(complaintTel)) {
                    addReport();
                }
            }
        });

        return view;
    }

    private boolean validate(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returns true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Report");
    }

    public void addReport() {

        final String dname = drugName.getText().toString().trim();
        final String location = pharmacyName_and_location.getText().toString().trim();
        final String cdetails = complainDetails.getText().toString().trim();
        final String cname = complaintName.getText().toString().trim();
        final String ctel = complaintTel.getText().toString().trim();
        final String fdacno = fdaNumber.getText().toString().trim();


        if (!TextUtils.isEmpty(dname) && !TextUtils.isEmpty(drugtype)) {
            AppUtils.showToast(getContext(), "Reporting");
            Report report = new Report(dname, drugtype, fdacno, location, cdetails, cname, ctel, Date);

            mReportDatabaseReference.push().setValue(report).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        AppUtils.showToast(getContext(), "Thank You for the Report");
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }

                }
            });
        } else {
            Snackbar snackbar = Snackbar
                    .make(layout, "All Fields are required", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }


}
