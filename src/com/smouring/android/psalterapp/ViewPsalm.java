package com.smouring.android.psalterapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

/**
 * @author Stephen Mouring
 */
public class ViewPsalm extends Activity {
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.viewpsalm);

    int selectedBook = Integer.parseInt(getIntent().getStringExtra("selectedBook"));
    int selectedPsalm = Integer.parseInt(getIntent().getStringExtra("selectedPsalm"));

    TextView psalmText = (TextView) findViewById(R.id.psalmtext);

    psalmText.setText(Html.fromHtml("<b>Book" + selectedBook + "</b><br /><br /><i>" + selectedPsalm + "</b>"));

    psalmText.setMovementMethod(new ScrollingMovementMethod());
  }
}
