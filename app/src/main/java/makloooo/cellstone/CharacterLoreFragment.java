package makloooo.cellstone;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CharacterLoreFragment extends Fragment {

    private static final String LORE_TITLES = "Entry Titles";
    private static final String LORE_MATERIAL = "Entry Material";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CharacterLoreFragment() {
    }

    public static CharacterLoreFragment newInstance(ArrayList<String> titles, ArrayList<String> material) {
        CharacterLoreFragment fragment = new CharacterLoreFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(LORE_TITLES, titles);
        args.putStringArrayList(LORE_MATERIAL, material);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_lore, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(
                    new CharacterLoreAdapter(
                            getArguments().getStringArrayList(LORE_TITLES),
                            getArguments().getStringArrayList(LORE_MATERIAL)));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }
}
