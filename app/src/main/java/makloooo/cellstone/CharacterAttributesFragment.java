package makloooo.cellstone;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

public class CharacterAttributesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";
    private static final String ATTRIBUTES = "Character Attributes";

    // TODO: Rename and change types of parameters
    private HashMap<String, String> mAttributes;

    public CharacterAttributesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CharacterAttributesFragment newInstance(HashMap<String, String> attr) {
        Log.d("CharacterAttributesFragment", "created new CharacterAttributesFragment");
        CharacterAttributesFragment fragment = new CharacterAttributesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ATTRIBUTES, attr);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAttributes = (HashMap)getArguments().getSerializable(ATTRIBUTES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_attributes, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
