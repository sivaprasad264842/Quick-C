package com.quickc.repository;

import com.google.firebase.database.*;
import com.quickc.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final DatabaseReference dbRef;

    public UserRepository() {
        // Get reference to 'users' node in Firebase
        dbRef = FirebaseDatabase.getInstance().getReference("users");
    }

    public void saveUser(User user) {
        // Save user to Firebase with auto-generated ID if null
        if (user.getId() == null) {
            user.setId(dbRef.push().getKey());  // Generate unique key
        }
        dbRef.child(user.getId()).setValueAsync(user);  // Async save for realtime
    }

    public User findByEmail(String email) {
        // This is synchronous for simplicity; use listeners for realtime updates
        final User[] user = new User[1];
        dbRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Handle data snapshot
                if (snapshot.exists()) {
                    user[0] = snapshot.getChildren().iterator().next().getValue(User.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Error handling
            }
        });
        return user[0];
    }

    // Similar methods for orders, products (hardcode products initially)
}
