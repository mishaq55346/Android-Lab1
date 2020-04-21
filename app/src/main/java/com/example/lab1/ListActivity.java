package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<NumItem> nums = new ArrayList<>();
    ChickenAdapter adapter;
    RecyclerView recyclerView;
    boolean isLoading = false;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        for (int i = 1; i < 50 + 1; i++) {
            nums.add(new NumItem(i));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            adapter = new ChickenAdapter(nums);
        }

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() == nums.size() - 1) {
                        Log.d("scroll", "need to load more further");
                        loadMore();
                        isLoading = true;
                    }
                }
            }
            void loadMore(){
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (nums.size() >= 1000000)
                            return;
                        int scrollPosition = nums.size();
                        int currentSize = scrollPosition;
                        int nextLimit = currentSize + 50;
                        for (int i = nums.size(); i < nextLimit; i++){
                            nums.add(new NumItem(i + 1));
                            adapter.FillNum(i);
                        }
                        /*


                        while (currentSize - 1 < nextLimit) {
                            nums.add(new NumItem(currentSize));
                            currentSize++;
                        }
                        */
                        adapter.notifyDataSetChanged();
                        isLoading = false;
                    }
                }, 500);
            }
        });


    }

}
