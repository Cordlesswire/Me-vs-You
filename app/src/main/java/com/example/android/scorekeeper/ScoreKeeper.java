package com.example.android.scorekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.scorekeeper.MainActivity.PLAYER_1;
import static com.example.android.scorekeeper.MainActivity.PLAYER_2;

public class ScoreKeeper extends AppCompatActivity {

    static final String STATE_SCORE_A = "saveTeamAscore";
    static final String STATE_SCORE_B = "saveTeamBscore";

    // Tracks the score for Team A
    int scoreTeamA;
    // Tracks the score for Team B
    int scoreTeamB;

    // Need to be declare the edit text
    private TextView nameA;
    private TextView nameB;
    private String gamer1;
    private String gamer2;
    private String winningPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_keeper);

        displayForTeamA(0);
        displayForTeamB(0);


        // Save the user's current game state
        if (savedInstanceState != null) {
            scoreTeamA = savedInstanceState.getInt(STATE_SCORE_A, scoreTeamA);
            scoreTeamB = savedInstanceState.getInt(STATE_SCORE_B, scoreTeamB);
            displayForTeamA(scoreTeamA);
            displayForTeamB(scoreTeamB);
        }

        // Always call the superclass so it can save the view hierarchy state
        // This causes the app to crash!
        // super.onSaveInstanceState(savedInstanceState);



        // Get the message from the intent
        Intent intent = getIntent();

        gamer1 = intent.getStringExtra(PLAYER_1);
        gamer2 = intent.getStringExtra(PLAYER_2);

        nameA = (TextView) findViewById(R.id.teamNameA);
        nameB = (TextView) findViewById(R.id.teamNameB);
        nameA.setText(gamer1);
        nameB.setText(gamer2);

        // Welcome message
        // TODO Make message with center gravity
        Toast.makeText(this, "Prepare for the battle: " + "\n" + gamer1 + " vs " + gamer2,
                Toast.LENGTH_LONG).show();
    }


    /**
     * Display the given score for the Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);

        if (scoreTeamA <= -1) {
            Toast.makeText(this, "That sucks, " + gamer1, Toast.LENGTH_SHORT).show();
        }

        // If the button +3 is pushed two in a row
//        if (scoreTeamB ) {
//            Toast.makeText(this, "You're doing great!", Toast.LENGTH_SHORT).show();
//       }

        if (scoreTeamA >= 20) {
            Toast.makeText(this, "Legendary " + gamer1, Toast.LENGTH_SHORT).show();
        }

        scoreView.setText(String.valueOf(score));
    }

    /**
     * Increase the score for Team A by 1 points.
     */
    public void submitOnePointForTeamA(View view) {
        scoreTeamA++;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Increase the score for Team A by 3 points.
     */
    public void submitThreePointsForTeamA(View view) {
        scoreTeamA += 3;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Decrease the score for Team A by 1 points.
     */
    public void submitMinusOnePointForTeamA(View view) {
        scoreTeamA--;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Display the given score for the Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);

        if (scoreTeamB <= -1) {
            Toast.makeText(this, "That sucks " + gamer2, Toast.LENGTH_SHORT).show();
        }

//        if (scoreTeamB >= 9) {
//            Toast.makeText(this, "You're doing great!", Toast.LENGTH_SHORT).show();
//        }
        if (scoreTeamB >= 20) {
            Toast.makeText(this, "Legendary " + gamer2, Toast.LENGTH_SHORT).show();
        }

        scoreView.setText(String.valueOf(score));
    }

    /**
     * Increase the score for Team A by 1 point.
     */
    public void submitOnePointForTeamB(View view) {
        scoreTeamB++;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Increase the score for Team A by 3 points.
     */
    public void submitThreePointsForTeamB(View view) {
        scoreTeamB += 3;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Decrease the score for Team A by 1 point.
     */
    public void submitMinusOnePointForTeamB(View view) {
        scoreTeamB--;
        displayForTeamB(scoreTeamB);
    }

    public void reset(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

    public void finish(View view) {

        if (scoreTeamA > scoreTeamB) {
            winningPlayer = gamer1;
        } else {
            winningPlayer = gamer2;
        }

        ImageView winnerImage = (ImageView) findViewById(R.id.winnerImage);
        winnerImage.setImageResource(R.drawable.avatar_4);

        // Set image to be visible

        TextView winnerText = (TextView) findViewById(R.id.winnerText);
        winnerText.setText("You win, " + winningPlayer + "!");



        //if (scoreTeamB == scoreTeamB) {
            // Do something
        //}


    }


    // Save state when rotating
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt(STATE_SCORE_A, scoreTeamA);
        savedInstanceState.putInt(STATE_SCORE_B, scoreTeamB);

        super.onSaveInstanceState(savedInstanceState);

    }

    // Restore state when rotating
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        savedInstanceState.getInt(STATE_SCORE_A);
        savedInstanceState.getInt(STATE_SCORE_B);
    }
}
