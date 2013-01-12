package com.smouring.android.psalterapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * @author Stephen Mouring
 */
public class ViewPsalms extends Activity {

  public static final int[] BOOK_RANGES = {41, 72, 89, 106, 150};

  public static final String[] BOOK_NAMES = {
      "Book 1 (1 - 41)",
      "Book 2 (42 - 72)",
      "Book 3 (73 - 89)",
      "Book 4 (90 - 106)",
      "Book 5 (107 - 150)"
  };
  public static final String[][] PSALM_NAMES = new String[BOOK_NAMES.length][];

  static {
    for (int i = 0; i < BOOK_RANGES.length; ++i) {
      int prevBookRange = (i == 0) ? 0 : BOOK_RANGES[i - 1];

      int bookRange = BOOK_RANGES[i];
      int bookRangeLength = bookRange - prevBookRange;

      PSALM_NAMES[i] = new String[bookRangeLength];

      for (int j = 0; j < bookRangeLength; ++j) {
        PSALM_NAMES[i][j] = "Psalm " + (j + 1 + prevBookRange);
      }
    }
  }

  protected int selectedBook = 0;
  protected int selectedPsalm = 0;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.viewpsalms);

    System.setProperty("log.tag.1650ForAndroid", "WARN");

    Spinner chooseBook = (Spinner) findViewById(R.id.choosebook);
    ArrayAdapter<String> bookNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, BOOK_NAMES);
    bookNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    chooseBook.setAdapter(bookNameAdapter);
//    chooseBook.setSelection(0);
    chooseBook.setOnItemSelectedListener(new OnItemSelectedListenerAdapter() {
      public void onItemSelected(AdapterView parent, View view, int pos, long id) {
        selectedBook = Integer.parseInt(parent.getItemAtPosition(pos).toString().split(" ")[1]);

        Spinner choosePsalm = (Spinner) findViewById(R.id.choosepsalm);
        ArrayAdapter<String> psalmNameAdapter = new ArrayAdapter<String>(ViewPsalms.this, android.R.layout.simple_list_item_1, PSALM_NAMES[selectedBook - 1]);
        psalmNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choosePsalm.setAdapter(psalmNameAdapter);
      }
    });

    Spinner choosePsalm = (Spinner) findViewById(R.id.choosepsalm);
    ArrayAdapter<String> psalmAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{""});
    psalmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    choosePsalm.setAdapter(psalmAdapter);
    choosePsalm.setOnItemSelectedListener(new OnItemSelectedListenerAdapter() {
      public void onItemSelected(AdapterView parent, View view, int pos, long id) {
        selectedPsalm = Integer.parseInt(parent.getItemAtPosition(pos).toString().replace("Psalm ", ""));
      }
    });

    Button selectButton = (Button) findViewById(R.id.select);
    selectButton.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        Log.e("1650ForAndroid", "Launching intent for ViewPsalm activity.");

        Intent i = new Intent(ViewPsalms.this, ViewPsalm.class);
        i.putExtra("selectedBook", String.valueOf(selectedBook));
        i.putExtra("selectedPsalm", String.valueOf(selectedPsalm));
        startActivity(i);
      }
    });

  }

  public class OnItemSelectedListenerAdapter implements OnItemSelectedListener {
    public void onItemSelected(AdapterView parent, View view, int pos, long id) {
    }

    public void onNothingSelected(AdapterView parent) {
    }
  }
}
