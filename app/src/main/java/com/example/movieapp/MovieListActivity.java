package com.example.movieapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.movieapp.models.MovieModel;
import com.example.movieapp.request.MovieServices;
import com.example.movieapp.response.MovieSearchResponse;
import com.example.movieapp.utils.Credentials;
import com.example.movieapp.utils.MovieApi;
import com.example.movieapp.viewmodels.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {

    private static final String TAG = "MovieListActivity";
    private Button button;

    //ViewModel
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        //Calling the observers
        ObserverAnyChange();

        //Testing the method
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovieAPI("Wonder",1);
            }
        });

    }

    //Observers any data change
    private void ObserverAnyChange(){
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels!= null) {
                    for(MovieModel movieModel: movieModels){
                        //GetData
                        Log.v("TAG", "onChanged "+ movieModel.getTitle());
                    }
                }
            }
        });
    }
    //4- Calling method in Main Activity
    private void searchMovieAPI(String query, int pageNumber){
        movieListViewModel.searchMovieAPI(query,pageNumber);
    }


//    private void GetRetrofitResponse() {
//        try {
//            MovieApi movieApi = MovieServices.getMovieApi();
//
//            Call<MovieSearchResponse> responseCall = movieApi
//                    .searchMovie(
//                            Credentials.API_KEY,
//                            "Woman",
//                            1);
//            responseCall.enqueue(new Callback<MovieSearchResponse>() {
//                @Override
//                public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//                    if (response.code() == 200) {
//                        Log.e("TAG", "onResponse:" + response.body().toString());
//                        List<MovieModel> movieModelList = new ArrayList<>(response.body()
//                                .getMovies());
//
//                        for (MovieModel movieModel : movieModelList) {
//                            Log.v("Tag", "The realease date" + movieModel.getRelease_date());
//                        }
//                    } else {
//                        try {
//                            Log.v("Tag", "Error" + response.errorBody().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
//                    Log.e(TAG, "onFailure: ", t.getCause());
//                }
//            });
//        } catch (Exception e) {
//            Log.e(TAG, "GetRetrofitResponse: " + e.getMessage());
//        }
//    }
//
//    private void getRetrofitResponseAccoringToID() {
//        MovieApi movieApi = MovieServices.getMovieApi();
//        Call<MovieModel> responseCall = movieApi.getMovie(
//                581389,
//                Credentials.API_KEY
//        );
//
//        responseCall.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
//                if (response.code() == 200) {
//                    MovieModel movieModel = response.body();
//                    Log.d(TAG, "onResponse: " + movieModel.getTitle());
//                } else {
//                    try {
//                        Log.d(TAG, "onResponse: " + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//                Log.e(TAG, "onFailure: ");
//            }
//        });
//    }

}