package com.kstu.perrys_project.PVRS;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.widget.Toast;

import com.kstu.perrys_project.PVRS.model.Drug;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class ScanActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    final static String REFERENCE_CHILD = "drugs";
    private static final String TAG = "ScanActivity";
    BarcodeReader barcodeReader;
    Drug drug;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        myRef = FirebaseDatabase.getInstance().getReference(REFERENCE_CHILD);
        // get the barcode reader instance
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);

    }

    @Override
    public void onScanned(final Barcode barcode) {
        // playing barcode reader beep sound
        barcodeReader.playBeep();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Barcode: " + barcode.displayValue, Toast.LENGTH_SHORT).show();
                FindItem(barcode.displayValue);
            }
        });

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

        String codes = "";
        for (Barcode barcode : barcodes) {
            codes += barcode.displayValue + ", ";
        }

        final String finalCodes = codes;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Barcodes: " + finalCodes, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {
        Toast.makeText(getApplicationContext(), "Error occurred while scanning " + errorMessage, Toast.LENGTH_SHORT).show();

    }

    public void FindItem(String value) {

        Toast.makeText(getApplicationContext(), "Searching", Toast.LENGTH_LONG).show();
        String child = "fdaName";

        Query query = myRef.orderByChild(child).equalTo(value);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                drug = dataSnapshot.getValue(Drug.class);

                if (drug != null) {

                    Toast.makeText(getApplicationContext(), drug.getManufacturerName(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), DrugDetailsActivity.class);
                    intent.putExtra("drugName", drug.getDrugName());
                    intent.putExtra("productType", drug.getDrugType());
                    intent.putExtra("imageUrl", drug.getImageUrl());
                    intent.putExtra("fdaName", drug.getFdaNumber());
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
                Toast.makeText(getApplicationContext(), "Item not found: " + databaseError, Toast.LENGTH_LONG).show();
            }
        });
    }

}
