package com.example.aliouswang.movie_binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.aliouswang.movie_binder.bean.Movie;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MovieService extends Service {

    private CopyOnWriteArrayList<IMovieHotChangeListener> mIMovieHotChangeListeners =
            new CopyOnWriteArrayList<>();

    private IMovieInterface.Stub mIMovieInterface = new IMovieInterface.Stub() {
        @Override
        public List<Movie> queryMovie(int pageSize) throws RemoteException {
            return mMovieManager.getMovieList();
        }

        @Override
        public void star(Movie movie) throws RemoteException {
            mMovieManager.star(movie);

            notifyChange(movie);
        }

        @Override
        public void registerObserver(IMovieHotChangeListener listener) throws RemoteException {
            mIMovieHotChangeListeners.add(listener);
        }

        @Override
        public void unRegisterObserver(IMovieHotChangeListener listener) throws RemoteException {

        }
    };

    private void notifyChange(Movie movie) {
        for (IMovieHotChangeListener listener : mIMovieHotChangeListeners) {
            try {
                listener.onHotChange(movie);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private MovieManager mMovieManager;
    @Override
    public void onCreate() {
        super.onCreate();
        mMovieManager = MovieManager.getInstance();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return  mIMovieInterface;
//        return new MovieBinder();
    }

    public class MovieBinder extends Binder {

    }
}
