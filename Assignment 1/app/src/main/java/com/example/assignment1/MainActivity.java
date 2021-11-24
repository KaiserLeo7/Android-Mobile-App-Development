package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    //widgets
    Button trueButton;
    Button falseButton;
    ProgressBar QProgressBar;

    //variables
    String qStringCorrect = "";
    int questionIndex = 0;
    static int totalScore = 0;
    static int averageScore = 0;
    static int correctResponse = 0;
    static int qCorrect = 0;
    static int qIndex = 0;
    static int qTotal = 0;

    Quiz starWarsQuiz = new Quiz();
    Quiz starWarsQuiz2 = new Quiz();

    static int language = 1;
    private Menu menu;

    StorageManager storageM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storageM =  new StorageManager();

        if (savedInstanceState != null) {
            qCorrect = savedInstanceState.getInt("correct");
            qIndex = savedInstanceState.getInt("index");
            qTotal = savedInstanceState.getInt("total");
            language = savedInstanceState.getInt("lang");

            for (int i = 0 ; i < starWarsQuiz2.qSize(); i++) {
                QandA starWarsTemp = savedInstanceState.getParcelable("quiz"+i);
                starWarsQuiz2.qContainer.set(i, starWarsTemp);
            }

            if (language == 2) {  // language 2

                Locale locale = new Locale("fr");
                Locale.setDefault(locale);

                Configuration config = new Configuration();
                config.locale = locale;

                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(getApplicationContext(), "French", Toast.LENGTH_SHORT).show();
                language = 2;

                finish();
                startActivity(getIntent());
            } else {    // language 1

                Locale locale = new Locale("en");
                Locale.setDefault(locale);

                Configuration config = new Configuration();
                config.locale = locale;

                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
                language = 1;

                finish();
                startActivity(getIntent());
            }
        }

        setContentView(R.layout.activity_main);

        //Link the widgets
        trueButton = (Button) findViewById(R.id.True);
        falseButton = (Button) findViewById(R.id.False);
        QProgressBar = (ProgressBar) findViewById(R.id.QProgressBar);

        questionIndex = qIndex;
        correctResponse = qCorrect;

        QProgressBar.setProgress(qIndex);

        if (starWarsQuiz2.qSize() > 0) {
            starWarsQuiz = starWarsQuiz2;
        }

        //set widgets and variables to proper max values
        QProgressBar.setMax(starWarsQuiz.qSize());
        qTotal = starWarsQuiz.qSize();

        SwitchFragment();

        updateMenuTitles();

        //if True button is pressed
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(qIndex < starWarsQuiz.qSize()) {
                   //check if answer is true
                   if (starWarsQuiz.getAnswer(qIndex) == true) {     //if the true button is pressed and the answer returns true
                       qCorrect++;
                       Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();

                   } else {
                       Toast.makeText(MainActivity.this, "inCorrect", Toast.LENGTH_SHORT).show();
                   }

                   // update the progressbar and index
                   qUpdateProgress();

                   if (qIndex == qTotal) {
                       qFinishAlert();

                   try {
                       saveInternal();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }

               } else {
                       //makes a new fragment, takes in data, and replaces the old
                      SwitchFragment();
                   }
               }
            }
        });

        //if False button is pressed
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(qIndex < starWarsQuiz.qSize()) {
                    //check if answer is false
                    if (starWarsQuiz.getAnswer(qIndex) == false) {     //if the false button is pressed and the answer returns false
                        qCorrect++;
                        Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "inCorrect", Toast.LENGTH_SHORT).show();
                    }

                    qUpdateProgress();

                    if (qIndex == qTotal) {
                        qFinishAlert();

                        try {
                            saveInternal();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //makes a new fragment, takes in data, and replaces the old
                        SwitchFragment();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Please Wait", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateMenuTitles() {
        if (menu != null) {
            MenuItem langMenuItem = menu.findItem(R.id.lang_item);
            if (language == 1) { // if app language is 1 then menu to be French
                langMenuItem.setTitle(R.string.French);
            } else {// if app language is 2 then menu to be English
                langMenuItem.setTitle(R.string.English);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.assignment_menu, menu);
        this.menu = menu;
        updateMenuTitles();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (qIndex == 0) {
            switch (item.getItemId()) {
                case R.id.lang_item: {
                    if (item.getTitle().equals("French")) {

                        Locale locale = new Locale("fr");
                        Locale.setDefault(locale);

                        Configuration config = new Configuration();
                        config.locale = locale;

                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                        Toast.makeText(getApplicationContext(), "French", Toast.LENGTH_SHORT).show();
                        language = 2;//app language is French

                        finish();
                        startActivity(getIntent());

                    } else {
                        Locale locale = new Locale("en");
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;

                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                        Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
                        language = 1;// App language is English

                        finish();
                        startActivity(getIntent());
                    }
                }
                case R.id.average: {

                    //try to load the average value
                    try {
                        loadInternal();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                        //create the alert builder
                        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);

                        //Alert at the end of the Test containing (Result, Score, Repeat and Cancel)
                        myAlertBuilder
                                .setTitle("Average Score")
                                .setMessage("Your Average Score is: " + averageScore )

                                //Positive Button to Repeat the Quiz
                                .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        qReset();

                                        Toast.makeText(getApplicationContext(), "Average Reset", Toast.LENGTH_SHORT).show();

                                        try {
                                            resetInternal();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                })

                                //Negative Button to Cancel the Quiz
                                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                      //empty function dismisses dialog
                                    }
                                });
                        //send out the Alert
                        myAlertBuilder.show();
                }
            }
        }else {
            Toast.makeText(getApplicationContext(), "Only at Start", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void qUpdateProgress() {
        qIndex++;
        QProgressBar.setProgress(qIndex);
    }

    //resets the variables, progressBar and also shuffles the questions
    public void qReset() {
        qCorrect = 0;
        qIndex = 0;
        QProgressBar.setProgress(0);
        starWarsQuiz.qShuffle();
    }

    public void  saveInternal() throws IOException  {  // SAVE
        //save score of the attempt
        qStringCorrect = Integer.toString(qCorrect);
        qStringCorrect = qStringCorrect + ",";
        storageM.saveInternal(this, qStringCorrect);
    }

    public void  loadInternal() throws IOException {   // LOAD

        //load in the data string
        String data = storageM.loadFromInternal(this);

        if (data.equals("")) {
            averageScore = 0;
        } else {

            //split the string into a string array
            String str[] = data.split(",");     //separated by a ","

            //create int array size of string array
            int[] numbers = new int[str.length];
            //convert and pass each value from string to int array
            for (int i = 0; i < str.length; i++) {
                numbers[i] = Integer.parseInt(str[i]);
            }
            //reset the loop value
            totalScore = 0;
            //add up the int array
            for (int i = 0; i < numbers.length; i++) {  //number of elements = number of tries
                totalScore += numbers[i];
            }
            //find the average of the int array
            averageScore = totalScore / numbers.length;
        }
    }

    public void  resetInternal() throws IOException {  // SAVE

        storageM.clearInternalData(this);
    }

    //makes a new fragment, and replaces the old
    public void SwitchFragment() {

        TextFragment fragment = TextFragment
                .newInstant(starWarsQuiz.getQuestion(qIndex),starWarsQuiz.getColorID(qIndex));

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.question_fragment, fragment).addToBackStack(null).commit();
    }

    public void qFinishAlert() {
        //create the alert builder
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);

        //Alert at the end of the Test containing (Result, Score, Repeat and Cancel)
        myAlertBuilder
                .setTitle("Result")
                .setMessage("Your Score is: " + qCorrect + " out of " + qTotal)

                //Positive Button to Repeat the Quiz
                .setPositiveButton("Repeat", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        qReset();
                        SwitchFragment();
                    }
                })

                //Negative Button to Cancel the Quiz
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     //reset all the values
                     qReset();

                    //turn off the app
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                    }
                });

        //send out the Alert
        myAlertBuilder.show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putInt("correct",qCorrect);
        outState.putInt("index",qIndex);
        outState.putInt("total",qTotal);
        outState.putInt("lang", language);

        for (int i = 0 ; i < 6; i++) {
            outState.putParcelable("quiz"+i, starWarsQuiz.qContainer.get(i));
        }
        super.onSaveInstanceState(outState);
    }
}