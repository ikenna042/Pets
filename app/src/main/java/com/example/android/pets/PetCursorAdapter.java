package com.example.android.pets;


import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.pets.data.PetContract;

/**
 * {@link PetCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link android.database.Cursor} of pet data as its data source.
 * This adapter knows how to create list items for each row of the pet data in
 * a {@link android.database.Cursor}.
 */

public class PetCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link PetCursorAdapter}.
     *
     * @param context The context
     * @param c The cursor from which to get the data.
     */

    public PetCursorAdapter(Context context, Cursor c) {
        super(context,c,0);
    }

    /**
     * Makes a new blank list item view. No data is set or bound to it yet
     *
     * @param context app context
     * @param cursor The cursor from which to get the data. The cursor is already moved to the
     *               correct position.
     * @param parent The parent to which the new view is attached to
     * @return the newly created list item view.
     */

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the pets data {in the current row pointed to by the cursor} to the given
     * list item layout. For example, the name for the current pet can be set to the name text view
     * in the list item layout.
     *
     * @param view  Existing view, returned earlier by the newView() method
     * @param context app context
     * @param cursor The cursor from which to get the data. The cursor is already moved to the
     *               correct row.
     */

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        //Find the individual views that we want to modify in the list item layout.
        TextView nameTextView = view.findViewById(R.id.name);
        TextView summaryTextView = view.findViewById(R.id.summary);

        //Find the columns of pet attribute that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
        int breedColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);

        //Read the pet attributes from the Cursor for the current pet
        String petName = cursor.getString(nameColumnIndex);
        String petBreed = cursor.getString(breedColumnIndex);

        // If the pet breed is empty string or null, then use some default text that
        // says "Unknown breed", so the TextView isn't blank.
        if (TextUtils.isEmpty(petBreed)) {
            petBreed = context.getString(R.string.unknown_breed);
        }

        //Update the TextViews with the attributes for the current pet
        nameTextView.setText(petName);
        summaryTextView.setText(petBreed);

    }
}
