package runnable.merttutsak.com.six_demoapp.topmovies;

import io.reactivex.Observable;

public interface TopMoviesMVP {

    interface View {

        void updateData(ViewModel viewModel);

        void showSnackBar(String s);
    }

    interface Presenter {

        void loadData();

        void rxUnsubscribe();

        void setView(TopMoviesMVP.View view);
    }

    interface Model {

        Observable<ViewModel> result();

    }
}
