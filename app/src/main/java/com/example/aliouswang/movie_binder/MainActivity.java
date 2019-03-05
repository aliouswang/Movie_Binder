package com.example.aliouswang.movie_binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aliouswang.movie_binder.bean.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler_view;

    private IMovieInterface mIMovieInterface;
    private IMovieHotChangeListener mIMovieHotChangeListener = new IMovieHotChangeListener.Stub() {
        @Override
        public void onHotChange(Movie movie) throws RemoteException {
            if (mMovies != null) {
                int index = mMovies.indexOf(movie);
                if (index >= 0) {
                    mMovies.get(index).hot = movie.hot;
                    mMovieAdapter.notifyItemChanged(index);
                }
            }
        }

        @Override
        public void resort(List<Movie> movieList) throws RemoteException {

        }
    };
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            if (service != null) {

            }

            mIMovieInterface = IMovieInterface.Stub.asInterface(service);
            try {
                mIMovieInterface.registerObserver(mIMovieHotChangeListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            refreshMovieList();

            Log.e("service_tag", "service connected!");
            Log.e("service_tag", "cur thread:" + Thread.currentThread());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIMovieInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        Intent intent = new Intent(this, MovieService.class);
        boolean bindResult = bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
        if (bindResult) {

        }
    }

    private MovieAdapter mMovieAdapter;
    private void initView() {
        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new GridLayoutManager(this, 2));

        mMovieAdapter = new MovieAdapter();
        recycler_view.setAdapter(mMovieAdapter);
    }

    private List<Movie> mMovies = new ArrayList<>();
    private void refreshMovieList() {
        try {
            mMovies = mIMovieInterface.queryMovie(10);
            mMovieAdapter.notifyDataSetChanged();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private class MovieViewHolder extends RecyclerView.ViewHolder {

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(final Movie movie) {
            ImageView imageView = itemView.findViewById(R.id.img_movie);
            Glide.with(imageView.getContext()).load(movie.image).into(imageView);
            TextView tvName = itemView.findViewById(R.id.tv_name);
            TextView tvHot = itemView.findViewById(R.id.tv_hot);
            tvName.setText(movie.name);
            tvHot.setText("热度:" + movie.hot);

            Button btn_star = itemView.findViewById(R.id.btn_star);
            btn_star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        movie.hot += 1;
                        mIMovieInterface.star(movie);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    private class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

        @NonNull
        @Override
        public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MovieViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.item_movie, viewGroup, false
            ));
        }

        @Override
        public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
            movieViewHolder.bind(mMovies.get(i));
        }

        @Override
        public int getItemCount() {
            return mMovies.size();
        }
    }

}
