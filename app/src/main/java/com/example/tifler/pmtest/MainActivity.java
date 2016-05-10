package com.example.tifler.pmtest;

import android.content.Context;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int WL_ID_NONE = 0;
    private static final int WL_ID_PARTIAL = 1;
    private static final int WL_ID_DIM = 2;
    private static final int WL_ID_BRIGHT = 3;
    private static final int WL_ID_FULL = 4;

    private static final String TAG = "PMTestApp";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private PowerManager mPowerManager;
    private PowerManager.WakeLock mWakelock = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinWL);

        List<String> wl = new ArrayList<String>();
        wl.add("NONE");
        wl.add("PARTIAL_WAKE_LOCK");
        wl.add("SCREEN_DIM_WAKE_LOCK");
        wl.add("SCREEN_BRIGHT_WAKE_LOCK");
        wl.add("FULL_WAKE_LOCK");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, wl);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateWakelockState(id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e(TAG, ">>>>>>>>>>> onNothingSelected");
            }
        });

        mPowerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Log.e(TAG, ">>>>>>>>>>>>> onStart");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.tifler.pmtest/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    protected void onResume() {
        super.onResume();
        Log.e(TAG, ">>>>>>>>>>>>> onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.e(TAG, ">>>>>>>>>>>>> onPause");
    }

    protected void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.tifler.pmtest/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        Log.e(TAG, ">>>>>>>>>>>>> onStop");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, ">>>>>>>>>>>>> onRestart");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, ">>>>>>>>>>>>> onDestroy");
    }

    private void updateWakelockState(long id) {
        Log.e(TAG, "updateWakeLockStat(" + id + ")");
        if (mWakelock != null) {
            mWakelock.release();
            mWakelock = null;
        }

        switch ((int)id) {
            case WL_ID_NONE:
                break;
            case WL_ID_PARTIAL:
                mWakelock = mPowerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
                mWakelock.acquire();
                break;
            case WL_ID_DIM:
                mWakelock = mPowerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, TAG);
                mWakelock.acquire();
                break;
            case WL_ID_BRIGHT:
                mWakelock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);
                mWakelock.acquire();
                break;
            case WL_ID_FULL:
                mWakelock = mPowerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, TAG);
                mWakelock.acquire();
                break;
            default:
                break;
        }
        String item = ((Spinner)findViewById(R.id.spinWL)).getItemAtPosition((int)id).toString();
        Toast.makeText(getApplicationContext(), item, Toast.LENGTH_LONG).show();

        //Toast.makeText(MainActivity getContext(), item, Toast.LENGTH_LONG).show();

    }
}
