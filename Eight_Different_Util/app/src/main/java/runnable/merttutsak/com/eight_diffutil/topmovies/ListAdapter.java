package runnable.merttutsak.com.eight_diffutil.topmovies;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import runnable.merttutsak.com.eight_diffutil.R;
import runnable.merttutsak.com.eight_diffutil.helper.MyDiffUtil;
import runnable.merttutsak.com.eight_diffutil.http.apimodel.Result;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder> {

    private List<Result> list;

    public ListAdapter(List<Result> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        holder.itemName.setText(list.get(position).title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(List<Result> newList) {
        MyDiffUtil diffUtil = new MyDiffUtil(list, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtil);
        list.clear();
        list.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.movieName);
        }
    }
}
