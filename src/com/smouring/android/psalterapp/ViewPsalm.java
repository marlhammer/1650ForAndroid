package com.smouring.android.psalterapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Stephen Mouring
 */
public class ViewPsalm extends Activity {
  private int selectedBook = 0;
  private int selectedPsalm = 0;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.viewpsalm);

    selectedBook = Integer.parseInt(getIntent().getStringExtra("selectedBook"));
    selectedPsalm = Integer.parseInt(getIntent().getStringExtra("selectedPsalm"));

    TextView psalmText = (TextView) findViewById(R.id.psalmtext);

    // TODO: Temporary solution until Lucene is working.
    StringBuilder psalmString = new StringBuilder();
    for (String psalmLine : Constants.PSALMS[selectedPsalm - 1]) {
      psalmString.append(psalmLine);
    }

    psalmText.setText(Html.fromHtml(psalmString.toString()));

    psalmText.setMovementMethod(new ScrollingMovementMethod());

    Button selectButton = (Button) findViewById(R.id.back);
    selectButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Log.i("1650ForAndroid", "Launching intent for ViewPsalms activity.");

        Intent i = new Intent(ViewPsalm.this, ViewPsalms.class);
        i.putExtra("selectedBook", String.valueOf(selectedBook));
        i.putExtra("selectedPsalm", String.valueOf(selectedPsalm));
        startActivity(i);
      }
    });
  }
}
