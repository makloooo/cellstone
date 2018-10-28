package makloooo.cellstone;

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

import makloooo.cellstone.CharacterLoreFragment.OnEntrySelectedListener;

public class MainActivity extends AppCompatActivity
    implements HomeScreenFragment.OnHomeScreenInteractionListener,
               FactionMatrixFragment.OnMatrixInteractionListener,
               CharacterPagerFragment.OnProfileInteractionListener,
               OnEntrySelectedListener,
               ImageDisplayFragment.OnImageInteractionListener,
               MaterialDisplayFragment.OnMaterialDisplayListener,
               CallFragment.OnCallInteractionListener {

    ArrayList<Faction> mFactionList;
    Faction mCurrentFaction;
    Character mCurrentCharacter;

    int mUserCharacterId;

    FragmentManager mFragmentManager;

    ImageDisplayFragment mImageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // TODO: Interface this with a Database later
        loadData();

        // TODO: Set this to an initialization fragment upon startup
        // TODO: if it hasn't been set yet
        characterSelect();

        // loading a bg, currently a stock photo
        ImageView bg = findViewById(R.id.background);
        Glide.with(this)
                .load(R.drawable.bg)
                .apply(RequestOptions.centerCropTransform())
                .into(bg);

        //SharedPreferences sharedPref = this.getSharedPreferences(
        //    getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        mImageFragment = ImageDisplayFragment
                .newInstance(R.drawable.sample_sigil);

        // Build an array list of faction names to pass in, since that's all the fragment
        // really needs
        HomeScreenFragment homeScreen = HomeScreenFragment
                .newInstance(getFactionNames(), mUserCharacterId);

        fragmentTransaction.add(R.id.main_container, mImageFragment, "ImageDisplay");
        fragmentTransaction.add(R.id.main_container, homeScreen, "HomeScreen");
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

        // TODO: Reduce these two lines to something more elegant
        fragmentTransaction.replace(R.id.main_container, mImageFragment, "ImageDisplay");
        fragmentTransaction.add(R.id.main_container, factionMatrix, "FactionMatrix");

        fragmentTransaction.addToBackStack("HomeScreen");
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
        CharacterPagerFragment characterProfile = CharacterPagerFragment
                .newInstance();

        // TODO: Reduce these two lines to something more elegant
        fragmentTransaction.replace(R.id.main_container, mImageFragment);
        fragmentTransaction.add(R.id.main_container, characterProfile, "CharacterProfile");

        fragmentTransaction.addToBackStack("CharacterProfile");
        fragmentTransaction.commit();
    }

    /* Character Profile Fragment Interactions */
    public Character fetchCharacter() {
        return mCurrentCharacter;
    }

    public FragmentManager fetchFragmentManager() {
        return mFragmentManager;
    }

    /* Display Fragment Interactions */
    public void displayMaterial(String title, final String material) {
        String combinedMaterialText = title + "\n\n" + material;

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        MaterialDisplayFragment materialDisplay = MaterialDisplayFragment
                .newInstance(combinedMaterialText);

        fragmentTransaction.add(R.id.main_container, materialDisplay, "MaterialDisplay");
        fragmentTransaction.addToBackStack("MaterialDisplay");
        fragmentTransaction.commit();
    }

    public void clearMaterialDisplay() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.remove(mFragmentManager.findFragmentByTag("MaterialDisplay"));
        mFragmentManager.popBackStack();
        fragmentTransaction.commit();
    }

    /* Call Fragment Interactions */
    public void displayCommentary() {
        // start call fragment
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        CallFragment callFragment = CallFragment
                .newInstance(mCurrentCharacter.getCommentary(), R.drawable.sample_silhouette);

        fragmentTransaction.add(R.id.main_container, callFragment, "CommentaryDisplay");
        fragmentTransaction.addToBackStack("CommentaryDisplay");
        fragmentTransaction.commit();
    }

    public void clearCommentary() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.remove(mFragmentManager.findFragmentByTag("CommentaryDisplay"));
        mFragmentManager.popBackStack();
        fragmentTransaction.commit();
    }

    /* Activity Data Management */
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

        mUserCharacterId = R.drawable.sample_tamamo;
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
        loadGawain();
        mFactionList.get(1).addCharacter(new Character(
                "A Surprise Karna",
                R.drawable.sample_karna,
                "Another Sun Boy"));
        mFactionList.get(1).getCharacter(1).addLoreEntry("Hohohoho","he");

        mFactionList.get(1).addCharacter(new Character(
                "Also a BB",
                R.drawable.sample_bb,
                "Scary AI"));
        mFactionList.get(1).getCharacter(2).addLoreEntry("Hahahaha", "ha");

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

    protected void loadGawain() {
        Character gawain = mFactionList.get(1).getCharacter(0);
        gawain.addLoreEntry(
                        "01 - Excalibur Galatine: The Resurrected Sword of Victory",
                        "The sword of Sir Gawain and the sister sword to Excalibur. It is said that like Excalibur, Galatine once belonged to the Lady of the Lake. However, its legend have been overshadowed by its more famous counterpart, and therefore very few people know of its true significance.\n" +
                                "\n" +
                                "Another difference is the while Excalibur was said to absorb light radiated from the Earth, Galatine reflected the warming rays of the Sun.");
        gawain.addLoreEntry("02 - Knight of the Sun",
                        "While King Arthur was ruled by the movement of Artemis, the goddess of the moon, Sir Gawain drew his strength as a knight from the sun.\n" +
                                "It is said that his powers are at their greatest when the sun is at its zenith.");
        gawain.addLoreEntry("03 - Excalibur",
                        "Arthur, said to be the mightiest of all the kings to rule Britain, is believed to have ruled sometime in the fifth or sixth century.\n" +
                                "\n" +
                                "The sword that is said to have proclaimed his rule was bestowed upon him by the Elemental known in legends as the Lady of the Lake. The entire episode was passed in the stories collectively known as \"King Arthur and the Knights of the Round Table.\" ");
        gawain.addLoreEntry("Complete Wiki Entry",
                            "Saber's True Name is Gawain (ガウェイン, Gawein?), one of the Knights of the Round Table that appeared in the Arthurian legends, also known as the White Knight of the Round Table (円卓の白騎士, Entaku no Shirokishi?).[3]\n" +
                                    "Saber Lancelot Gawain Bedivere\n" +
                                    "\n" +
                                    "Artoria with Bedivere (right) Gawain (far right) and Lancelot (left).\n" +
                                    "\n" +
                                    "If King Arthur was the night, the symbol of the moon itself, then Sir Gawain was the day, which shoulders the sun. He acted as King Arthur's impersonator (in the kagemusha sense) in the royal castle of Camelot. In other words, he was one of the candidates to substitute the King after his fall. He is the bearer of the sister-sword to Excalibur, Excalibur Galatine. However, in his blind adoration for the King, he remained unaware of the King's agony. A loyal knight just like Sir Bedivere who took care of King Arthur in his final moments, but unlike Bedivere who wished for the King to attain happiness as a person from the bottom of his heart, Gawain wished for him to flourish as a king. Unmindful of such evaluation and intentions from those who surrounded him, Sir Gawain remained as King Arthur's right-hand to the end.[8]\n" +
                                    "\n" +
                                    "He never looked down on his enemies. Needless to say, he also never insults his enemies, confronting them with politeness and maintaining his fighting spirit and resolve even when the opponent lacks capacity. Remaining under the shadow of the King, he gallantly proceeds to the battlefield once the command is given and triumphs with a refreshing smile. Naturally, he was described as the ideal knight. His greatest regret was his dispute with Sir Lancelot. He was a knight who was equal to Sir Lancelot but was also incompatible with him, unable to forget that Lancelot killed his brothers, Gareth and Gaheris. With a pure personality, he was an ideal young warrior and thus his affection towards his relatives was greater than in others.\n" +
                                    "“Sir Lancelot not only killed my brothers, that black knight even betrayed the King. Kidnapped the King's wife. How do you expect that to be forgiven?”\n" +
                                    "\n" +
                                    "—Gawain\n" +
                                    "\n" +
                                    "But in the end, King Arthur forgave Sir Lancelot; \"I was at fault for everything\". Gawain, however, never forgave Sir Lancelot's betrayal and remained hostile towards him to the end. That grudge not only cost Sir Gawain's status as a knight, but in the end even played a part in the King's ruin. Sir Gawain continued to reject Sir Lancelot when he attempted to participate in the Battle of Camlann even after being excommunicated and, consequentially, caused the death of both the King and himself.\n" +
                                    "\n" +
                                    "During King Arthur's final battle at the hill of Camlann, Sir Gawain died after being struck by Mordred in an old wound inflicted by Sir Lancelot. Just as King Arthur reproached himself in the hill of Camlann, Sir Gawain, too, cried in his deathbed. His passion invited the King to death. He regretted that his grudge invited the ruin of the King and accepted his enmity towards Sir Lancelot - and even Lancelot's disloyalty - as coming from his own lack of virtue. Although his ending that did not help the king in the legend, that loyalty was sincere to the very end.[8]\n" +
                                    "“...in the chance there is a next time. If there is an opportunity to restore my honor - a second life - then at that time I will devote my everything to the king...”\n" +
                                    "\n" +
                                    "—Gawain\n" +
                                    "\n" +
                                    "Thus, he was brought back as a Heroic Spirit. From this conclusion, the Heroic Spirit Gawain seems to perceive \"being of assistance to the king\" as his absolute mission. Now set free from all doubts, Sir Gawain regained his proper figure as a literal Knight of the Sun (太陽の騎士, Taiyō no Kishi?). Having become a Servant while retaining such an oath in his heart in order to correct his mistake, the regret of his past life, Sir Gawain serves his Master as an even more perfect \"knight\". All for the sake of becoming the cornerstone of a single, lonely king.[7] ");
        gawain.setName("Gawain");
        gawain.setTitle("Knight of the Sun");
        gawain.setHeightWeight(180, 78);
        gawain.setRaceSpecialization("Human", "Knight");
        gawain.setMisc("Belongs to the Knights of the Round Table");
        gawain.setStats("B+","B","A","E","B+","A");
        gawain.setNotes("Only knows how to make mashed potatoes");

        gawain.addSpeculationEntry("Is he a dummy?");
        gawain.addSpeculationEntry("Effective hit confirmation with the Holy Sword, Keeping up a smile in the workplace");
        gawain.addSpeculationEntry("But in the end, King Arthur forgave Sir Lancelot; \"I was at fault for everything\". Gawain, however, never forgave Sir Lancelot's betrayal and remained hostile towards him to the end. That grudge not only cost Sir Gawain's status as a knight, but in the end even played a part in the King's ruin. Sir Gawain continued to reject Sir Lancelot when he attempted to participate in the Battle of Camlann even after being excommunicated and, consequentially, caused the death of both the King and himself.");
        gawain.addSpeculationEntry("If King Arthur was the night, the symbol of the moon itself, then Sir Gawain was the day, which shoulders the sun. He acted as King Arthur's impersonator (in the kagemusha sense) in the royal castle of Camelot. In other words, he was one of the candidates to substitute the King after his fall. He is the bearer of the sister-sword to Excalibur, Excalibur Galatine. However, in his blind adoration for the King, he remained unaware of the King's agony. A loyal knight just like Sir Bedivere who took care of King Arthur in his final moments, but unlike Bedivere who wished for the King to attain happiness as a person from the bottom of his heart, Gawain wished for him to flourish as a king. Unmindful of such evaluation and intentions from those who surrounded him, Sir Gawain remained as King Arthur's right-hand to the end.[8]\n" +
                "\n" +
                "He never looked down on his enemies. Needless to say, he also never insults his enemies, confronting them with politeness and maintaining his fighting spirit and resolve even when the opponent lacks capacity. Remaining under the shadow of the King, he gallantly proceeds to the battlefield once the command is given and triumphs with a refreshing smile. Naturally, he was described as the ideal knight. His greatest regret was his dispute with Sir Lancelot. He was a knight who was equal to Sir Lancelot but was also incompatible with him, unable to forget that Lancelot killed his brothers, Gareth and Gaheris. With a pure personality, he was an ideal young warrior and thus his affection towards his relatives was greater than in others. ");

        gawain.addCorrection(1, "This is actually correct");

        gawain.setCommentary("He's a knight all around, but all he knows is how to make mashed potatoes... I can't believe the knight of the sun is such an idiot despite his legend!");
    }

    public void loadImage(int resId) {
        mImageFragment.changePortrait(resId);
    }
}
