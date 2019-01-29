package runnable.merttutsak.com.eight_diffutil.topmovies;

import java.util.List;

import runnable.merttutsak.com.eight_diffutil.http.apimodel.Result;

public interface TopMoviesMVP {

    interface View {

        void updateData(List<Result> results);

        void showSnackBar(String s);

        void setLoading(boolean isLoading);
    }

    interface Presenter {

        void loadData();

        void detachView();

        void attachView(TopMoviesMVP.View view);
    }
}
