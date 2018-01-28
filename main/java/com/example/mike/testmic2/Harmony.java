package com.example.mike.testmic2;

import android.util.Log;

/**
 * Created by Mike on 1/27/2018.
 */

class Harmony {

    private String[] flatScale = {"A♭", "A", "B♭", "B", "C", "D♭", "D", "E♭", "E", "F", "G♭", "G"};
    private String[] sharpScale = {"G♯", "A", "A♯", "B", "C", "C♯", "D", "D♯", "E", "F", "F♯", "G" };
    private String[] majorKeys = {"C", "F", "B♭", "E♭", "A♭", "D♭", "F♯", "B", "E", "A", "D", "G"};
    private int[] scaleSteps = {2,2,1,2,2,2}; // (1)

    // fields
    private String Key;
    private String Note;

    // getters/setters
    public String getKey()
    {
        return Key;
    }
    public void setKey( String newKey)
    {
        Key = newKey;
    }

    public String getNote()
    {
        return Note;
    }
    public void setNote( String newNote )
    {
        Note = newNote;
    }

    public Harmony( String note, String key )
    {
        setKey(key);
        setNote(note);
    }

    public int FindIndex( String[] search, String value )
    {
        for( int i = 0; i < search.length; i++ )
        {
            if( search[i].equals(value) )
            {
                return i;
            }
        }
        return -1;
    }

    // main functionality of class
    public String[] FindHarmony()
    {
        // 1st step: get index of Key in majorKeys array
        int keyIndex = FindIndex(majorKeys, Key);
        Log.d("KEY", Integer.toString(keyIndex));
        String[] notes;

        if( keyIndex < 6 )
        {
            notes = flatScale;
        }
        else
        {
            notes = sharpScale;
        }

        int noteIndex = FindIndex(notes, Note);
        keyIndex = FindIndex(notes, Key);
        Log.d("KEY", Integer.toString(keyIndex));

        // if the note sung isn't found in the selected scale... HANDLE
        if( noteIndex == -1 )
        {
            // inform user they can't carry tuna
        }

        String[] scale = new String[7];
        scale[0] = Key;  // first note of scale is the key value

        boolean foundNote = Note.equals(Key) ? true : false;

        // for each other note in the scale, we must walk the steps
        for( int sindex = 0; sindex < scaleSteps.length; sindex++ )
        {
            keyIndex += scaleSteps[sindex];
            if( keyIndex == 12 )
            {
                keyIndex = 0;
            }
            if( keyIndex == 13 )
            {
                keyIndex = 1;
            }
            scale[sindex+1]= notes[keyIndex];
            if( notes[keyIndex].equals(Note) )
            {
                foundNote = true;
            }
        }
        Log.d("Scale", "Begin---");
        for( int i = 0; i < scale.length; i++ )
        {
            Log.d("Scale", scale[i] + ",");
        }

        if( foundNote)
        {
            // fni = foundNoteIndex
            int fni = FindIndex(scale, Note);

            if( fni == 0 || fni == 2 || fni == 4 )
            {
                // major chord found
                String[] result = {scale[0], scale[2], scale[4]};
                return result;
            }

            if( fni == 1 || fni == 3 || fni == 5 )
            {
                // minor chord found
                String[] result = {scale[1], scale[3], scale[5]};
                return result;
            }

            if( fni == 2 || fni == 4|| fni == 6 )
            {
                // minor chord found
                String[] result = {scale[2], scale[4], scale[6]};
                return result;
            }

            if( fni == 3 || fni == 5 || fni == 0 )
            {
                // major chord found
                String[] result = {scale[3], scale[5], scale[0]};
                return result;
            }

            if( fni == 4 || fni == 6 || fni == 1 )
            {
                // major chord found
                String[] result = {scale[4], scale[6], scale[1]};
                return result;
            }

            if( fni == 5 || fni == 0 || fni == 2 )
            {
                // minor chord found
                String[] result = {scale[5], scale[0], scale[2]};
                return result;
            }

            if( fni == 6 || fni == 1 || fni == 3 )
            {
                // diminished chord found
                String[] result = {scale[6], scale[1], scale[3]};
                return result;
            }

        }

        String[] dud = {"S", "O", "L"};
        return dud;

    }


}
