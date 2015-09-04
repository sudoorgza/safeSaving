package za.org.sudo.safesaving;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hendrikschalekamp on 15/08/27.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ActionModel[] mDataset;
    private Toast mToast;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mLetterView;
        public TextView mWordView;
        public LinearLayout mLayout;
        public ViewHolder(LinearLayout layout, TextView letterView, TextView wordView) {
            super(layout);
            mLayout = layout;
            mWordView = wordView;
            mLetterView = letterView;
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(ActionModel[] myDataset) {
        mDataset = myDataset;
        if (mDataset.length >0 ) {
            mDataset[0].setHidden(false);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        //create a new view
        LinearLayout layout = (LinearLayout)LayoutInflater.from(parent.getContext())
        .inflate(R.layout.recycler_layout, parent, false);
        TextView wordView = (TextView) layout.findViewById(R.id.recycler_word_view);
        TextView letterView = (TextView) layout.findViewById(R.id.recycler_letter_view);

        return new ViewHolder(layout, letterView, wordView);
    }

    public void notifyItemClicked(Context context, int position) {
        if (mToast != null) {
            mToast.cancel();
        }
        if ((position + 1) < mDataset.length) {
            if (mDataset[position+1].isHidden()) {
                mDataset[position + 1].setHidden(false);
                notifyItemInserted(position+1);
                mToast = Toast.makeText(context, mDataset[position+1].getDescription(), Toast.LENGTH_LONG);
            } else {
                mToast = Toast.makeText(context, mDataset[position].getDescription(), Toast.LENGTH_LONG);
            }
        } else {
            mToast = Toast.makeText(context, mDataset[position].getDescription(), Toast.LENGTH_LONG);
        }
        mToast.show();
        //notifyDataSetChanged();
    }

    public void resetItems() {
        if (mDataset.length > 0 ) {
            mDataset[0].setHidden(false);
            for (int index = 1; index < mDataset.length; index++) {
                mDataset[index].setHidden(true);
            }
            if (mDataset.length > 1 ) {
                notifyItemRangeRemoved(1, mDataset.length - 1);
            }
        }

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mWordView.setText(mDataset[position].getWord());
        holder.mLetterView.setText(mDataset[position].getLetter());
        Resources res = holder.mLayout.getContext().getResources();
        switch (mDataset[position].getLevel()) {
            case 0:
                break;
            case 1:
                holder.mLayout.setBackgroundColor(res.getColor(R.color.primary_light));
                break;
            case 2:
                holder.mLayout.setBackgroundColor(res.getColor(R.color.primary_dark));
                holder.mLetterView.setTextColor(res.getColor(R.color.primary_light));
                holder.mWordView.setTextColor(res.getColor(R.color.background));
                break;
            default:
                break;
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        for (int index = 0 ; index < mDataset.length; index++) {
            if (mDataset[index].isHidden()) {
                return index;
            }
        }
        return mDataset.length;
    }
}
