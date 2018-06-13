package makloooo.cellstone;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.CropTransformation.CropType;

public class FactionMatrixFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ROSTER = "FactionRoster";
    private static final String LAST_SELECTED = "LastSelectedCharacter";
    private static final String TAG = "FactionMatrixFragment";

    private int mSelectIndex;

    private OnMatrixInteractionListener mListener;

    private ArrayList<String> mRoster;

    public FactionMatrixFragment() {
        // Required empty public constructor
    }

    public static FactionMatrixFragment newInstance(ArrayList<String> factionRoster) {
        FactionMatrixFragment fragment = new FactionMatrixFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ROSTER, factionRoster);
        args.putInt(LAST_SELECTED, -1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // load faction data depending on string
            mRoster = getArguments().getStringArrayList(ROSTER);
            mSelectIndex = getArguments().getInt(LAST_SELECTED);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faction_matrix,
                container, false);

        // inflate the left faction matrix
        final ListView factionMatrix = view.findViewById(R.id.faction_matrix_list);
        factionMatrix.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        // mPortrait = view.findViewById(R.id.faction_member_portrait);

        //String[] memberArray = getResources().getStringArray(R.array.adventurer_names);
        factionMatrix.setAdapter(new FactionMatrixAdapter(getActivity(), mRoster));
        factionMatrix.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mSelectIndex != position) onButtonPressed(position); // "focus" character
                else onButtonDoublePressed(position); // select character
            }
        });

        /* load the first character's portrait */
        // TODO: make it load the last character selected portrait using bundle, default 0
        onButtonPressed(0);

        return view;
    }

    // Focus and change portrait
    public int onButtonPressed(int position) {
        Log.d(TAG, "Single Tap on " + position);
        mSelectIndex = position;
        if (mListener != null) {
            int resId = mListener.fetchPortrait(position);
            mListener.loadImage(resId);
        }
        return 0;
    }

    // Go into character profile
    public void onButtonDoublePressed(int position) {
        Log.d(TAG, "Double Tap on " + position);
        if (mListener != null) {
            mListener.onCharacterSelected(position);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMatrixInteractionListener) {
            mListener = (OnMatrixInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMatrixInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMatrixInteractionListener {
        void onCharacterSelected(int position);
        int fetchPortrait(int position); // returns resId
        void loadImage(int resId);
    }
}
