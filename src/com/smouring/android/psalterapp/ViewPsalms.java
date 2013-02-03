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

// TODO
//
//  private boolean applySelections = true;

  private int selectedBook = 1;
  private int selectedPsalm = 1;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.viewpsalms);

    System.setProperty("log.tag.1650ForAndroid", "INFO");

// TODO
//
//    applySelections = false;

    String selectedBookParam = getIntent().getStringExtra("selectedBook");
    String selectedPsalmParam = getIntent().getStringExtra("selectedPsalm");
    if (selectedBookParam != null && selectedBookParam.length() > 0) {
      selectedBook = Integer.parseInt(selectedBookParam);
// TODO
//
//      applySelections = true;
      Log.i("1650ForAndroid", "Received selectedBook parameter: [" + selectedBook + "]");
    } else {
      selectedBook = 1;
    }
    if (selectedPsalmParam != null && selectedPsalmParam.length() > 0) {
      selectedPsalm = Integer.parseInt(selectedPsalmParam);
// TODO
//
//      applySelections = true;
      Log.i("1650ForAndroid", "Received selectedPsalm parameter: [" + selectedPsalm + "]");
    } else {
      selectedPsalm = 1;
    }
    Log.i("1650ForAndroid", "selectedBook set to: [" + selectedBook + "]");
    Log.i("1650ForAndroid", "selectedPsalm set to: [" + selectedPsalm + "]");

    Spinner chooseBook = (Spinner) findViewById(R.id.choosebook);
    ArrayAdapter<String> bookNameAdapter = new ArrayAdapter<String>(this, R.layout.psalmselector, BOOK_NAMES);
    bookNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    chooseBook.setAdapter(bookNameAdapter);
    chooseBook.setOnItemSelectedListener(new OnItemSelectedListenerAdapter() {
      public void onItemSelected(AdapterView parent, View view, int pos, long id) {
        Log.i("1650ForAndroid", "chooseBook onItemSelectedListener fired.");

        selectedBook = Integer.parseInt(parent.getItemAtPosition(pos).toString().split(" ")[1]);
        Log.i("1650ForAndroid", "selectedBook set to: [" + selectedBook + "]");

        Spinner choosePsalm = (Spinner) findViewById(R.id.choosepsalm);
        ArrayAdapter<String> psalmNameAdapter = new ArrayAdapter<String>(ViewPsalms.this, R.layout.psalmselector, PSALM_NAMES[selectedBook - 1]);
        psalmNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choosePsalm.setAdapter(psalmNameAdapter);

// TODO
//
//        if (applySelections) {
//          choosePsalm.setSelection(selectedPsalm);
//        }
      }
    });

// TODO
//
//    if (applySelections) {
//      chooseBook.setSelection(selectedBook);
//    }

    Spinner choosePsalm = (Spinner) findViewById(R.id.choosepsalm);
    ArrayAdapter<String> psalmAdapter = new ArrayAdapter<String>(this, R.layout.psalmselector, new String[]{""});
    psalmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    choosePsalm.setAdapter(psalmAdapter);
    choosePsalm.setOnItemSelectedListener(new OnItemSelectedListenerAdapter() {
      public void onItemSelected(AdapterView parent, View view, int pos, long id) {
        Log.i("1650ForAndroid", "choosePsalm onItemSelectedListener fired.");

        selectedPsalm = Integer.parseInt(parent.getItemAtPosition(pos).toString().replace("Psalm ", ""));
      }
    });

    Button selectButton = (Button) findViewById(R.id.select);
    selectButton.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        Log.i("1650ForAndroid", "Launching intent for ViewPsalm activity.");

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
