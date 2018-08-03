package com.mag.moponseoslaskuri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.app.AlertDialog;
import android.widget.SeekBar;
import android.widget.TextView;
import  android.content.SharedPreferences;


public class MainActivity extends AppCompatActivity {
    SeekBar seosvalue;
    TextView seoslabel;
    public double seos;
    public static final String Seokset = "MOPON_SEOKSET_SHARED_PREFRENCES";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seosvalue=(SeekBar) findViewById(R.id.seekBar2);
        seoslabel= findViewById(R.id.visualseos);
        SharedPreferences prefs = getSharedPreferences(Seokset, MODE_PRIVATE);
        String temp = prefs.getString("seosmaara", "2.0");
        seoslabel.setText(temp + "%");
        seos =  Double.parseDouble(temp);
        seos = seos * 4 - 6;
        int seekseos= (int) seos;
        seosvalue.setProgress(seekseos);
        seosvalue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seos = (double) i;

                seos = seos / 4 + 1.5;
                seoslabel.setText(seos+"" + "%");

                SharedPreferences.Editor editor = getSharedPreferences(Seokset, MODE_PRIVATE).edit();
                editor.putString("seosmaara", seos+"");
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void buttonOnClick(View v) {
// do something when the button is clicked
      try {
        double gasAmount = Double.parseDouble(getText());
        gasAmount = gasAmount * (seos / 10) * 100;

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(String.format("%.0f", gasAmount) + " millilitraa.");
        dlgAlert.setTitle("Seokset:");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
      }
      catch (Exception e)
        {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Taisit nyt tehdä virheen!");
            dlgAlert.setTitle("Virhe tapahtui!");
            dlgAlert.setPositiveButton("Jaahas...", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
    }
    public String getText() {
        textView=(TextView)findViewById(R.id.litra);
        String text = textView.getText().toString();
        return  text;
    }
}
