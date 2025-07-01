package com.agrumee.backend.service.reset;

import com.agrumee.backend.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleanerRunner implements CommandLineRunner {

    @Value("${app.db.reset:false}")
    private boolean resetEnabled;

    private final ParticipationRepository participationRepository;
    private final NotificationRepository notificationRepository;
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final AccessibilityTagRepository accessibilityTagRepository;
    private final CommunityTagRepository communityTagRepository;

    public DatabaseCleanerRunner(
            ParticipationRepository participationRepository,
            NotificationRepository notificationRepository,
            EventRepository eventRepository,
            PlaceRepository placeRepository,
            UserRepository userRepository,
            AccessibilityTagRepository accessibilityTagRepository,
            CommunityTagRepository communityTagRepository
    ) {
        this.participationRepository = participationRepository;
        this.notificationRepository = notificationRepository;
        this.eventRepository = eventRepository;
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.accessibilityTagRepository = accessibilityTagRepository;
        this.communityTagRepository = communityTagRepository;
    }

    @Override
    public void run(String... args) {
        if (!resetEnabled) {
            System.out.println("⏭️ Nettoyage désactivé (flag désactivé)");
            return;
        }

        System.out.println("🧹 Réinitialisation de la base de données...");

        // IMPORTANT : respecter l’ordre pour éviter erreurs de contraintes FK
        participationRepository.deleteAll();
        notificationRepository.deleteAll();
        eventRepository.deleteAll();
        placeRepository.deleteAll();
        userRepository.deleteAll();
        accessibilityTagRepository.deleteAll();
        communityTagRepository.deleteAll();

        System.out.println("✅ Base de données réinitialisée !");
    }
}
