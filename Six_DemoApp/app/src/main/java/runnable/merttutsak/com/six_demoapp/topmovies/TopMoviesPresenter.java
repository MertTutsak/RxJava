package runnable.merttutsak.com.six_demoapp.topmovies;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import io.reactivex.observers.DisposableObserver;

public class TopMoviesPresenter implements TopMoviesMVP.Presenter {
    private final String TAG = TopMoviesPresenter.class.getName();

    private TopMoviesMVP.View view;
    private TopMoviesMVP.Model model;

    private Disposable subscription = null;

    public TopMoviesPresenter(TopMoviesMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {
        subscription = model
                .result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ViewModel>() {
                    @Override
                    public void onNext(ViewModel model) {
                        if (view != null) {
                            view.updateData(model);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (view != null) {
                            view.showSnackBar("Error !");
                            Log.e(TAG,"Error -> " + e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription.isDisposed()) {
                subscription.dispose();
            }
        }
    }

    @Override
    public void setView(TopMoviesMVP.View view) {
        this.view = view;
    }
}
