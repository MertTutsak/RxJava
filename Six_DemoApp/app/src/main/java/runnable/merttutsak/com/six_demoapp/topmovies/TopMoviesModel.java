package runnable.merttutsak.com.six_demoapp.topmovies;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import runnable.merttutsak.com.six_demoapp.http.apimodel.Result;

public class TopMoviesModel implements TopMoviesMVP.Model {

    private Repository repository;

    public TopMoviesModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<ViewModel> result() {
        return Observable.zip(
                repository.getResultData(),
                repository.getCountryData(),
                new BiFunction<Result, String, ViewModel>() {
                    @Override
                    public ViewModel apply(Result result, String s) throws Exception {
                        return new ViewModel(result.title, s);
                    }
                });
    }
}
