package com.quickc.config;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() {
        try {
            // Load the service account JSON from resources
            InputStream serviceAccount = new ClassPathResource("firebase-service-account.json").getInputStream();
            
            // Build Firebase options with credentials
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://quickc-ecom-default-rtdb.firebaseio.com/")  // Replace with your DB URL
                    .build();
            
            // Initialize Firebase app if not already done
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            // Handle error if JSON file not found or invalid
            e.printStackTrace();
        }
    }
}