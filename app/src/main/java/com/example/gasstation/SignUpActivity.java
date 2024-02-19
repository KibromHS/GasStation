package com.example.gasstation;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gasstation.utils.AndroidUtil;
import com.example.gasstation.utils.FirebaseUtil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private Button registerBtn;
    private EditText edFullName;
    private ImageView addProfileImage;
    private ProgressBar progressBar;

    private Uri imageUri;

    private final ActivityResultLauncher<Intent> imagePickActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        imageUri = data.getData();
                        addProfileImage.setImageURI(imageUri);
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();

        addProfileImage.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imagePickActivityResultLauncher.launch(intent);
        });

        registerBtn.setOnClickListener(view -> {
            setInProgress(true);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_HH_mm_ss", Locale.ENGLISH);
            Date now = new Date();
            String fileName = formatter.format(now);

            StorageReference ref = FirebaseUtil.getStorageReference("UserImage/" + fileName);
            ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String fullName = edFullName.getText().toString();
                            if (TextUtils.isEmpty(fullName)) {
                                AndroidUtil.showToast(getApplicationContext(), "Fill in your full name");
                                setInProgress(false);
                                return;
                            }

                            Map<String, Object> data = new HashMap<>();
                            data.put("userId", FirebaseUtil.getCurrentUser().getUid());
                            data.put("userName", fullName);
                            data.put("phoneNumber", FirebaseUtil.getCurrentUser().getPhoneNumber());
                            data.put("profileImageUrl", uri.toString());
                            data.put("favoriteStations", FieldValue.arrayUnion());
                            data.put("emergencyContacts", FieldValue.arrayUnion());

                            FirebaseUtil.addUser(data);

                            setInProgress(false);
                            Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            setInProgress(false);
                            AndroidUtil.showToast(getApplicationContext(), "Couldn't get download url");
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    setInProgress(false);
                    AndroidUtil.showToast(getApplicationContext(), "Couldn't upload data");
                }
            });
        });
    }

    private void initView() {
        addProfileImage = findViewById(R.id.addProfileImage);
        edFullName = findViewById(R.id.edFullName);
        registerBtn = findViewById(R.id.registerBtn);
        progressBar = findViewById(R.id.login_progress_bar);
    }

    private void setInProgress(boolean inProgress) {
        if (inProgress) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }
}