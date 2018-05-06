package makloooo.cellstone;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class FactionMatrixFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ROSTER = "FactionRoster";

    private ImageView mPortrait;

    private OnMatrixInteractionListener mListener;

    private ArrayList<String> mRoster;

    public FactionMatrixFragment() {
        // Required empty public constructor
    }

    public static FactionMatrixFragment newInstance(ArrayList<String> factionRoster) {
        FactionMatrixFragment fragment = new FactionMatrixFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ROSTER, factionRoster);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // load faction data depending on string
            mRoster = getArguments().getStringArrayList(ROSTER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faction_matrix,
                container, false);

        // inflate the left faction matrix
        ListView factionMatrix = view.findViewById(R.id.faction_matrix_list);

        mPortrait = view.findViewById(R.id.faction_member_portrait);

        //String[] memberArray = getResources().getStringArray(R.array.adventurer_names);
        factionMatrix.setAdapter(new FactionMatrixAdapter(getActivity(), mRoster));
        factionMatrix.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!view.isFocused()) {
                    onButtonPressed(position); // "focus" character
                    view.requestFocus();
                }
                else onButtonDoublePressed(position); // select character
            }
        });

        // load the first character's portrait
        // TODO: make it load the last character selected portrait using bundle, default 0
        view.post(new LoadMainPortraitRunnable(mPortrait,
                onButtonPressed(0),
                this));

        return view;
    }

    // Focus and change portrait
    public int onButtonPressed(int position) {
        if (mListener != null) {
            int resId = mListener.fetchPortrait(position);
            // TODO: Transition fade out old, fade out in
            new LoadMainPortraitRunnable(mPortrait,
                    onButtonPressed(0),
                    this);
            return resId;
        }
        return 0;
    }

    // Go into character profile
    public void onButtonDoublePressed(int position) {
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
    }
}
