package com.example.gasstation.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gasstation.models.GasStationModel;
import com.example.gasstation.models.UserModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FirebaseUtil {
    private static final FirebaseAuth auth = FirebaseAuth.getInstance();
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final FirebaseStorage storage = FirebaseStorage.getInstance();

    public static FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }

    public static void signOut() {
        auth.signOut();
    }

    @NonNull
    public static DocumentReference currentUserDetails() {
        return db.collection("Users").document(getCurrentUser().getUid());
    }

    public static void addUser(String location, Map<String, Object> data) {
        db.collection("Users").document(location).set(data);
    }

    public static UserModel getUser() {
        final UserModel[] user = new UserModel[1];
        user[0] = null;

        db.collection("Users").document(getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String userName = documentSnapshot.getString("userName");
                String phoneNumber = documentSnapshot.getString("phoneNumber");
                String profileImageUrl = documentSnapshot.getString("profileImageUrl");
                List<String> favoriteStations = (List<String>) documentSnapshot.get("favoriteStations");
                List<String> emergencyContacts = (List<String>) documentSnapshot.get("emergencyContacts");

                user[0] = new UserModel(getCurrentUser().getUid(), userName, phoneNumber, profileImageUrl, favoriteStations, emergencyContacts);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });

        return user[0];
    }

    @Nullable
    public ArrayList<GasStationModel> getStations() {
        ArrayList<GasStationModel> stations = new ArrayList<>();

        db.collection("Stations").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value == null) return;
                for (DocumentChange change : value.getDocumentChanges()) {
                    if (change.getType().equals(DocumentChange.Type.ADDED)) {
                        Map<String, Object> data = change.getDocument().getData();
                        String stationId = Objects.requireNonNull(data.get("stationId")).toString();
                        String stationName = Objects.requireNonNull(data.get("stationName")).toString();
                        LatLng stationLocation = (LatLng) data.get("location");
                        String imageUrl = Objects.requireNonNull(data.get("imageUrl")).toString();
                        boolean isOpen = (Boolean) data.get("isOpen");
                        String queue = Objects.requireNonNull(data.get("queueLength")).toString();
                        List<Map<String, Object>> ratesReviews = (List<Map<String, Object>>) data.get("ratesReviews");
                        List<Map<String, Object>> fuels = (List<Map<String, Object>>) data.get("fuels");

                        GasStationModel station = new GasStationModel(stationId, stationName, stationLocation, imageUrl, isOpen, queue, ratesReviews, fuels);
                        stations.add(station);
                    }
                }
            }
        });
        return stations.size() == 0 ? null : stations;
    }

    @NonNull
    public static StorageReference getStorageReference(String location) {
        return storage.getReference(location);
    }
}
