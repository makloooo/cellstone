package makloooo.cellstone;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
    implements FragmentHomeScreen.OnHomeScreenInteractionListener {

    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //SharedPreferences sharedPref = this.getSharedPreferences(
        //    getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        FragmentHomeScreen homeScreen = new FragmentHomeScreen();
        fragmentTransaction.add(R.id.main_container, homeScreen);
        fragmentTransaction.commit();
    }

    public void onFactionSelected(int position) {
        // remove homescreen fragment
        // start faction matrix fragment passing relative faction info as args
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

    protected void displayFactionMembers(View faction) {
        // start a new activity with a matrix for each member
    }
}
