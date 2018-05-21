package makloooo.cellstone;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CharacterPagerFragment.OnProfileInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CharacterPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharacterPagerFragment extends Fragment {

    private static final String TAG = "CharacterPagerFragment";

    private OnProfileInteractionListener mListener;

    public CharacterPagerFragment() {
        // Required empty public constructor
    }

    public static CharacterPagerFragment newInstance() {
        CharacterPagerFragment fragment = new CharacterPagerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // empty for now
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_pager, container, false);

        ViewPager pager = view.findViewById(R.id.character_info_pager);

        pager.setAdapter(new CharacterPagerAdapter(
                mListener.fetchFragmentManager(),
                mListener.fetchCharacter()));

        SlidingTabLayout tabStrip = view.findViewById(R.id.character_info_tabs);
        tabStrip.setDistributeEvenly(true);
        tabStrip.setViewPager(pager);

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
        FragmentManager fetchFragmentManager();
    }
}
