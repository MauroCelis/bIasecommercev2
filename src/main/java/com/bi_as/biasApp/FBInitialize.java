package com.bi_as.biasApp;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class FBInitialize {
    @PostConstruct
    public void initialize() {
        try {

            FileInputStream serviceAccount =
                    new FileInputStream("C://Users/ASUS/Desktop/Octavo semestre/Taller de sistemas de informacion/v2biasbackend/BI-AS-app-backend-master/src/main/java/com/bi_as/biasApp/kuinbi-cejdjm-firebase-adminsdk-mt6kj-e1eb7e1317.json");
            if(serviceAccount!=null){
                System.out.println("El archivo si fue encontrado");
            }
            else {
                System.out.println("El archivo no se encptro :(");
            }

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://kuinbi-cejdjm.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Firestore getFirebase() {
        return FirestoreClient.getFirestore();
    }

}
