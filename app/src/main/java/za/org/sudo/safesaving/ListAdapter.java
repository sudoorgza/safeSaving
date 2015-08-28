package za.org.sudo.safesaving;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by hendrikschalekamp on 15/08/27.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ActionModel[] mDataset;

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


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mWordView.setText(mDataset[position].getWord());
        holder.mLetterView.setText(mDataset[position].getLetter());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
