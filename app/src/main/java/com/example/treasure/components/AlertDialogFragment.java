package com.example.treasure.components;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.treasure.R;

/**
 * Created by 93432 on 2016/7/13.
 */
public class AlertDialogFragment extends DialogFragment {

    private static final String KEY_TITLE = "title";
    private static final String KEY_MESSAGE= "message";

    public static AlertDialogFragment newInstance(int resTitle, String msg){
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_TITLE, resTitle);
        args.putString(KEY_MESSAGE, msg);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt(KEY_TITLE);
        String msg = getArguments().getString(KEY_MESSAGE);
        return new AlertDialog.Builder(getActivity(), getTheme())
                .setTitle(title)
                .setMessage(msg)
                .setNegativeButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
    }
}
