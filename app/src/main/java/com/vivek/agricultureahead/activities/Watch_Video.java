package com.vivek.agricultureahead.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vivek.agricultureahead.R;
import com.vivek.agricultureahead.adapters.PolicyAdapter;
import com.vivek.agricultureahead.adapters.VideoAdapter;

import java.util.ArrayList;


public class Watch_Video extends AppCompatActivity {

    private VideoAdapter adapter;
    private ArrayList list;
    private RecyclerView recyclerView;

    private String[] links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        links=getResources().getStringArray(R.array.video_link);

        list = new ArrayList<>();

        list=new ArrayList();

        String[] array=getResources().getStringArray(R.array.videos);

        for(int i=0;i<array.length;i++){
            list.add(array[i]);
        }

        adapter = new VideoAdapter(this,list,links);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.progress).setVisibility(View.GONE);
    }
}
