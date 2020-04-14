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
        // Links XML elements to class variables
        mButton = (Button) findViewById(R.id.button);
        mQuote = (TextView) findViewById(R.id.quote);
        // Generates a quote on start up
        generateQuote();

        // Sets an OnClickListener that will help change the quote
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // Quote is changed every time button is click
                generateQuote();


            }
        });
    }
    // method for code re-usability so that the quote generates when the app is started and when changed
    public void generateQuote() {
        //Create Retrofit instance and parse the retrieved Json using Gson deserializer
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.chucknorris.io/").addConverterFactory(GsonConverterFactory.create()).build();
        //Get service and call object for the request
        JokesService service = retrofit.create(JokesService.class);
        Call<Jokes> jokesCall = service.getJoke();
        jokesCall.enqueue(new Callback<Jokes>() {
            @Override
            public void onResponse(Call<Jokes> call, Response<Jokes> response) {
                String jokes =response.body().getValue();
                // Changes the quote encapsulated in quotation marks
                mQuote.setText("\""+jokes+"\"");
            }

            @Override
            public void onFailure(Call<Jokes> call, Throwable t) {
            }
        });
    }
}
