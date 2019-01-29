package runnable.merttutsak.com.eight_diffutil.topmovies;

import dagger.Module;
import dagger.Provides;

import runnable.merttutsak.com.eight_diffutil.http.MovieApiService;

@Module
public class TopMoviesModule {

    @Provides
    public TopMoviesMVP.Presenter provideTopMoviesPresenter(MovieApiService movieApiService) {
        return new TopMoviesPresenter(movieApiService);
    }
}
