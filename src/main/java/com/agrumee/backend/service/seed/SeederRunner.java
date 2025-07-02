package com.agrumee.backend.service.seed;

import com.agrumee.backend.service.seeders.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeederRunner implements CommandLineRunner {

    @Value("${app.seeder.enabled:false}")
    private boolean seederEnabled;

    private final UserSeeder userSeeder;
    private final PlaceSeeder placeSeeder;
    private final EventSeeder eventSeeder;
    private final ParticipationSeeder participationSeeder;
    private final NotificationSeeder notificationSeeder;
    private final TagSeeder tagSeeder;

    public SeederRunner(
            UserSeeder userSeeder,
            PlaceSeeder placeSeeder,
            EventSeeder eventSeeder,
            ParticipationSeeder participationSeeder,
            NotificationSeeder notificationSeeder,
            TagSeeder tagSeeder
    ) {
        this.userSeeder = userSeeder;
        this.placeSeeder = placeSeeder;
        this.eventSeeder = eventSeeder;
        this.participationSeeder = participationSeeder;
        this.notificationSeeder = notificationSeeder;
        this.tagSeeder = tagSeeder;
    }

    @Override
    public void run(String... args) {
        if (!seederEnabled) {
            System.out.println("⏭️ Seeding désactivé");
            return;
        }
        System.out.println("🚀 Lancement du seeding...");

        userSeeder.seed(10);
        placeSeeder.seed(15);
        tagSeeder.seed(10); // 10 COMMUNITY + 10 ACCESSIBILITY
        eventSeeder.seed(5);
        participationSeeder.seed(50);
        notificationSeeder.seed(10);

        System.out.println("✅ Seeding terminé avec succès !");
    }
}
