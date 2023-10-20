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
                .setTitle(R.string.borrar_notas)
                .setMessage(R.string.est_s_seguro_de_que_quieres_borrar_las_notas)
                .setPositiveButton(R.string.borrar, (dialog, which) -> {
                    // Delete the note
                    mainActivity.deleteFile();
                })
                .setNegativeButton(R.string.cancelar, (dialog, which) -> {
                    dismiss();
                })
                .create();
    }

}
