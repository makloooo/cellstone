package makloooo.cellstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityHomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        characterSelect();

        // test persistent data
    }

    protected void characterSelect() {
        // start character select activity
    }
}
