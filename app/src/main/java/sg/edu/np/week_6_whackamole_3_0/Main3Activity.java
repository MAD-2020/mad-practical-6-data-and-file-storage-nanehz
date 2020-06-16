package sg.edu.np.week_6_whackamole_3_0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Main3Activity extends AppCompatActivity {
    /* Hint:
        1. This displays the available levels from 1 to 10 to the user.
        2. The different levels makes use of the recyclerView and displays the highest score
           that corresponds to the different levels.
        3. Selection of the levels will load relevant Whack-A-Mole game.
        4. The levels are with the following difficulties.
            a. Level 1 will have a new mole at each 10000ms.
            b. Each level up will shorten the time to next mole by 100ms with level 10 as 1000 second per mole.
            c. For level 1 ~ 5, there is only 1 mole.
            d. For level 6 ~ 10, there are 2 moles.
            e. Each location of the mole is randomised.
        5. There is an option return to the login page.
     */

    MyDBHandler myDatabase;


    private static final String FILENAME = "Main3Activity.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        Button backToLogin = findViewById(R.id.backToLogin);
        RecyclerView levelsPage = findViewById(R.id.recyclerView);
        myDatabase = new MyDBHandler(this, null, null, 1);
        Intent intent = getIntent();
        //here we are retrieving the username that we put in mainactivity so that we know which is the current user that is logged in
        String userName = intent.getStringExtra("userName");
        UserData userData = myDatabase.findUser(userName);

        CustomScoreAdaptor adaptor = new CustomScoreAdaptor(userData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        levelsPage.setAdapter(adaptor);
        levelsPage.setLayoutManager(layoutManager);
        Log.v(TAG, FILENAME + ": Show level for User: "+ userName);


        // go back to login page
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));}
        });

        /* Hint:
        This method receives the username account data and looks up the database for find the
        corresponding information to display in the recyclerView for the level selections page.

        Log.v(TAG, FILENAME + ": Show level for User: "+ userName);
         */
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
