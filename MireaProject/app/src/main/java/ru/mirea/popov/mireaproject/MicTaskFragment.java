package ru.mirea.popov.mireaproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;

public class MicTaskFragment extends Fragment {

    private static final int REQUEST_CODE_PERMISSION = 200;
    private boolean isRecording = false;
    private MediaRecorder recorder;
    private String fileName;
    private TextView textResult;
    private Button btnRecord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mic_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        btnRecord = view.findViewById(R.id.btnRecord);
        textResult = view.findViewById(R.id.textResult);

        int audioPerm = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO);
        if (audioPerm != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_CODE_PERMISSION);
        }

        fileName = new File(requireContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC),
                "mic_task_record.3gp").getAbsolutePath();

        btnRecord.setOnClickListener(v -> {
            if (!isRecording) {
                startRecording();
                btnRecord.setText("Остановить запись");
                textResult.setText("Идёт запись...");
            } else {
                stopRecording();
                btnRecord.setText("Начать запись");
                textResult.setText("Запись завершена");
            }
            isRecording = !isRecording;
        });
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;
    }
}
