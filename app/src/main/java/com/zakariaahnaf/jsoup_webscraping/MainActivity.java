package com.zakariaahnaf.jsoup_webscraping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BuildCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.jsoup.Jsoup;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String currentVersion, latestVersion;
    Float lVersion;
    Float Version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get latest version Play Store
        //call for latest version
        new GetLatestVersion().execute();
    }



    //for update App Version====================================================================
    private class GetLatestVersion extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

            try {
                latestVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=com.copypasteit.mobilehut.latest.mobile.market")
                        .timeout(30000).get().select("div.hAyfc:nth-child(4)>" +
                                "span:nth-child(2) > div:nth-child(1)" +
                                "> span:nth-child(1)").first().ownText();

                // urlta dik teke aste hoy html er

            } catch (IOException e) {
                e.printStackTrace();
            }


            return latestVersion;
        }

        @Override
        protected void onPostExecute(String s) {
            //Get current version

            currentVersion = BuildConfig.VERSION_NAME;


            Toast.makeText(MainActivity.this, "LV="+latestVersion+"CV="+currentVersion, Toast.LENGTH_SHORT).show();

            if (latestVersion != null) {
                Version = Float.parseFloat(currentVersion);
                lVersion = Float.parseFloat(latestVersion); //Check condition (latest version is greater than current version)
                if (lVersion > Version) {
                    ShowUpdateDialog();
                }
            }
        }
    }

    private void ShowUpdateDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setMessage("Update Available!");
        builder.setCancelable(true);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + getPackageName())));
                //Dismiss AlertDialog dialog.dismiss();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();

    }
    //end Updata app version==============================================================
}