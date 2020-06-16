package com.example.ntinfo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {
    public interface Service {
        @GET("https://api.nytimes.com/svc/archive/v1/{year}/{month}.json?api-key=sbt8XxGLFQTfGZB8TTiJWTZxWpPMf74D")
        Call<ArticleRespoce> getArtilcles(@Path("year") int y1, @Path("month") int i2);
    }

    int DIALOG_DATE = 1;
    int myYear = 2011;
    int myMonth = 02;
    int myDay = 03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);
        Retrofit retrofit = new Retrofit.Builder()
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
        });
        DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
        tpd.getDatePicker().setMaxDate(Calendar.getInstance().getTime().getTime());
        Calendar c = Calendar.getInstance();
        c.set(1860, 0, 1);
        tpd.getDatePicker().setMinDate(c.getTime().getTime());
        tpd.show();
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
        }
    };
}