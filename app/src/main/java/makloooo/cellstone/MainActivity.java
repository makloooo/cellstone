package makloooo.cellstone;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.CropTransformation.CropType;

public class MainActivity extends AppCompatActivity
    implements HomeScreenFragment.OnHomeScreenInteractionListener,
               FactionMatrixFragment.OnMatrixInteractionListener,
               CharacterProfileFragment.OnProfileInteractionListener {

    ArrayList<Faction> mFactionList;
    Faction mCurrentFaction;
    Character mCurrentCharacter;

    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // TODO: Interface this with a Database later
        loadData();

        //SharedPreferences sharedPref = this.getSharedPreferences(
        //    getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        // Build an array list of faction names to pass in, since that's all the fragment
        // really needs
        HomeScreenFragment homeScreen = HomeScreenFragment.newInstance(getFactionNames());

        fragmentTransaction.add(R.id.main_container, homeScreen);
        fragmentTransaction.commit();
    }

    /* Home Screen Fragment Interactions */
    public void onFactionSelected(int position) {
        // get the faction at that position
        mCurrentFaction = mFactionList.get(position);

        // remove homescreen fragment
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        FactionMatrixFragment factionMatrix = FactionMatrixFragment
                .newInstance(mCurrentFaction.getCharacterNames());
        fragmentTransaction.replace(R.id.main_container, factionMatrix);
        fragmentTransaction.addToBackStack("HomeScreen");
        /*

         */
        fragmentTransaction.commit();
        // start faction matrix fragment passing relative faction info as args
    }

    /* Faction Matrix Fragment Interactions */
    public int fetchPortrait(int position) {
        return mCurrentFaction.getCharacter(position).getPortraitId();
    }

    public void onCharacterSelected(int position) {
        mCurrentCharacter = mCurrentFaction.getCharacter(position);
        Toast.makeText(this, "Selected character #" + position, Toast.LENGTH_SHORT)
            .show();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        CharacterProfileFragment characterProfile = CharacterProfileFragment
                .newInstance();
        fragmentTransaction.replace(R.id.main_container, characterProfile);
        fragmentTransaction.addToBackStack("CharacterProfile");
        fragmentTransaction.commit();
    }

    /* Character Profile Fragment Interactions */
    public Character fetchCharacter() {
        return mCurrentCharacter;
    }

    /* Activity Data Management */
    public void loadPortrait(Fragment fragment, ImageView imageView, int resId) {
        RequestOptions options = new RequestOptions();
        options.transforms(new CropTransformation(
                imageView.getWidth(),
                imageView.getHeight() - imageView.getPaddingTop(),
                CropType.TOP));
        Glide.with(fragment)
                .load(resId)
                .apply(options)
                .into(imageView);
    }

    protected void characterSelect() {
        // start character select activity

        // black out screen
        // scroll choices to screen
        // red for gruh
        // white for toshiie
        // green for polly
        // blue for valerian
        // set theme
        // save to preferences
    }

    private ArrayList<String> getFactionNames() {
        ArrayList<String> factionNames = new ArrayList<>();
        for (Faction faction : mFactionList) { factionNames.add(faction.getName()); }
        return factionNames;
    }

    /** Loads data from resources into hash map
     *  We'll be using a Room Persistence library later for data
     *  storage, but for now let's just initialize a sample faction. */
    protected void loadData() {
        mFactionList = new ArrayList<>();

        mFactionList.add(new Faction("The Three Gawains"));
        mFactionList.get(0).addCharacter(new Character(
                "Gawain",
                R.drawable.sample_gawain,
                "Our Gorilla Knight of the Sun"));
        mFactionList.get(0).addCharacter(new Character(
                "Also Gawain",
                R.drawable.sample_gawain,
                "Our Ape Knight of the Sun"));
        mFactionList.get(0).addCharacter(new Character(
                "Another Gawain",
                R.drawable.sample_gawain,
                "Our Monkey Knight of the Sun"));

        mFactionList.add(new Faction("The Other Three Gawains"));
        mFactionList.get(1).addCharacter(new Character(
                "Still Gawain",
                R.drawable.sample_gawain,
                "Our Gorilla Knight of the Sun"));
        mFactionList.get(1).addCharacter(new Character(
                "A Surprise Karna",
                R.drawable.sample_karna,
                "Another Sun Boy"));
        mFactionList.get(1).addCharacter(new Character(
                "Also a BB",
                R.drawable.sample_bb,
                "Scary AI"));

        mFactionList.add(new Faction("Please no more Gawains"));
        mFactionList.get(2).addCharacter(new Character(
                "Still Gawain",
                R.drawable.sample_gawain,
                "Our Gorilla Knight of the Sun"));
        mFactionList.get(2).addCharacter(new Character(
                "Yet Another Gawain",
                R.drawable.sample_gawain,
                "Our Ape Knight of the Sun"));
        mFactionList.get(2).addCharacter(new Character(
                "Stop showing me Gawain",
                R.drawable.sample_gawain,
                "Our Monkey Knight of the Sun"));
    }
}
