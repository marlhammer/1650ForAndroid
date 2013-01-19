package com.smouring.android.psalterapp;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author Stephen Mouring
 */
public class ViewPsalm extends Activity {
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

	setContentView(R.layout.viewpsalm);

/*
int selectedBook  = Integer.parseInt(getIntent().getStringExtra("selectedBook"));
int selectedPsalm = Integer.parseInt(getIntent().getStringExtra("selectedPsalm"));

TextView psalmDisplay = (TextView)findViewById(R.id.psalmdisplay);

String[] psalmVerses = psalms[selectedPsalm - 1];

StringBuilder psalmText = new StringBuilder();
for (int i = 0; i < psalmVerses.length; ++i) {
	psalmText.append(psalmVerses[i]);
}

psalmDisplay.setText(Html.fromHtml(psalmText.toString()));

psalmDisplay.setMovementMethod(new ScrollingMovementMethod());
*/

  }
}
