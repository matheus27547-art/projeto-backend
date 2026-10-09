package br.edu.fiec.helptec.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    // Conteúdo COMPLETO do JSON da service account, vindo de variável de ambiente
    @Value("${app.firebase.credentials-json}")
    private String credentialsJson;

    @PostConstruct
    public void initialize() {
        if (credentialsJson == null || credentialsJson.isBlank()) {
            throw new IllegalStateException(
                    "Variável de ambiente FIREBASE_CREDENTIALS_JSON não definida ou vazia");
        }

        try (InputStream serviceAccount =
                     new ByteArrayInputStream(credentialsJson.getBytes(StandardCharsets.UTF_8))) {

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao inicializar o Firebase", e);
        }
    }
}