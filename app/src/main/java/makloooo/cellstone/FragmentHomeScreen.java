package makloooo.cellstone;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentHomeScreen.OnHomeScreenInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentHomeScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHomeScreen extends Fragment {

    private static final String TAG = "FragmentHomeScreen";

    private OnHomeScreenInteractionListener mListener;

    ImageView mCharacterPortrait;
    View mContainer;

    public FragmentHomeScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHomeScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHomeScreen newInstance(String param1, String param2) {
        FragmentHomeScreen fragment = new FragmentHomeScreen();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Create View");
        // populate list
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        ListView factionList = view.findViewById(R.id.faction_list_listView);
        String[] factionArray = getResources().getStringArray(R.array.faction_names);
        factionList.setAdapter(new AdapterFactionMenu(getActivity(), factionArray));

        mCharacterPortrait = view.findViewById(R.id.character_portrait);

        view.post(new LoadMainPortraitRunnable(mCharacterPortrait,
                R.drawable.sample_gawain,
                this));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
                    + " must implement OnFragmentInteractionListener");
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
        // TODO: Update argument type and name
        public void onFactionSelected(int position);
    }
}
