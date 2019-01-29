package runnable.merttutsak.com.six_demoapp.topmovies;

import io.reactivex.Observable;
import runnable.merttutsak.com.six_demoapp.http.apimodel.Result;

public interface Repository {


    Observable<Result> getResultsFromMemory();

    Observable<Result> getResultsFromNetwork();

    Observable<String> getCountriesFromMemory();

    Observable<String> getCountriesFromNetwork();

    Observable<String> getCountryData();

    Observable<Result> getResultData();
}
