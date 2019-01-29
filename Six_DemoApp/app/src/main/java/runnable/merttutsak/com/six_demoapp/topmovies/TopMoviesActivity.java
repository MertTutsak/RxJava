package runnable.merttutsak.com.six_demoapp.topmovies;

import android.support.design.widget.Snackbar;
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
import runnable.merttutsak.com.six_demoapp.R;
import runnable.merttutsak.com.six_demoapp.root.App;

public class TopMoviesActivity extends AppCompatActivity implements TopMoviesMVP.View {
    private final String TAG = TopMoviesActivity.class.getName();

    @BindView(R.id.listActivity_rootView)
    ViewGroup rootView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    TopMoviesMVP.Presenter presenter;

    private ListAdapter listAdapter;
    private List<ViewModel> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies);

        ((App) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        listAdapter = new ListAdapter(resultList);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void updateData(ViewModel viewModel) {
        resultList.add(viewModel);

        if (resultList.isEmpty()) {
            listAdapter.notifyItemInserted(0);
        } else {
            listAdapter.notifyItemInserted(resultList.size() - 1);
        }

        Log.d(TAG, "updateData: size() -> " + resultList.size());
    }

    @Override
    public void showSnackBar(String s) {
        Snackbar.make(rootView, s, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.rxUnsubscribe();
    }
}
