package runnable.merttutsak.com.eight_diffutil.topmovies;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import runnable.merttutsak.com.eight_diffutil.http.MovieApiService;

public class TopMoviesPresenter implements TopMoviesMVP.Presenter {
    private final String TAG = TopMoviesPresenter.class.getName();

    private TopMoviesMVP.View view;
    private MovieApiService movieApiService;

    private CompositeDisposable subscription = null;

    public TopMoviesPresenter(MovieApiService movieApiService) {
        this.movieApiService = movieApiService;
        subscription = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        subscription.clear();
    }

    @Override
    public void attachView(TopMoviesMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        if (view != null) {
            view.setLoading(true);
        }
        subscription.add(movieApiService.getTopRatedMovies(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(() -> {
                    if (view != null) {
                        view.setLoading(false);
                    }
                }).subscribe(
                        topRated -> {
                            if (view != null) {
                                view.updateData(topRated.results);
                            }
                        }, error -> {
                            if (view != null) {
                                view.showSnackBar(error.getLocalizedMessage());
                            }
                        }));
    }
}
