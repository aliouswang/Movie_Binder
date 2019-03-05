// IMovieInterface.aidl
package com.example.aliouswang.movie_binder;

// Declare any non-default types here with import statements

import com.example.aliouswang.movie_binder.bean.Movie;
import com.example.aliouswang.movie_binder.IMovieHotChangeListener;

interface IMovieInterface {

    List<Movie> queryMovie(int pageSize);
    void star(in Movie movie);
    void registerObserver(IMovieHotChangeListener listener);
    void unRegisterObserver(IMovieHotChangeListener listener);

}
