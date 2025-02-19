package lookids.mono.common.kafka;

// @Slf4j
// @EnableKafka
// @Configuration
public class FeedKafkaConfig {

	// @Value("${spring.kafka.bootstrap-servers}")
	// private String bootstrapServer;
	//
	// @Bean
	// public Map<String, Object> feedProducerConfigs() {
	// 	Map<String, Object> producerProps = new HashMap<>();
	// 	producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
	// 	producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	// 	producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	// 	return producerProps;
	// }
	//
	// @Bean
	// public ProducerFactory<String, FeedKafkaDto> createFeedNotification() {
	// 	return new DefaultKafkaProducerFactory<>(feedProducerConfigs());
	// }
	//
	// @Bean
	// public KafkaTemplate<String, FeedKafkaDto> feedkafkaTemplate() {
	// 	return new KafkaTemplate<>(createFeedNotification());
	// }
	//
	// @Bean
	// public Map<String, Object> feedDeleteProducerConfigs() {
	// 	Map<String, Object> producerProps = new HashMap<>();
	// 	producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
	// 	producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	// 	producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	// 	return producerProps;
	// }
	//
	// @Bean
	// public ProducerFactory<String, DeleteKafkaDto> DeleteFeedNotification() {
	// 	return new DefaultKafkaProducerFactory<>(feedProducerConfigs());
	// }
	//
	// @Bean
	// public KafkaTemplate<String, DeleteKafkaDto> deletekafkaTemplate() {
	// 	return new KafkaTemplate<>(DeleteFeedNotification());
	// }
	//
	// @Bean
	// public ProducerFactory<String, TargetRequestKafkaDto> recommendKafka() {
	// 	return new DefaultKafkaProducerFactory<>(feedProducerConfigs());
	// }
	//
	// @Bean
	// public KafkaTemplate<String, TargetRequestKafkaDto> recommendKafkaTemplate() {
	// 	return new KafkaTemplate<>(recommendKafka());
	// }
}