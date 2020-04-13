package com.example.inspirationalquotes;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JokesService {
        @GET("jokes/random?category=dev")
        Call<Jokes> getJoke();
    }

