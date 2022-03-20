package com.example.libraskids.Activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraskids.Adapter.AdapterDetailsCategory;
import com.example.libraskids.Model.Item;
import com.example.libraskids.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class DetailsCategoryActivity extends AppCompatActivity {

    private DatabaseReference fireBaseRef;
    private RecyclerView recyclerviewid;
    private ArrayList<Item> numerosList;
    private AdapterDetailsCategory numAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_numeros);

            //removendo sidebar
            Objects.requireNonNull(getSupportActionBar()).hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            Bundle bundle = getIntent().getExtras();
            String basePath = bundle.getString("basePath");
            String path = bundle.getString("path");
            fireBaseRef = FirebaseDatabase.getInstance().getReference().child(basePath).child(path);

            //aqui é declarado o formato do recycler view, com 2 colunas na vertical
            recyclerviewid = (RecyclerView) findViewById(R.id.recyclerviewid);
            recyclerviewid.setLayoutManager(new GridLayoutManager(this,2));

            //método que recupera dados para o recyclerview, direto do firebase em tempo real.
            retrieveListFirebase();
        }

    private void retrieveListFirebase(){

        numerosList = new ArrayList<>();
        numAdapter = new AdapterDetailsCategory(this, numerosList);

        fireBaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Item itens_numeros = dataSnapshot.getValue(Item.class);

                numerosList.add(itens_numeros);

                recyclerviewid.setAdapter(numAdapter);
                numAdapter.notifyDataSetChanged();
            }

            // verificações de alterações de conteúdos com notificação em toast no app
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(DetailsCategoryActivity.this, "change", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Toast.makeText(DetailsCategoryActivity.this, "removed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(DetailsCategoryActivity.this, "Moved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DetailsCategoryActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

    }
    }