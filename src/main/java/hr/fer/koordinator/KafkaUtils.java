package hr.fer.koordinator;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.Properties;

public class KafkaUtils {

    public static void resetTopics(String bootstrapServers) {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        try (AdminClient adminClient = AdminClient.create(config)) {
            adminClient.deleteTopics(Collections.singletonList("Register")).all().get();
            adminClient.deleteTopics(Collections.singletonList("Command")).all().get();

            adminClient.createTopics(Collections.singletonList(new NewTopic("Register", 1, (short) 1))).all().get();
            adminClient.createTopics(Collections.singletonList(new NewTopic("Command", 1, (short) 1))).all().get();

            System.out.println("Teme 'Register' i 'Command' su resetirane.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
