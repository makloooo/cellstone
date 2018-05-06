package makloooo.cellstone;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeScreenFragment.OnHomeScreenInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeScreenFragment extends Fragment {

    private static final String TAG = "HomeScreenFragment";

    private static final String FACTION_NAMES  = "FactionNames";

    private OnHomeScreenInteractionListener mListener;

    ArrayList<String> mFactionList;
    ImageView mCharacterPortrait;
    View mContainer;

    public HomeScreenFragment() {
        // Required empty public constructor
    }

    /** Creates a new fragment via factory method.
     *  Arguments are passed into this method, and stored in a bundle.
     *  These bundles are then restored on the onCreate method, storing
     *  them into actual member variables when created. */
    public static HomeScreenFragment newInstance(ArrayList<String> factionNames) {
        HomeScreenFragment fragment = new HomeScreenFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(FACTION_NAMES, factionNames);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFactionList = getArguments().getStringArrayList(FACTION_NAMES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // populate list
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        ListView factionList = view.findViewById(R.id.faction_list_listView);
        //String[] factionArray = getResources().getStringArray(R.array.faction_names);
        factionList.setAdapter(new HomeScreenAdapter(getActivity(), mFactionList));
        factionList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onButtonPressed(position);
            }
        });

        mCharacterPortrait = view.findViewById(R.id.character_portrait);

        view.post(new LoadMainPortraitRunnable(mCharacterPortrait,
                R.drawable.sample_gawain,
                this));

        return view;
    }

    public void onButtonPressed(int position) {
        if (mListener != null) {
            mListener.onFactionSelected(position);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeScreenInteractionListener) {
            mListener = (OnHomeScreenInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnHomeScreenInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnHomeScreenInteractionListener {
        public void onFactionSelected(int position);
    }
}
