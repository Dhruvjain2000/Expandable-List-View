package com.example.android.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.android.expandablelistview.common.data.ExampleExpandableDataProvider;
import com.example.android.expandablelistview.common.model.Cart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roger.catloadinglibrary.CatLoadingView;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mRef;
    private DatabaseReference mCart;
    private CatLoadingView mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = new CatLoadingView();
        mView.show(getSupportFragmentManager(), "");

        readAndSerialise();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(),ExpandableDraggableSwipeableExampleActivity.class));
                finish();
            }
        },3000);
    }


    private void getCartContents() {
        mRef = FirebaseDatabase.getInstance().getReference();
        mCart = mRef.child("cart");
        ExampleExpandableDataProvider.cartContents = new ArrayList<Cart>();

        mCart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> c = dataSnapshot.getChildren();
                for (DataSnapshot dsCart : c) {
                    Cart cartContent = dsCart.getValue(Cart.class);
                    ExampleExpandableDataProvider.cartContents.add(cartContent);
                    String s;
                    s = "user : ";
                    s = s + cartContent.getUser();
                    s = s + "$";
                    ExampleExpandableDataProvider.groupItems = ExampleExpandableDataProvider.groupItems + s;
                    s = s + "status : ";
                    s = s + cartContent.getStatus();
                    s = s + "$";
                    s = s + "product : ";
                    s = s + cartContent.getProduct();
                    s = s + "$";
                    ExampleExpandableDataProvider.childItems = ExampleExpandableDataProvider.childItems + s;
                    Log.e(TAG, "onDataChange: " + s );
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: " + databaseError.getMessage());
            }
        });
    }


    private void readAndSerialise() {
        ExampleExpandableDataProvider.groupItems = "";
        ExampleExpandableDataProvider.childItems = "";
        getCartContents();

    }
}
