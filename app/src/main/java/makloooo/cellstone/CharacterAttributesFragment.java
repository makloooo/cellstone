package makloooo.cellstone;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

public class CharacterAttributesFragment extends Fragment {
    private static final String ATTRIBUTES = "Character Attributes";
    private static final String STATS = "Character Stats";

    private HashMap<String, String> mAttributes;
    private HashMap<String, String> mStats;

    public CharacterAttributesFragment() {
        // Required empty public constructor
    }

    public static CharacterAttributesFragment newInstance(HashMap<String, String> attr, HashMap<String, String> stats) {
        Log.d("CharacterAttributesFragment", "created new CharacterAttributesFragment");
        CharacterAttributesFragment fragment = new CharacterAttributesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ATTRIBUTES, attr);
        args.putSerializable(STATS, stats);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAttributes = (HashMap)getArguments().getSerializable(ATTRIBUTES);
            mStats = (HashMap)getArguments().getSerializable(STATS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_attributes, container, false);

        // Setting attribute info
        int resIds[] = {R.id.characterAttribute_name, R.id.characterAttribute_title,
                        R.id.characterAttribute_heightWeight,
                        R.id.characterAttribute_raceSpecialization,
                        R.id.characterAttribute_misc, R.id.characterAttribute_stats,
                        R.id.characterAttribute_notes};
        String keys[] = {"NAME", "TITLE", "HEIGHT/WEIGHT", "RACE/SPECIALIZATION", "MISC", "STATS", "NOTES"};

        for (int i = 0; i <= mAttributes.size(); ++i) {
            LinearLayout entry = view.findViewById(resIds[i]);
            if (resIds[i] == R.id.characterAttribute_stats) {
                writeStatValuesToDisplay(entry);
                continue;
            }
            TextView title = entry.findViewById(R.id.characterAttribute_entry_title);
            TextView value = entry.findViewById(R.id.characterAttribute_entry_value);
            title.setText(keys[i]);
            value.setText(mAttributes.get(keys[i]));
        }

        return view;
    }

    private void writeStatValuesToDisplay(LinearLayout statDisplay) {
        int resIds[] = {R.id.characterStat_str,
                        R.id.characterStat_dex,
                        R.id.characterStat_int,
                        R.id.characterStat_wis,
                        R.id.characterStat_con,
                        R.id.characterStat_chr};
        String stats[] = {"STR", "DEX", "INT", "WIS", "CON", "CHR"};

        // Allocating stats to display panel
        for (int i = 0; i < resIds.length; ++i) {
            RelativeLayout stat = statDisplay.findViewById(resIds[i]);

            TextView statName = stat.findViewById(R.id.characterAttribute_stat_name);
            statName.setText(stats[i]);

            TextView statValue = stat.findViewById(R.id.characterAttribute_stat_value);
            statValue.setText(mStats.get(stats[i]));
        }
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
