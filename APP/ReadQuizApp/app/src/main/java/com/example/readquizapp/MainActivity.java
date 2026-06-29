package com.example.readquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readquizapp.api.RetrofitClient;
import com.example.readquizapp.model.Reading;
import com.example.readquizapp.utils.PreferenceManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvReadings;
    private ReadingAdapter adapter;
    private List<Reading> readingList = new ArrayList<>();
    private FloatingActionButton fabAdd;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        preferenceManager = new PreferenceManager(this);

        // Setup Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Welcome, " + preferenceManager.getUserName());
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvReadings = findViewById(R.id.rvReadings);
        fabAdd = findViewById(R.id.fabAdd);

        rvReadings.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReadingAdapter(readingList, reading -> {
            Intent intent = new Intent(MainActivity.this, ReadingDetailActivity.class);
            intent.putExtra("READING_ID", (int)reading.getId());
            startActivity(intent);
        });
        rvReadings.setAdapter(adapter);

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditReadingActivity.class);
            intent.putExtra("READING_ID", -1);
            startActivity(intent);
        });

        fetchReadings();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            preferenceManager.clear();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchReadings();
    }

    private void fetchReadings() {
        RetrofitClient.getApiService().getAllReadings().enqueue(new retrofit2.Callback<com.example.readquizapp.model.ApiResponse<List<Reading>>>() {
            @Override
            public void onResponse(Call<com.example.readquizapp.model.ApiResponse<List<Reading>>> call, Response<com.example.readquizapp.model.ApiResponse<List<Reading>>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    readingList.clear();
                    readingList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch readings", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.readquizapp.model.ApiResponse<List<Reading>>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
