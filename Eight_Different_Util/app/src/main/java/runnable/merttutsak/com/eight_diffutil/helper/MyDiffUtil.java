package runnable.merttutsak.com.eight_diffutil.helper;

import android.support.v7.util.DiffUtil;

import java.util.List;

import runnable.merttutsak.com.eight_diffutil.http.apimodel.Result;

public class MyDiffUtil extends DiffUtil.Callback {

    private List<Result> oldList;
    private List<Result> newList;

    public MyDiffUtil(List<Result> oldList, List<Result> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldListPosition, int newListPosition) {
        return oldList.get(oldListPosition).id == newList.get(newListPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldListPosition, int newListPosition) {
        return oldList.get(oldListPosition).equals(newList.get(newListPosition));
    }
}
