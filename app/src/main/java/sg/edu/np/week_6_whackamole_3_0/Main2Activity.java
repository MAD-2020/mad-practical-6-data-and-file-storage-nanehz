package sg.edu.np.week_6_whackamole_3_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.AutofillService;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

public class Main2Activity extends AppCompatActivity {

    Button cancelBTN, createBTN;
    EditText username_input, password_input;
    /* Hint:
        1. This is the create new user page for user to log in
        2. The user can enter - Username and Password
        3. The user create is checked against the database for existence of the user and prompts
           accordingly via Toastbox if user already exists.
        4. For the purpose the practical, successful creation of new account will send the user
           back to the login page and display the "User account created successfully".
           the page remains if the user already exists and "User already exist" toastbox message will appear.
        5. There is an option to cancel. This loads the login user page.
     */

    private static final String FILENAME = "Main2Activity.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        cancelBTN = findViewById(R.id.cancelBTN);
        createBTN = findViewById(R.id.createBTN);
        username_input = findViewById(R.id.username_input);
        password_input = findViewById(R.id.password_input);

        final MyDBHandler myDatabase = new MyDBHandler(this,null,null,1);

        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });

        createBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserData userData = myDatabase.findUser(username_input.getText().toString().trim());

                if (userData != null)
                {
                    Log.v(TAG, FILENAME + ": User already exist during new user creation!");
                    Toast.makeText(getApplicationContext(), "User already exist during new user creation!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ArrayList<Integer> Scores = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
                    ArrayList<Integer> Levels = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0));

                    UserData newUser = new UserData(username_input.getText().toString(),password_input.getText().toString(),Scores, Levels);

                    myDatabase.addUser(newUser);
                    Log.v(TAG, FILENAME + ": New user created successfully!");
                    Toast.makeText(getApplicationContext(), "New user created successfully!", Toast.LENGTH_SHORT).show();
                    username_input.setText("");
                    password_input.setText("");
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                }

            }
        });

        /* Hint:
            This prepares the create and cancel account buttons and interacts with the database to determine
            if the new user created exists already or is new.
            If it exists, information is displayed to notify the user.
            If it does not exist, the user is created in the DB with default data "0" for all levels
            and the login page is loaded.

            Log.v(TAG, FILENAME + ": New user created successfully!");
            Log.v(TAG, FILENAME + ": User already exist during new user creation!");

         */
    }

    protected void onStop() {
        super.onStop();
        finish();
    }
}
