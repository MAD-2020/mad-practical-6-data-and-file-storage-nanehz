package sg.edu.np.week_6_whackamole_3_0;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.logging.Level;

import androidx.recyclerview.widget.RecyclerView;

public class CustomScoreAdaptor extends RecyclerView.Adapter<CustomScoreViewHolder> {
    /* Hint:
        1. This is the custom adaptor for the recyclerView list @ levels selection page

     */
    private static final String FILENAME = "CustomScoreAdaptor.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    UserData player;
    ArrayList<Integer> Levels;
    ArrayList<Integer> Scores;


    public CustomScoreAdaptor(UserData userdata){

        player = userdata;
        Levels = player.getLevels();
        Scores = player.getScores();
        /* Hint:
        This method takes in the data and readies it for processing.
         */

    }

    public CustomScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        /* Hint:
        This method dictates how the viewholder layuout is to be once the viewholder is created.
         */
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.level_select, parent, false);
        return new CustomScoreViewHolder(v);

    }

    public void onBindViewHolder(CustomScoreViewHolder holder, final int position){

        /* Hint:
        This method passes the data to the viewholder upon bounded to the viewholder.
        It may also be used to do an onclick listener here to activate upon user level selections.

        Log.v(TAG, FILENAME + " Showing level " + level_list.get(position) + " with highest score: " + score_list.get(position));
        Log.v(TAG, FILENAME+ ": Load level " + position +" for: " + list_members.getMyUserName());
         */

        final Integer lvls = Levels.get(position);
        final Integer scores = Scores.get(position);

        Log.v("TEST", "Levels" + player.getLevels());

        holder.lvl.setText("Levels: " + lvls);
        holder.score.setText("Scores: " + scores);
        Log.v(TAG, FILENAME + " Showing level " + Levels.get(position) + " with highest score: " + Scores.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passing data from current viewholder to main4activity
                Intent intent = new Intent(v.getContext(), Main4Activity.class);
                //passing username, selected level and score
                intent.putExtra("username", player.getMyUserName());
                intent.putExtra("level", lvls);
                intent.putExtra("score", scores);
                v.getContext().startActivity(intent);
                Log.v(TAG, FILENAME+ ": Load level " + position +" for: " + player.getMyUserName());

            }
        });


    }

    public int getItemCount(){
        return Levels.size();
        /* Hint:
        This method returns the the size of the overall data.
         */
    }
}