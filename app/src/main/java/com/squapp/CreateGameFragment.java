package com.squapp;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by JdRod on 28/05/2017.
 */

public class CreateGameFragment extends Fragment {

    Button create;
    Spinner levelSpinner;
    DatePicker datePicker;
    TimePicker timePickerInit;
    TimePicker timePickerFinish;
    EditText playersText;

    String placeID;
    String date;
    String starTime;
    String finishTime;
    String level;
    String state = "Por jugar"; //default
    String currentPlayer ="1"; //default
    String maxPlayers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_game_layout, container,false);
        placeID = ((MapsActivity)getActivity()).getId();
        initViews(v);
        initDate();
        validateButton();
        return v;
    }

    private void initViews(View v){
        create = (Button) v.findViewById(R.id.button_create);
        levelSpinner = (Spinner) v.findViewById(R.id.spinner_level);
        datePicker = (DatePicker) v.findViewById(R.id.date_game_pick);
        timePickerInit = (TimePicker) v.findViewById(R.id.picker_starting);
        timePickerFinish = (TimePicker) v.findViewById(R.id.picker_finish);
        playersText = (EditText) v.findViewById(R.id.number_players);
    }
    private void validateButton(){
        create.setOnClickListener(new View.OnClickListener() {
            Toast toast;
            @Override
            public void onClick(View v) {
                if(!validateHour()){
                    toast = Toast.makeText(getActivity().getBaseContext(),"Not a valid hour!",Toast.LENGTH_LONG);
                    toast.show();
                }else if(!validatePlayers()){
                    toast = Toast.makeText(getActivity().getBaseContext(),"Number of player too low!",Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    getDate();
                    getInitHour();
                    getFinishHour();
                    getPlayers();
                    getSpinner();
                    sendNewGame2DB();
                    toast = Toast.makeText(getActivity().getBaseContext(),"Game created!",Toast.LENGTH_LONG);
                    toast.show();
                    ((MapsActivity)getActivity()).viewGamesFragmentTransition();
                }
            }
        });
    }
    private void sendNewGame2DB(){

    }
    private void initDate(){
        datePicker.setMinDate(System.currentTimeMillis()-1000);
    }
    private void getInitHour(){
        starTime = timePickerInit.getCurrentHour()+":"+timePickerInit.getCurrentMinute();
    }
    private void getFinishHour(){
        finishTime = timePickerFinish.getCurrentHour()+":"+timePickerFinish.getCurrentMinute();
    }
    private void getPlayers(){
        maxPlayers = playersText.getText().toString();
    }
    private void getDate(){
        date = datePicker.getDayOfMonth() +"/" + datePicker.getMonth() +"/"+ datePicker.getYear();
    }
    private boolean validateHour(){
        return timePickerFinish.getCurrentHour() > timePickerInit.getCurrentHour();
    }
    private boolean validatePlayers(){
        return Integer.parseInt(playersText.getText().toString()) >= 1;
    }
    private void getSpinner(){
        String array[] = getResources().getStringArray(R.array.levels);
        level = array[levelSpinner.getSelectedItemPosition()];
    }
}
