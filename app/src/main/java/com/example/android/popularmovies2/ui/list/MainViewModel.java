/*
 * Copyright (c) 2020. This project was submitted by Tarek Bohdima as part of the Android Developer Nanodegree At Udacity.
 * As part of Udacity Honor code, your submissions must be your own work, hence submitting this project as yours will cause you to break the Udacity Honor Code and the suspension of your account.
 * Me, the author of the project, allow you to check the code as a reference, but if you submit it, it's your own responsibility if you get expelled.
 */

package com.example.android.popularmovies2.ui.list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.popularmovies2.Constants;
import com.example.android.popularmovies2.MoviesApp;
import com.example.android.popularmovies2.data.AppRepository;
import com.example.android.popularmovies2.data.model.Movie;

import java.util.List;

import timber.log.Timber;

public class MainViewModel extends AndroidViewModel {

    private final LiveData<List<Movie>> popularMoviesLiveData;
    private final LiveData<List<Movie>> topRatedMoviesLiveData;
    private final LiveData<List<Movie>> favoriteMoviesLiveData;
    private final AppRepository appRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        appRepository = ((MoviesApp) getApplication()).getMovieComponent().getAppRepository();
        Timber.tag(Constants.TAG).d("MainViewModel: get AppRepository instance");
        popularMoviesLiveData = appRepository.getPopularMovies();
        topRatedMoviesLiveData = appRepository.getTopRatedMovies();
        favoriteMoviesLiveData = appRepository.getAllFavoriteMovies();
    }

    public LiveData<List<Movie>> getPopularMovies() {
        Timber.tag(Constants.TAG).d("MainViewModel: getPopularMovies() called");
        return popularMoviesLiveData;
    }

    public LiveData<List<Movie>> getTopRatedMovies() {
        Timber.tag(Constants.TAG).d("MainViewModel: getTopRatedMovies() called");
        return topRatedMoviesLiveData;
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        Timber.tag(Constants.TAG).d("MainViewModel: getFavoriteMovies() called");
        return favoriteMoviesLiveData;
    }

    public void deleteAllFavoriteMovies() {
        Timber.tag(Constants.TAG).d("MainViewModel: deleteAllFavoriteMovies() called");
        appRepository.deleteAllFavoriteMovies();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        appRepository.clearDisposables();
        Timber.tag(Constants.TAG).d("MainViewModel: onCleared() called");
    }
}
