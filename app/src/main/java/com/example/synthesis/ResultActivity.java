package com.example.synthesis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;

import java.io.File;
import java.util.List;

import static android.os.Environment.DIRECTORY_PICTURES;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File imgFile = new File(getExternalFilesDir(DIRECTORY_PICTURES)
                + "/temp.jpeg");
        Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        ImageView imageView = (ImageView) findViewById(R.id.imagePhoto);
        imageView.setImageBitmap(bitmap);

        displayResult(bitmap);
    }

    private void displayResult(Bitmap bitmap) {
        FirebaseVisionImage visionImage = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionImageLabeler labeler = FirebaseVision.getInstance()
                .getOnDeviceImageLabeler();
        labeler.processImage(visionImage)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionImageLabel>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionImageLabel> firebaseVisionImageLabels) {
                        float highConfidence = 0;
                        String highLabel = "";

                        for (FirebaseVisionImageLabel label: firebaseVisionImageLabels) {
                            String labelText = label.getText();
                            float confidence = label.getConfidence();

                            if (confidence > highConfidence) {
                                highConfidence = confidence;
                                highLabel = labelText;
                            }
                        }
                        TextView textLabel =(TextView) findViewById(R.id.textLabel);
                        textLabel.setText(highLabel);
                        String successMessage = "Unlocked a note!";
                        Toast.makeText(getBaseContext(), successMessage, Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

//        if (landmark.equals("")) {
//            String failMessage = "Object not recognised"
//            Toast.makeText(getBaseContext(),failMessage, Toast.LENGTH_LONG).show();
//        } else {
//            Integer key = map.get(landmark);
//            if (key == null) {
//                String unrecognisedMessage = "Unrecognisable landmark";
//                Toast.makeText(getBaseContext(),unrecognisedMessage, Toast.LENGTH_LONG).show();
//            }
//            else {
//                if (locked[key]) {
//                    locked[key] = false;
//                    String unlockedNote = note[key];
//                    String successMessage = "Found a new note! " + unlockedNote + " unlocked!";
//                    Toast.makeText(getBaseContext(),successMessage, Toast.LENGTH_LONG).show();
//                }
//                else {
//                    String alreadyMessage = "Landmark already recognised";
//                    Toast.makeText(getBaseContext(),alreadyMessage, Toast.LENGTH_LONG).show();
//                }
//            }
//        }
    }
}
