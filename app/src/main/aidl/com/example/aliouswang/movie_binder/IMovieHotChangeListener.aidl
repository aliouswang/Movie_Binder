// IMovieHotChangeListener.aidl
package com.example.aliouswang.movie_binder;

// Declare any non-default types here with import statements

import com.example.aliouswang.movie_binder.bean.Movie;

interface IMovieHotChangeListener {

    void onHotChange(in Movie movie);
    void resort(out List<Movie> movieList);

}
