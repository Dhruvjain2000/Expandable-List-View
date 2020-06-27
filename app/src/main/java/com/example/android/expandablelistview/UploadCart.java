package com.example.android.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.expandablelistview.common.model.Cart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadCart extends AppCompatActivity {

    private EditText etProductName;
    private Button btnSubmit;
    DatabaseReference mDatabaseRef;
    private FirebaseAuth mauth;
    private ProgressDialog pd;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_cart);

        etProductName = findViewById(R.id.et_Name);
        btnSubmit = findViewById(R.id.submit);

        mauth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("cart");
        pd = new ProgressDialog(this);

//        userId = mauth.getUid().toString();
        userId = "scdvf";

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("Upload in progress");
                pd.show();
                uploadFile();
            }
        });
    }
    private void uploadFile() {
        String uploadId = mDatabaseRef.push().getKey();
        Cart upload = new Cart(userId,uploadId,etProductName.getText().toString());
        mDatabaseRef.child(uploadId).setValue(upload);
        pd.dismiss();
        Toast.makeText(getBaseContext(), "Upload successful", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();

    }

}
