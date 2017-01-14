package com.jenny.myhome;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by JennyPash on 1/13/2017.
 */

public class Prompts {
    private Context context;
    private EditText userInput;

    private AlertDialog.Builder alertDialogBuilder;
    private OnOkListener listener;

    public Prompts(Context context, String label, String initialValue) {
        this.context = context;

        LayoutInflater li = LayoutInflater.from(this.context);
        View promptsView = li.inflate(R.layout.prompts, null);

        this.alertDialogBuilder = new AlertDialog.Builder(this.context);
        this.alertDialogBuilder.setView(promptsView);

        TextView tv = (TextView)promptsView.findViewById(R.id.prompts_label);
        tv.setText(label);

        this.userInput = (EditText) promptsView.findViewById(R.id.prompts_input);
        this.userInput.setText(initialValue);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.save),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                if (listener != null) {
                                    listener.onOkClick(userInput.getText().toString());
                                }
                            }
                        })
                .setNegativeButton(context.getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
    }

    public void show() {
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void setOnOkListener(OnOkListener listener) {
        this.listener = listener;
    }

    public interface OnOkListener {
        void onOkClick(String v);
    }
}
