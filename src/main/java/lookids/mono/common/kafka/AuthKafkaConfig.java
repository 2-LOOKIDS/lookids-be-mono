package lookids.mono.common.kafka;

//@Configuration
public class AuthKafkaConfig {
	// @Value("${spring.kafka.bootstrap-servers}")
	// private String bootstrapServers;
	//
	// @Bean
	// public Map<String, Object> accountSoftDeleteProducerConfigs() {
	// 	Map<String, Object> producerProps = new HashMap<>();
	// 	producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	// 	producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	// 	producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	// 	return producerProps;
	// }
	//
	// @Bean
	// public ProducerFactory<String, AccountDeleteKafkaRequestDto> softDeleteAccountNotification() {
	// 	return new DefaultKafkaProducerFactory<>(accountSoftDeleteProducerConfigs());
	// }
	//
	// @Bean
	// public KafkaTemplate<String, AccountDeleteKafkaRequestDto> kafkaAccountDeleteTemplate() {
	// 	return new KafkaTemplate<>(softDeleteAccountNotification());
	// }
}
