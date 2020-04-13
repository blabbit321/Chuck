package com.example.inspirationalquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private TextView mQuote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button);
        mQuote = (TextView) findViewById(R.id.quote);
        generateQuote();


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                generateQuote();


            }
        });
    }
    public void generateQuote() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.chucknorris.io/").addConverterFactory(GsonConverterFactory.create()).build();
        JokesService service = retrofit.create(JokesService.class);
        Call<Jokes> jokesCall = service.getJoke();
        jokesCall.enqueue(new Callback<Jokes>() {
            @Override
            public void onResponse(Call<Jokes> call, Response<Jokes> response) {
                String jokes =response.body().getValue();
                mQuote.setText("\""+jokes+"\"");
            }

            @Override
            public void onFailure(Call<Jokes> call, Throwable t) {
            }
        });
    }
}
