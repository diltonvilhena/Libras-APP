package com.example.libraskids.Activity;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraskids.Model.Item;
import com.example.libraskids.R;
import com.example.libraskids.Adapter.AdapterDetails;

import java.util.ArrayList;
import java.util.Objects;

public class DetailsItemActivity extends AppCompatActivity {

    private RecyclerView videoRV;
    private ArrayList<Item> videoArrayList;
    private AdapterDetails adapterDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        videoRV = findViewById(R.id.videosRV);

        //removendo sidebar
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //recebe os dados
        Bundle bundle = getIntent().getExtras();
        Integer position = bundle.getInt("position");
        videoArrayList = bundle.getParcelableArrayList("list");
        videoRV.getLayoutManager().scrollToPosition(position);
        loadList();
    }
    private void loadList(){
        adapterDetails = new AdapterDetails(DetailsItemActivity.this, videoArrayList);
        videoRV.setAdapter(adapterDetails);
    }
}