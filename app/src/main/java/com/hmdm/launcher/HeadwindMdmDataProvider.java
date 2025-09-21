package com.hmdm.launcher;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HeadwindMdmDataProvider extends ContentProvider {

    // This is the public "address" of our data. The other app will use this.
    public static final String AUTHORITY = "com.hmdm.launcher.flutter_data_provider";    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/data");
    public static final String COLUMN_NAME = "shared_string";

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // Get the SharedPreferences where our string is stored
        SharedPreferences prefs = getContext().getSharedPreferences("hmdm_flutter_data", Context.MODE_PRIVATE);
        String storedString = prefs.getString(COLUMN_NAME, "No data"); // Default value

        // Create a "MatrixCursor" which is like a temporary, in-memory table
        MatrixCursor cursor = new MatrixCursor(new String[]{COLUMN_NAME});

        // Add one row with our stored string
        cursor.addRow(new Object[]{storedString});

        return cursor;
    }

    // We are only providing data, not allowing changes, so the rest can be empty.
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) { return null; }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) { return null; }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) { return 0; }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) { return 0; }
}