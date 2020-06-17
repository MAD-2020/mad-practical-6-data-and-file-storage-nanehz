package sg.edu.np.week_6_whackamole_3_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Main4Activity extends AppCompatActivity {

    /* Hint:
        1. This creates the Whack-A-Mole layout and starts a countdown to ready the user
        2. The game difficulty is based on the selected level
        3. The levels are with the following difficulties.
            a. Level 1 will have a new mole at each 10000ms.
                - i.e. level 1 - 10000ms
                       level 2 - 9000ms
                       level 3 - 8000ms
                       ...
                       level 10 - 1000ms
            b. Each level up will shorten the time to next mole by 100ms with level 10 as 1000 second per mole.
            c. For level 1 ~ 5, there is only 1 mole.
            d. For level 6 ~ 10, there are 2 moles.
            e. Each location of the mole is randomised.
        4. There is an option return to the login page.
     */
    private static final String FILENAME = "Main4Activity.java";
    private static final String TAG = "Whack-A-Mole3.0!";
    CountDownTimer readyTimer;
    CountDownTimer newMolePlaceTimer;
    Button BackToLevelSelect;
    TextView ScoreView;
    int Score = 0;
    Button[] buttonList = new Button[9];




    private void readyTimer(){
        readyTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
                Toast.makeText(Main4Activity.this,
                        "Get Ready in " + millisUntilFinished/1000 + " seconds",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Log.v(TAG, "Ready CountDown Complete!");
                Toast.makeText(Main4Activity.this, "GO!", Toast.LENGTH_SHORT).show();
                newMolePlaceTimer.start();
            }
        };
        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */
    }
    private void placeMoleTimer(final int currLevel){

        int timeInterval = 11000 - (1000 * currLevel);
        newMolePlaceTimer = new CountDownTimer(Long.MAX_VALUE, timeInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                setNewMole(currLevel);
                Log.v(TAG, "New Mole Location!");
            }

            @Override
            public void onFinish() {

            }
        };
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */
    }
    private static final int[] BUTTON_IDS = {
            /* HINT:
                Stores the 9 buttons IDs here for those who wishes to use array to create all 9 buttons.
                You may use if you wish to change or remove to suit your codes.*/
            R.id.topLeftBtn, R.id.topMidBtn, R.id.topRightBtn, R.id.midLeftBtn, R.id.midBtn, R.id.midRightBtn,
            R.id.bottomLeftBtn, R.id.bottomMidBtn, R.id.bottomRightBtn
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ScoreView = findViewById(R.id.basicScoreTextView);
        BackToLevelSelect = findViewById(R.id.backBtn);

        final Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        final Integer level = intent.getIntExtra("level", 0);
        final Integer score = intent.getIntExtra("score", 0);

        BackToLevelSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Main4Activity.this, Main3Activity.class);
                if (Score > score)
                {
                    updateUserScore(username, level, Score);
                }
                intent2.putExtra("username", username);
                startActivity(intent2);
            }
        });

        readyTimer();
        placeMoleTimer(level);

        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares level difficulty.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
            It also prepares the back button and updates the user score to the database
            if the back button is selected.
         */


        for(int i = 0; i < 9; i++){
            buttonList[i] = findViewById(BUTTON_IDS[i]);
            final int finalI = i;
            buttonList[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doCheck(buttonList[finalI]);
                    setNewMole(level);
                }
            });
            /*  HINT:
            This creates a for loop to populate all 9 buttons with listeners.
            You may use if you wish to remove or change to suit your codes.
            */
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        readyTimer();
    }
    private void doCheck(Button checkButton)
    {
        if (checkButton.getText() == "*"){
            Score++;
            Log.v(TAG, FILENAME + ": Hit, score added!");

        }
        else{
            Score--;
            Log.v(TAG, FILENAME + ": Missed, point deducted!");
        }
        ScoreView.setText(String.valueOf(Score));
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, FILENAME + ": Hit, score added!");
            Log.v(TAG, FILENAME + ": Missed, point deducted!");
            belongs here.
        */

    }

    public void setNewMole(int currLevel)
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole. Adds additional mole if the level difficulty is from 6 to 10.
         */
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);
        for (Button btn: buttonList){
            btn.setText("O");
        }
        buttonList[randomLocation].setText("*");

        if (currLevel > 5){
            int randomLocation2 = 0;
            while (randomLocation2 != 0 && randomLocation2 != randomLocation){
                randomLocation2 = ran.nextInt(9);
            }
            buttonList[randomLocation2].setText("*");
        }

    }


    private void updateUserScore(String userName, int currLevel, int highScore)
    {
        newMolePlaceTimer.cancel();
        readyTimer.cancel();
        Log.v(TAG, FILENAME + ": Update User Score...");
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        UserData userData = dbHandler.findUser(userName);
        dbHandler.deleteAccount(userName);
        userData.getScores().set(currLevel -1 , highScore);
        dbHandler.addUser(userData);

     /* Hint:
        This updates the user score to the database if needed. Also stops the timers.
        Log.v(TAG, FILENAME + ": Update User Score...");
      */

    }
}
