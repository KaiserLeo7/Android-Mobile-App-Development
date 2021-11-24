package com.example.assignment1;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz {

    ArrayList<QandA> qContainer = new ArrayList<QandA>();
    ArrayList<Integer> cContainer = new ArrayList<>();

    public Quiz() {

        cContainer.add(R.color.Red);
        cContainer.add(R.color.Blue);
        cContainer.add(R.color.Green);
        cContainer.add(R.color.Yellow);
        cContainer.add(R.color.Orange);
        cContainer.add(R.color.Purple);

        Collections.shuffle(cContainer);

        qContainer.add(new QandA(R.string.Yoda,false, cContainer.get(0)));          //false
        qContainer.add(new QandA(R.string.Vader,true, cContainer.get(1)));         //true
        qContainer.add(new QandA(R.string.Sith,false, cContainer.get(2)));          //false
        qContainer.add(new QandA(R.string.Wookie,true, cContainer.get(3)));         //true
        qContainer.add(new QandA(R.string.HanSolo,true, cContainer.get(4)));        //true
        qContainer.add(new QandA(R.string.Leia,true, cContainer.get(5)));
    }

    //GETTERS
    //returns only the string question a given question number (the iterated number of questions asked)
    public int getQuestion(int qIndex) {
        return qContainer.get(qIndex).question;
    }

    //returns only the boolean answer of a given question number
    public boolean getAnswer(int qIndex) {
        return qContainer.get(qIndex).answer;
    }

    //returns color of a given color index
    public int getColorID(int qIndex) {
        return cContainer.get(qIndex);
    }

    /////// /////// /////// /////// ///////

    //returns the size of the container (the number of questions)
    public int qSize() {
        return qContainer.size();
    }


    //reOrder the questions
    public void qShuffle() {
        Collections.shuffle(qContainer);
        Collections.shuffle(cContainer);
    }
}
