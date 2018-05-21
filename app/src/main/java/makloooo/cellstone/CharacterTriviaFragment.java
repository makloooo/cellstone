package makloooo.cellstone;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CharacterTriviaFragment extends Fragment {

    private static final String TRIVIA = "Character Trivia";

    //private OnProfileInteractionListener mListener;

    private ArrayList<String> mEntries;

    public CharacterTriviaFragment() {
        // Required empty public constructor
    }

    public static CharacterTriviaFragment newInstance(ArrayList<String> trivia) {
        Log.d("CharacterTriviaFragment", "created new CharacterTriviaFragment");
        CharacterTriviaFragment fragment = new CharacterTriviaFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(TRIVIA, trivia);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEntries = getArguments().getStringArrayList(TRIVIA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_trivia, container, false);
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
