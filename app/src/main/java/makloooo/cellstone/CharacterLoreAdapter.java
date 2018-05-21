package makloooo.cellstone;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CharacterLoreAdapter extends RecyclerView.Adapter<CharacterLoreAdapter.ViewHolder> {

    private static final String TAG = "CharacterLoreAdapter";

    // These are pairs
    private final ArrayList<String> mTitles;
    private final ArrayList<String> mMaterial;

    CharacterLoreAdapter(ArrayList<String> titles, ArrayList<String> material) {
        mTitles = titles;
        mMaterial = material;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menuitem_character_lore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitle.setText(mTitles.get(position));

        // Truncating to a single line in the preview
        holder.mView.post(new Runnable() {
            @Override
            public void run() {
                holder.mPreview.setMaxWidth(holder.mView.getWidth());
                holder.mPreview.setSingleLine();
                holder.mPreview.setText(mMaterial.get(position));
                holder.mPreview.setEllipsize(TruncateAt.END);
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Dim screen, display entire material over it
                /* I propose, we simply have a view that is gone at first
                 * in this activity, and update it with the material text
                 * before setting it to visible. The view have a fade in
                 * transition after updating. Once it is loaded in, it will
                 * be scrollable, and when tapped, it will transition fade
                 * out. Once animation is done, it will be set to gone again. */
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    private int calculateMaxLength(ViewHolder holder) {
        Log.d(TAG, "holder.mView.getWidth() = " + holder.mView.getWidth());
        Log.d(TAG, "holder.mPreview.getTextSize() = " + holder.mPreview.getTextSize());
        Log.d(TAG, "result = " + holder.mView.getWidth()/holder.mPreview.getTextSize());

        return (int)(holder.mView.getWidth()/holder.mPreview.getTextSize());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView mTitle;
        public final TextView mPreview;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.title);
            mPreview = (TextView) view.findViewById(R.id.preview);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}
