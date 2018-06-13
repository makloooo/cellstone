package makloooo.cellstone;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import makloooo.cellstone.CharacterLoreFragment.OnEntrySelectedListener;
import makloooo.cellstone.CharacterPagerFragment.OnProfileInteractionListener;

public class CharacterLoreAdapter extends RecyclerView.Adapter<CharacterLoreAdapter.ViewHolder> {

    private static final String TAG = "CharacterLoreAdapter";

    private OnEntrySelectedListener mListener;

    // These are pairs
    private final ArrayList<String> mTitles;
    private final ArrayList<String> mMaterial;

    CharacterLoreAdapter(OnEntrySelectedListener listener,
                         ArrayList<String> titles, ArrayList<String> material) {
        mListener = listener;

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
                int i = holder.getAdapterPosition();
                mListener.displayMaterial(mTitles.get(i), mMaterial.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
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
