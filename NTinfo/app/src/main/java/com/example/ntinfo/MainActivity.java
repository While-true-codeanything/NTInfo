package com.example.ntinfo;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private static MainActivity m;

    static public void loadFragment(Fragment fragment) {
        FragmentTransaction ft = m.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.place, fragment);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m = this;
        ActionBar b = getActionBar();
        new DataGetter(MainActivity.this).GetTop();
        setContentView(R.layout.first_page);
        loadFragment(new DataSelectorFragment());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.menu);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.All:
                        if (DataGetter.isAnyData)
                            loadFragment(new ListFragment(DataGetter.result));
                        else {
                            Toast.makeText(MainActivity.this, "Ошибка, нет загруженных данных, выберете месяц и год!",
                                    Toast.LENGTH_LONG).show();
                        }
                        return true;
                    case R.id.Top:
                        if (DataGetter.TisAnyData)
                            loadFragment(new ListFragment(DataGetter.Tresult));
                            //* loadFragment(new ListFragment(DataGetter.result));*/
                        else {
                            Toast.makeText(MainActivity.this, "Ошибка, нет загруженных данных, проверьте подключение к сети!",
                                    Toast.LENGTH_LONG).show();
                        }
                        return true;
                }
                return false;
            }
        });
    /*    Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.nytimes.com/")//unused
                .build();
        Service service = retrofit.create(Service.class);
        service.getArtilcles(1860, 1).enqueue(new Callback<ArticleRespoce>() {
            @Override
            public void onResponse(Call<ArticleRespoce> call, Response<ArticleRespoce> response) {
                Toast.makeText(MainActivity.this, response.body().getResponse().getDocs()[0].getLead_paragraph(),
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ArticleRespoce> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.upbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_settings:
                loadFragment(new DataSelectorFragment());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}