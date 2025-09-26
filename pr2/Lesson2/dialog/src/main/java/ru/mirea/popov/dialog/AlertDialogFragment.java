package ru.mirea.popov.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class AlertDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("привет, мой любиый и неповторимый мирэа")
                .setMessage("успех близок?")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("дальше", (dialog, id) -> {
                    ((MainActivity) getActivity()).onOkClicked();
                    dialog.dismiss();
                })
                .setNeutralButton("пауза", (dialog, id) -> {
                    ((MainActivity) getActivity()).onNeutralClicked();
                    dialog.dismiss();
                })
                .setNegativeButton("нее", (dialog, id) -> {
                    ((MainActivity) getActivity()).onCancelClicked();
                    dialog.dismiss();
                });
        return builder.create();
    }
}
