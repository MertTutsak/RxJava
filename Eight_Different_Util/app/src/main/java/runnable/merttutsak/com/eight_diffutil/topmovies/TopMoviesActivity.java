package runnable.merttutsak.com.eight_diffutil.topmovies;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import runnable.merttutsak.com.eight_diffutil.R;
import runnable.merttutsak.com.eight_diffutil.helper.DividerItemDecoration;
import runnable.merttutsak.com.eight_diffutil.http.apimodel.Result;
import runnable.merttutsak.com.eight_diffutil.root.App;

public class TopMoviesActivity extends AppCompatActivity implements TopMoviesMVP.View {
    private final String TAG = TopMoviesActivity.class.getName();

    @BindView(R.id.rootView)
    ViewGroup rootView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    TopMoviesMVP.Presenter presenter;

    private ListAdapter listAdapter;
    private List<Result> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies);

        ((App) getApplication()).getComponent().inject(this);
        ButterKnife.bind(this);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            Log.d(TAG, "onRefresh");
            presenter.loadData();
        });

        listAdapter = new ListAdapter(resultList);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter.attachView(this);
        presenter.loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        resultList.clear();
        presenter.detachView();
    }

    @Override
    public void updateData(List<Result> results) {
        listAdapter.updateData(results);

        Log.d(TAG, "updateData: size() -> " + resultList.size());
    }

    @Override
    public void showSnackBar(String s) {
        Snackbar.make(rootView, s, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setLoading(boolean isLoading) {
        swipeRefreshLayout.setRefreshing(isLoading);
    }
}
