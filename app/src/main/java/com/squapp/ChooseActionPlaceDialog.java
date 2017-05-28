package com.squapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

/**
 * Created by JdRod on 28/05/2017.
 */

public class ChooseActionPlaceDialog extends DialogFragment {

    public static ChooseActionPlaceDialog createDialog(){
        ChooseActionPlaceDialog dialog = new ChooseActionPlaceDialog();
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.choose_action_dialog, null);
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.choose)
                .setView(dialogView)
                .setPositiveButton(R.string.view_games, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MapsActivity)getActivity()).viewGamesFragmentTransition();
                    }
                })
                .setNegativeButton(R.string.create_game, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MapsActivity)getActivity()).createGameFragmentTransition();
                    }
                }).create();
    }
}

