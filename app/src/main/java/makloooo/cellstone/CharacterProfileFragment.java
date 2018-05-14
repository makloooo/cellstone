package makloooo.cellstone;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CharacterProfileFragment.OnProfileInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CharacterProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharacterProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    private OnProfileInteractionListener mListener;

    public CharacterProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CharacterProfileFragment newInstance() {
        CharacterProfileFragment fragment = new CharacterProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_profile, container, false);

        Character character = mListener.fetchCharacter();

        /* Populate the view with the character info */

        ImageView portrait = view.findViewById(R.id.faction_member_portrait);

        // TODO: make it load the last character selected portrait using bundle, default 0
        view.post(new LoadMainPortraitRunnable(portrait,
                character.getPortraitId(),
                this));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProfileInteractionListener) {
            mListener = (OnProfileInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnProfileInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnProfileInteractionListener {
        Character fetchCharacter();
        void loadPortrait(Fragment fragment, ImageView imageView, int resId);
    }
}
