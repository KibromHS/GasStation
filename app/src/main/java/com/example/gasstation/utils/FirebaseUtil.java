package com.example.gasstation.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

public class FirebaseUtil {
    public static FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public static DocumentReference currentUserDetails() {
        return FirebaseFirestore.getInstance().collection("Users").document(getCurrentUser().getUid());
    }

    public static void addUser(Map<String, Object> data) {
        FirebaseFirestore.getInstance().collection("Users").add(data);
    }

    public static StorageReference getStorageReference(String location) {
        return FirebaseStorage.getInstance().getReference(location);
    }
}
