package com.example.libraskids.VideoPlay;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraskids.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class VpActivity extends AppCompatActivity {

    private RecyclerView videoplayRV;
    private DatabaseReference fireBaseRef;
    private ArrayList<ModelVideoPlay> videoPlayArrayList;
    private AdapterVP adapterVP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);

        //removendo sidebar
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        videoplayRV = findViewById(R.id.videoplayRV);

        loadvideofromfirebase();
    }

    private void loadvideofromfirebase() {

        videoPlayArrayList = new ArrayList<>();

        adapterVP = new AdapterVP(VpActivity.this, videoPlayArrayList);
        fireBaseRef = FirebaseDatabase.getInstance().getReference("play");
        fireBaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ModelVideoPlay modelVideoPlay = dataSnapshot.getValue(ModelVideoPlay.class);
                    videoPlayArrayList.add(modelVideoPlay);
                }

                videoplayRV.setAdapter(adapterVP);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}