package ru.mirea.popov.firebaseauth;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FirebaseAuth";
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private TextView statusTextView, detailTextView;
    private EditText emailField, passwordField;
    private Button createAccountButton, signInButton, signOutButton, verifyEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        statusTextView = findViewById(R.id.statusTextView);
        detailTextView = findViewById(R.id.detailTextView);
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        createAccountButton = findViewById(R.id.createAccountButton);
        signInButton = findViewById(R.id.signInButton);
        signOutButton = findViewById(R.id.signOutButton);
        verifyEmailButton = findViewById(R.id.verifyEmailButton);

        createAccountButton.setOnClickListener(v -> createAccount());
        signInButton.setOnClickListener(v -> signIn());
        signOutButton.setOnClickListener(v -> signOut());
        verifyEmailButton.setOnClickListener(v -> sendEmailVerification());
    }

    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void createAccount() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (email.isEmpty() || password.isEmpty()) return;

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        currentUser = mAuth.getCurrentUser();
                        updateUI(currentUser);
                    } else {
                        Toast.makeText(this, "Create failed", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void signIn() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        currentUser = mAuth.getCurrentUser();
                        updateUI(currentUser);
                    } else {
                        Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private void sendEmailVerification() {
        verifyEmailButton.setEnabled(false);
        currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            currentUser.sendEmailVerification().addOnCompleteListener(task -> {
                verifyEmailButton.setEnabled(true);
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Verification sent to " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to send verification", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            statusTextView.setText("Email: " + user.getEmail() + " (verified: " + user.isEmailVerified() + ")");
            detailTextView.setText("UID: " + user.getUid());
            emailField.setVisibility(View.GONE);
            passwordField.setVisibility(View.GONE);
            createAccountButton.setVisibility(View.GONE);
            signInButton.setVisibility(View.GONE);
            signOutButton.setVisibility(View.VISIBLE);
            verifyEmailButton.setVisibility(View.VISIBLE);
            verifyEmailButton.setEnabled(!user.isEmailVerified());
        } else {
            statusTextView.setText("Signed Out");
            detailTextView.setText("");
            emailField.setVisibility(View.VISIBLE);
            passwordField.setVisibility(View.VISIBLE);
            createAccountButton.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.VISIBLE);
            signOutButton.setVisibility(View.GONE);
            verifyEmailButton.setVisibility(View.GONE);
        }
    }
}
