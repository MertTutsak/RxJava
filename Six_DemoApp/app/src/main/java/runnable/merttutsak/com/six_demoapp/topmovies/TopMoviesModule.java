package runnable.merttutsak.com.six_demoapp.topmovies;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import runnable.merttutsak.com.six_demoapp.http.MoreInfoApiService;
import runnable.merttutsak.com.six_demoapp.http.MovieApiService;

@Module
public class TopMoviesModule {

    @Provides
    public TopMoviesMVP.Presenter provideTopMoviesPresenter(TopMoviesMVP.Model topMoviesModel) {
        return new TopMoviesPresenter(topMoviesModel);
    }

    @Provides
    public TopMoviesMVP.Model provideTopMoviesModel(Repository repository) {
        return new TopMoviesModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepository(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService) {
        return new TopMoviesRepository(movieApiService, moreInfoApiService);
    }
}
