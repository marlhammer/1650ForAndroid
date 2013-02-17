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

import static com.smouring.android.psalterapp.Constants.*;

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

  private int selectedBook = 1;
  private int selectedPsalm = 1;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.viewpsalms);

    selectedBook = 1;
    selectedPsalm = 1;
    if (savedInstanceState == null) {
      Log.i("1650ForAndroid", "No saved instance state.");
    } else {
      Log.i("1650ForAndroid", "Restoring saved instance state.");
      if (savedInstanceState.containsKey(SELECTED_BOOK_KEY) && savedInstanceState.containsKey(SELECTED_PSALM_KEY)) {
        selectedBook = savedInstanceState.getInt(SELECTED_BOOK_KEY);
        Log.i("1650ForAndroid", SELECTED_BOOK_KEY + " - " + selectedBook);
        selectedPsalm = savedInstanceState.getInt(SELECTED_PSALM_KEY);
        Log.i("1650ForAndroid", SELECTED_PSALM_KEY + " - " + selectedPsalm);
      } else {
        Log.e("1650ForAndroid", "No valid keys stored in saved instance state!");
      }
    }

    System.setProperty("log.tag.1650ForAndroid", "INFO");

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

        if (selectedBook == 1) {
          choosePsalm.setSelection((selectedPsalm-1));
        } else {
          choosePsalm.setSelection((selectedPsalm-1)-BOOK_RANGES[(selectedBook-1)-1]);
        }
      }
    });
    chooseBook.setSelection(selectedBook-1);

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
        i.putExtra(SELECTED_BOOK_KEY,  selectedBook);
        i.putExtra(SELECTED_PSALM_KEY, selectedPsalm);
        startActivity(i);
      }
    });
  }

  public void onSaveInstanceState(Bundle savedInstanceState) {
    Log.i("1650ForAndroid", "onSaveInstanceState listener fired.");
    savedInstanceState.putInt(SELECTED_BOOK_KEY, selectedBook);
    savedInstanceState.putInt(SELECTED_PSALM_KEY, selectedPsalm);
  }

  public class OnItemSelectedListenerAdapter implements OnItemSelectedListener {
    public void onItemSelected(AdapterView parent, View view, int pos, long id) {
    }

    public void onNothingSelected(AdapterView parent) {
    }
  }
}
