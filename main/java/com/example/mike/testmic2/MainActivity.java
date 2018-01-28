package com.example.mike.testmic2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.media.MediaPlayer;    // for playing
import android.media.MediaRecorder;  // for mic recording
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MediaRecorder r = new MediaRecorder();


        setContentView(R.layout.activity_main);

        final MediaPlayer foxsound = MediaPlayer.create(this,R.raw.fox);
        final MediaPlayer a4sound = MediaPlayer.create(this, R.raw.a4);
        final MediaPlayer ash4sound = MediaPlayer.create(this, R.raw.ash4_bb4);
        final MediaPlayer b4sound = MediaPlayer.create(this, R.raw.b4);
        final MediaPlayer c4sound = MediaPlayer.create(this, R.raw.c4);
        final MediaPlayer csh4sound = MediaPlayer.create(this, R.raw.csh4_db4);
        final MediaPlayer d4sound = MediaPlayer.create(this, R.raw.d4);
        final MediaPlayer dsh4sound = MediaPlayer.create(this, R.raw.dsh4_eb4);
        final MediaPlayer e4sound = MediaPlayer.create(this, R.raw.e4);
        final MediaPlayer f4sound = MediaPlayer.create(this, R.raw.f4);
        final MediaPlayer fsh4sound = MediaPlayer.create(this, R.raw.fsh4_gb4);
        final MediaPlayer g4sound = MediaPlayer.create(this, R.raw.g4);
        final MediaPlayer gsh4sound = MediaPlayer.create(this, R.raw.gsh4_ab4);

        final Button bSound = (Button) findViewById(R.id.buttonSound);
        bSound.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Harmony h = new Harmony( "C", "E♭");
                String[] result = h.FindHarmony();
                String printResult = result[0]+result[1]+result[2];
                //final TextView tv = (TextView) findViewById(R.id.label);
                //tv.setText(printResult);

                a4sound.start();
                g4sound.start();
                csh4sound.start();
            }
        });

        final Spinner spinner = (Spinner) findViewById(R.id.key_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,  R.array.key_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        final Spinner spinner2 = (Spinner) findViewById(R.id.note_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,  R.array.note_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

        final Button bMic = (Button) findViewById(R.id.buttonMic);
        bMic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                String selectedKey = spinner.getSelectedItem().toString();
                String tempNote = spinner2.getSelectedItem().toString();
                String selectedNote = tempNote;
                String selectedNoteAlt = tempNote;
                final TextView tv = (TextView) findViewById(R.id.output);

                if( tempNote.length() > 2 )
                {
                    try {
                        selectedNote = tempNote.substring(0, 2);
                        selectedNoteAlt = tempNote.substring(3);
                        tv.setText("Selected " + selectedKey + " " + selectedNote + "/" + selectedNoteAlt);
                    }
                    catch (Exception e){
                        tv.setText(e.getMessage());
                    }
                }



                Harmony h = new Harmony(selectedNote, selectedKey);
                String[] result = h.FindHarmony();
                if( result[0].equals("S") )
                {
                    h.setNote(selectedNoteAlt);
                    result = h.FindHarmony();
                    Toast.makeText(getApplicationContext(), "Using Alt note syntax ("+selectedNoteAlt+")", Toast.LENGTH_SHORT).show();
                }

                int count = 0;

                if( h.FindIndex(result, "A") > 0 )
                {
                    a4sound.start();
                    count++;
                }
                if( h.FindIndex(result, "B") > 0 )
                {
                    b4sound.start();
                    count++;
                }
                if( h.FindIndex(result, "C") > 0 )
                {
                    c4sound.start();
                    count++;
                }
                if( h.FindIndex(result, "D") > 0 )
                {
                    d4sound.start();
                    count++;
                }
                if( h.FindIndex(result, "E") > 0 )
                {
                    e4sound.start();
                    count++;
                }
                if( h.FindIndex(result, "F") > 0 )
                {
                    f4sound.start();
                    count++;
                }
                if( h.FindIndex(result, "G") > 0 )
                {
                    g4sound.start();
                    count++;
                }
                if( h.FindIndex(result, "A♭") > 0 || h.FindIndex(result, "G♯") > 0)
                {
                    gsh4sound.start();
                    count++;
                }
                if( h.FindIndex(result, "B♭") > 0 || h.FindIndex(result, "A♯") > 0)
                {
                    ash4sound.start();
                    count++;
                }
                if( h.FindIndex(result, "D♭") > 0 || h.FindIndex(result, "C♯") > 0)
                {
                    csh4sound.start();
                    count++;
                }
                if( h.FindIndex(result, "E♭") > 0 || h.FindIndex(result, "D♯") > 0)
                {
                    dsh4sound.start();
                    count++;
                }
                if( h.FindIndex(result, "G♭") > 0 || h.FindIndex(result, "F♯") > 0)
                {
                    fsh4sound.start();
                    count++;
                }


                final TextView tv2 = (TextView) findViewById(R.id.output2);
                if( count == 0 )
                {
                    foxsound.start();
                    tv2.setText("No harmony found");
                }
                else{
                    tv2.setText("Found Harmony: " + result[0] + " " + result[1] + " " + result[2]);
                }

            }
        });
    }

}
