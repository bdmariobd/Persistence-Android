package es.mario.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.color.DynamicColors;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String MI_FICHERO = "notes.txt";
    TextView text;
    TextView noteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DynamicColors.applyToActivityIfAvailable(this);

        text = findViewById(R.id.textInputEditText);
        noteTextView = findViewById(R.id.noteTexView);

        // Check if the file exists, and if not, create it
        if (!fileExists()) {
            createFile();
        }

        try {
            noteTextView.setText(readFile(MI_FICHERO));
        } catch (IOException e) {
            Log.e("IOException", "Exception in readFile(): " + e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            noteTextView.setText(readFile(MI_FICHERO));
        } catch (IOException e) {
            Log.e("IOException", "Exception in readFile(): " + e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.opcBorrar) {
            manageClickDeleteButton();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void manageClickDeleteButton() {
        DialogDelete dialogDelete = new DialogDelete();
        dialogDelete.show(getSupportFragmentManager(), "DialogDelete");
    }


    public String readFile(String filename) throws IOException {
        BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput(filename)));
        StringBuilder fileContent = new StringBuilder();
        String linea = fin.readLine();
        while (linea != null) {
            fileContent.append(linea).append("\n");
            linea = fin.readLine();
        }
        fin.close();

        Log.d("Ficheros", "File read! Content: " + fileContent);

        return fileContent.toString();
    }

    public void onClickButton(View v) throws IOException {
        String nextText = this.text.getText().toString();
        appendToFile(nextText);
        noteTextView.setText(readFile(MI_FICHERO));
    }



    private void createFile() {
        try {
            FileOutputStream fos = openFileOutput(MainActivity.MI_FICHERO, Context.MODE_PRIVATE);
            fos.close();
        } catch (IOException e) {
            Log.e("IOException", "Exception in create new File(" + MainActivity.MI_FICHERO + "): " + e);
        }
    }

    public void deleteFile() {
        if (fileExists()) {
            createFile();
        }
        noteTextView.setText("");
    }

    private void appendToFile(String content) {
        try {
            FileOutputStream fos = openFileOutput(MainActivity.MI_FICHERO, Context.MODE_APPEND);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            Log.e("IOException", "Exception in writing to File(" + MainActivity.MI_FICHERO + "): " + e);
        }
    }

    private boolean fileExists() {
        String[] files = fileList();
        for (String file : files) {
            if (file.equals(MainActivity.MI_FICHERO)) {
                return true;
            }
        }
        return false;
    }
}
