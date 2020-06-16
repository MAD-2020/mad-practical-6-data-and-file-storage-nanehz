package sg.edu.np.week_6_whackamole_3_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

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

        final MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);


//        createBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                UserData userData = new UserData();
//                userData.setMyUserName(username_input.getText().toString());
//                userData.setMyPassword(password_input.getText().toString());
//
//                if (dbHandler.findUser(username_input.getText().toString())  == userData)
//                {
//                    Toast.makeText(getApplicationContext(),"User already Exists. Please try again.",Toast.LENGTH_SHORT).show();
//                    Log.v(TAG, FILENAME + ": User already exist during new user creation!");
//                }
//                else
//                {
//
//                    UserData dbUserData = new UserData();
//                    dbUserData.setLevels(new ArrayList<Integer>());
//                    dbUserData.setMyUserName(username_input.getText().toString());
//                    dbUserData.setMyPassword(password_input.getText().toString());
//                    dbHandler.addUser(dbUserData);
//                    username_input.setText("");
//                    password_input.setText("");
//                    Log.v(TAG, FILENAME + ": New user created successfully!");
//                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
////                    dbHandler.addUser(userData);
////                    username_input.setText("");
////                    password_input.setText("");
////                    Log.v(TAG, FILENAME + ": New user created successfully!");
////                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                }
//
//            }
//        });



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
