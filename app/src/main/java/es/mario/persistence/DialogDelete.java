package es.mario.persistence;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DialogDelete extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final MainActivity mainActivity = (MainActivity) requireActivity();

        return new MaterialAlertDialogBuilder(mainActivity)
                .setTitle("Borrar notas")
                .setMessage("¿Estás seguro de que quieres borrar las notas?")
                .setPositiveButton("Borrar", (dialog, which) -> {
                    // Delete the note
                    mainActivity.deleteFile();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    dismiss();
                })
                .create();
    }

}
