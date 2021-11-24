package com.example.assignment1;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextFragment extends Fragment {

    int id = 0;
    int color = R.color.Blue;

    public TextFragment() {
        //required empty
    }

    public static TextFragment newInstant(int  text, int color) {
        TextFragment fragment = new TextFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("text", text);
        bundle.putInt("color", color);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            id = getArguments().getInt("text");
            color = getArguments().getInt("color");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text, container, false);

        TextView QTextView = (TextView) view.findViewById(R.id.QText);

        if (getArguments() != null) {

            QTextView.setText(getActivity().getResources().getString(id));

            QTextView.setBackgroundColor(getActivity().getResources().getColor(color));
        }

        return view;
    }


}