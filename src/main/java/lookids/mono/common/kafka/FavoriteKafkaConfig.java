package lookids.mono.common.kafka;

// @EnableKafka
// @Configuration
public class FavoriteKafkaConfig {
	//
	// @Value("${spring.kafka.bootstrap-servers}")
	// private String bootstrapServers;
	//
	// // 배치용 카프카
	// @Bean
	// public KafkaTemplate<String, FavoriteBatchDto> kafkaTemplateForBatch(
	// 	ProducerFactory<String, FavoriteBatchDto> producerFactory) {
	// 	return new KafkaTemplate<>(producerFactory);
	// }
	//
	// @Bean
	// public ProducerFactory<String, FavoriteBatchDto> producerFactoryForBatch() {
	// 	Map<String, Object> configProps = new HashMap<>();
	// 	configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	// 	configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	// 	configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // JSON 직렬화 설정
	// 	return new DefaultKafkaProducerFactory<>(configProps);
	// }
	//
	// //피드용 카프카
	// @Bean
	// public KafkaTemplate<String, FavoriteFeedDto> kafkaTemplateForFeed(
	// 	ProducerFactory<String, FavoriteFeedDto> producerFactory) {
	// 	return new KafkaTemplate<>(producerFactory);
	// }
	//
	// @Bean
	// public ProducerFactory<String, FavoriteFeedDto> producerFactoryForFeed() {
	// 	Map<String, Object> configProps = new HashMap<>();
	// 	configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	// 	configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	// 	configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // JSON 직렬화 설정
	// 	return new DefaultKafkaProducerFactory<>(configProps);
	// }
	//
	// // 알림용 카프카
	// @Bean
	// public KafkaTemplate<String, FavoriteNotificationDto> kafkaTemplateForNotification(
	// 	ProducerFactory<String, FavoriteNotificationDto> producerFactory) {
	// 	return new KafkaTemplate<>(producerFactory);
	// }
	//
	// @Bean
	// public ProducerFactory<String, FavoriteNotificationDto> producerFactoryForNotification() {
	// 	Map<String, Object> configProps = new HashMap<>();
	// 	configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	// 	configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	// 	configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // JSON 직렬화 설정
	// 	return new DefaultKafkaProducerFactory<>(configProps);
	// }
	//
	// //아래가 리슨
	// // 피드용 리스너
	// @Bean
	// public ConsumerFactory<String, FeedKafkaRequestDto> favoriteConsumerFactory() {
	// 	Map<String, Object> props = new HashMap<>();
	// 	// Kafka 브로커 주소 설정
	// 	props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	// 	// Consumer 그룹 ID 설정
	// 	// 같은 그룹의 consumer들은 토픽의 파티션을 분배하여 메시지를 소비
	// 	props.put(ConsumerConfig.GROUP_ID_CONFIG, "favorite-feed");
	// 	// 메시지 키의 역직렬화 설정 (String 타입)
	// 	props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	// 	// 메시지 값의 역직렬화 설정 (JSON -> KafkaFeedRequestDto)
	// 	props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	// 	// JSON을 자바 객체로 변환할 때 신뢰할 패키지 설정 ("*"는 모든 패키지 허용)
	// 	props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
	//
	// 	// Consumer Factory 생성
	// 	// StringDeserializer: 키를 String으로 역직렬화
	// 	// ErrorHandlingDeserializer: 역직렬화 실패 시 에러 처리
	// 	// JsonDeserializer: JSON을 KafkaFeedRequestDto로 변환
	// 	return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
	// 		new ErrorHandlingDeserializer<>(new JsonDeserializer<>(FeedKafkaRequestDto.class, false)));
	// }
	//
	// @Bean
	// public ConcurrentKafkaListenerContainerFactory<String, FeedKafkaRequestDto> feedFavoriteEventListenerContainerFactory() {
	// 	// @KafkaListener 어노테이션이 사용할 Factory 설정
	// 	ConcurrentKafkaListenerContainerFactory<String, FeedKafkaRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
	// 	factory.setConsumerFactory(favoriteConsumerFactory());
	// 	return factory;
	// }

}