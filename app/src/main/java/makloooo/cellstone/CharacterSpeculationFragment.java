package makloooo.cellstone;

import android.arch.persistence.room.Database;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CharacterSpeculationFragment extends Fragment {

    private static final String SPECULATION = "Character Speculation";
    private static final String CORRECTIONS = "Character Speculation Corrections";

    //private OnProfileInteractionListener mListener;

    private LinearLayout mSpeculationListLayout;

    private ArrayList<String> mEntries;
    private HashMap<Integer, String> mCorrections;

    public CharacterSpeculationFragment() {
        // Required empty public constructor
    }

    public static CharacterSpeculationFragment newInstance(ArrayList<String> speculation,
                                                        HashMap<Integer, String> correction) {
        Log.d("CharacterSpeculationFragment", "created new CharacterSpeculationFragment");
        CharacterSpeculationFragment fragment = new CharacterSpeculationFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(SPECULATION, speculation);
        args.putSerializable(CORRECTIONS, correction);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEntries = getArguments().getStringArrayList(SPECULATION);
            mCorrections = (HashMap<Integer, String>)getArguments().getSerializable(CORRECTIONS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_speculations, container, false);

        mSpeculationListLayout = view.findViewById(R.id.characterSpeculations_entries);

        for (int i = 0; i < mEntries.size(); ++i) {

            TextView entryText = generateBulletPoint(R.drawable.ic_keyboard_arrow_right_black_24dp,
                    mEntries.get(i));

            // if there's a correction to this point
            if (mCorrections.containsKey(i)) {
                // strikeout original text
                entryText.setPaintFlags(entryText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                // place correction under original text
                generateBulletPoint(0, mCorrections.get(i));
            }
        }

        return view;
    }

    // returns the textview
    private TextView generateBulletPoint(int bpResId, String text) {
        LinearLayout bulletPoint = new LinearLayout(getContext());
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bulletPoint.setLayoutParams(llp);
        bulletPoint.setOrientation(LinearLayout.HORIZONTAL);

        ImageView entryBullet = new ImageView(getContext());
        if (bpResId != 0) {
            entryBullet.setPadding(8, 8, 8, 8);
            entryBullet.setImageResource(bpResId);
        }
        // keep correction closer to original text
        else entryBullet.setPadding(8,0,8,8);

        TextView entryText = new TextView(getContext());
        entryText.setPadding(8,8,8,8);
        entryText.setText(text);

        bulletPoint.addView(entryBullet, 0, llp);
        bulletPoint.addView(entryText,1, llp);

        mSpeculationListLayout.addView(bulletPoint);

        return entryText;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnProfileInteractionListener) {
            mListener = (OnProfileInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnProfileInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /*public interface OnProfileInteractionListener {
        Character fetchCharacter();
    }*/
}
