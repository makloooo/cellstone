package makloooo.cellstone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class CharacterPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "CharacterPagerAdapter";

    private static final String[] TITLES = {"Attributes", "Lore", "Trivia"};

    private Character mCharacter;

    CharacterPagerAdapter(FragmentManager fragmentManager, Character character) {
        super(fragmentManager);
        mCharacter = character;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //Log.d("CharacterPagerAdapter", "pageTitle: " + TITLES[position]);
        //Log.d("CharacterPagerAdapter", "position: " + position);
        return TITLES[position];
    }

    @Override
    public Fragment getItem(int position) {
        //Log.d("PagerAdapter", "Position: " + position);

        // Pager returns fragments to display
        switch (position) {
            case 0: return CharacterAttributesFragment.newInstance(mCharacter.getAttributes(), mCharacter.getStats());
            case 1: return CharacterLoreFragment.newInstance(mCharacter.getLoreCaptions(), mCharacter.getLoreMaterial());
            case 2: return CharacterSpeculationFragment.newInstance(mCharacter.getSpeculationEntries());
            default: return CharacterAttributesFragment.newInstance(mCharacter.getAttributes(), mCharacter.getStats());
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
