package com.example.synthesis;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String landmark = extras.getString("landmark");

        displayResult(landmark);
    }

    private void displayResult(String landmark) {
        if (landmark.equals("")) {
            String failMessage = "Object not recognised"
            Toast.makeText(getBaseContext(),failMessage, Toast.LENGTH_LONG).show();
        } else {
            Integer key = map.get(landmark);
            if (key == null) {
                String unrecognisedMessage = "Unrecognisable landmark";
                Toast.makeText(getBaseContext(),unrecognisedMessage, Toast.LENGTH_LONG).show();
            }
            else {
                if (locked[key]) {
                    locked[key] = false;
                    String unlockedNote = note[key];
                    String successMessage = "Found a new note! " + unlockedNote + " unlocked!";
                    Toast.makeText(getBaseContext(),successMessage, Toast.LENGTH_LONG).show();
                }
                else {
                    String alreadyMessage = "Landmark already recognised";
                    Toast.makeText(getBaseContext(),alreadyMessage, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
