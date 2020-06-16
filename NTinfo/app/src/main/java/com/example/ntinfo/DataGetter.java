package com.example.ntinfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class DataGetter {
    public static Boolean isAnyData = false;
    public static Boolean TisAnyData = false;
    static public ArticleRespoce result;
    static public TopResponse Tresult;
    private Context context;

    public DataGetter(Context ct) {
        context = ct;
    }

    public interface ServiceAll {
        @GET("https://api.nytimes.com/svc/archive/v1/{year}/{month}.json?api-key=sbt8XxGLFQTfGZB8TTiJWTZxWpPMf74D")
        Call<ArticleRespoce> getArtilcles(@Path("year") int y1, @Path("month") int i2);
    }

    public interface ServiceTop {
        @GET("https://api.nytimes.com/svc/topstories/v2/home.json?api-key=sbt8XxGLFQTfGZB8TTiJWTZxWpPMf74D")
        Call<TopResponse> getArtilcles();
    }

    public void GetForDate(int month, int year) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.nytimes.com/")//unused
                .build();

        DataGetter.ServiceAll service = retrofit.create(DataGetter.ServiceAll.class);
        ProgressDialog pd = ProgressDialog.show(context, "", "Loading", true,
                false);
        service.getArtilcles(year, month).enqueue(new Callback<ArticleRespoce>() {
            @Override
            public void onResponse(Call<ArticleRespoce> call, Response<ArticleRespoce> response) {
                isAnyData = true;
                result = response.body();
                pd.dismiss();
            }

            @Override
            public void onFailure(Call<ArticleRespoce> call, Throwable t) {
                Toast.makeText(context, "Ошибка, проверьте подключение к интернету или доступность сервиса!",
                        Toast.LENGTH_LONG).show();
                pd.dismiss();
                result = new ArticleRespoce();
            }
        });
      /*  Toast.makeText(context, result.getResponse().getDocs()[0].getLead_paragraph(),
                Toast.LENGTH_LONG).show();*/
        /* return result;*/
    }

    public void GetTop() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.nytimes.com/")//unused
                .build();

        DataGetter.ServiceTop service = retrofit.create(DataGetter.ServiceTop.class);
        ProgressDialog pd = ProgressDialog.show(context, "", "Loading", true,
                false);
        service.getArtilcles().enqueue(new Callback<TopResponse>() {
            @Override
            public void onResponse(Call<TopResponse> call, Response<TopResponse> response) {
              /*  Toast.makeText(context, response.body().getResponse().getDocs()[0].getLead_paragraph(),
                        Toast.LENGTH_LONG).show();*/
                Tresult = response.body();
                TisAnyData = true;
                pd.dismiss();
                /* MainActivity.loadFragment(new ListFragment(TisAnyData));*/
            }

            @Override
            public void onFailure(Call<TopResponse> call, Throwable t) {
                Toast.makeText(context, "Ошибка, проверьте подключение к интернету или доступность сервиса!",
                        Toast.LENGTH_LONG).show();
                pd.dismiss();
                result = new ArticleRespoce();
            }
        });
      /*  Toast.makeText(context, result.getResponse().getDocs()[0].getLead_paragraph(),
                Toast.LENGTH_LONG).show();*/
        /* return result;*/
    }
}
