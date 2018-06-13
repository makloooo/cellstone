package makloooo.cellstone;

import android.arch.persistence.room.Database;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

public class CharacterSpeculationFragment extends Fragment {

    private static final String SPECULATION = "Character Speculation";

    //private OnProfileInteractionListener mListener;

    private ArrayList<String> mEntries;

    public CharacterSpeculationFragment() {
        // Required empty public constructor
    }

    public static CharacterSpeculationFragment newInstance(ArrayList<String> speculation) {
        Log.d("CharacterSpeculationFragment", "created new CharacterSpeculationFragment");
        CharacterSpeculationFragment fragment = new CharacterSpeculationFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(SPECULATION, speculation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEntries = getArguments().getStringArrayList(SPECULATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_speculations, container, false);

        LinearLayout speculationList = view.findViewById(R.id.characterSpeculations_entries);

        // Dynamically construct relative layouts for each entry
        LinearLayout bulletPoint;
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //llp.gravity = Gravity.CENTER_VERTICAL;

        ImageView entryBullet;
        TextView entryText;

        for (String entry : mEntries) {
            bulletPoint = new LinearLayout(getContext());
            bulletPoint.setLayoutParams(llp);
            bulletPoint.setOrientation(LinearLayout.HORIZONTAL);

            entryBullet = new ImageView(getContext());
            entryBullet.setPadding(8,8,8,8);
            entryBullet.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);

            entryText = new TextView(getContext());
            entryText.setPadding(8,8,8,8);
            entryText.setText(entry);

            bulletPoint.addView(entryBullet, 0, llp);
            bulletPoint.addView(entryText,1, llp);

            speculationList.addView(bulletPoint);
        }

        return view;
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
