package com.example.aliouswang.movie_binder;

import com.example.aliouswang.movie_binder.bean.Movie;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MovieManager {

    private static volatile MovieManager instance;

    public static MovieManager getInstance() {
        if (instance == null) {
            synchronized (MovieManager.class) {
                if (instance == null) {
                    instance = new MovieManager();
                }
            }
        }
        return instance;
    }

    private CopyOnWriteArrayList<Movie> movieList;

    private MovieManager() {
        initMovieList();
    }

    private void initMovieList() {
        movieList = new CopyOnWriteArrayList<>();
        movieList.addAll(MovieMock.mockMovies());
    }

    public List<Movie> getMovieList() {
        return this.movieList;
    }

    public void star(Movie movie) {
        int index = movieList.indexOf(movie);
        movieList.get(index).hot = movie.hot;
    }

}
