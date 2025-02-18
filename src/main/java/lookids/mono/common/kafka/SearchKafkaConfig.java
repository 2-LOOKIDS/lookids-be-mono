package lookids.mono.common.kafka;

// @EnableKafka
// @EnableScheduling
// @Configuration
public class SearchKafkaConfig {

	// @Value("${spring.kafka.bootstrap-servers}")
	// private String bootstrapServers;
	//
	// private Map<String, Object> commonConsumerProps(String groupId) {
	// 	Map<String, Object> props = new HashMap<>();
	// 	props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	// 	props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
	// 	props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	// 	props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	// 	props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
	// 	return props;
	// }
	//
	// private <T> ConsumerFactory<String, T> createConsumerFactory(Class<T> targetType, String groupId) {
	// 	return new DefaultKafkaConsumerFactory<>(commonConsumerProps(groupId), new StringDeserializer(),
	// 		new ErrorHandlingDeserializer<>(new JsonDeserializer<>(targetType, false)));
	// }
	//
	// private <T> ConcurrentKafkaListenerContainerFactory<String, T> createListenerContainerFactory(
	// 	ConsumerFactory<String, T> consumerFactory) {
	// 	ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
	// 	factory.setConsumerFactory(consumerFactory);
	// 	return factory;
	// }
	//
	// @Bean
	// public ConsumerFactory<String, KafkaUserCreateDto> UserCreateConsumerFactory() {
	// 	return createConsumerFactory(KafkaUserCreateDto.class, "usercreate-group");
	// }
	//
	// @Bean
	// public ConcurrentKafkaListenerContainerFactory<String, KafkaUserCreateDto> UserCreateContainerFactory() {
	// 	return createListenerContainerFactory(UserCreateConsumerFactory());
	// }
	//
	// @Bean
	// public ConsumerFactory<String, KafkaUserDeleteRequestDto> UserDeleteConsumerFactory() {
	// 	return createConsumerFactory(KafkaUserDeleteRequestDto.class, "userdelete-group");
	// }
	//
	// @Bean
	// public ConcurrentKafkaListenerContainerFactory<String, KafkaUserDeleteRequestDto> UserDeleteContainerFactory() {
	// 	return createListenerContainerFactory(UserDeleteConsumerFactory());
	// }
	//
	// @Bean
	// public ConsumerFactory<String, KafkaUserImageUpdateRequestDto> UserImageUpdateConsumerFactory() {
	// 	return createConsumerFactory(KafkaUserImageUpdateRequestDto.class, "userimageupdate-group");
	// }
	//
	// @Bean
	// public ConcurrentKafkaListenerContainerFactory<String, KafkaUserImageUpdateRequestDto> UserImageUpdateContainerFactory() {
	// 	return createListenerContainerFactory(UserImageUpdateConsumerFactory());
	// }
	//
	// @Bean
	// public ConsumerFactory<String, KafkaUserNicknameUpdateRequestDto> UserNickNameUpdateConsumerFactory() {
	// 	return createConsumerFactory(KafkaUserNicknameUpdateRequestDto.class, "usernicknameupdate-group");
	// }
	//
	// @Bean
	// public ConcurrentKafkaListenerContainerFactory<String, KafkaUserNicknameUpdateRequestDto> UserNicknameUpdateContainerFactory() {
	// 	return createListenerContainerFactory(UserNickNameUpdateConsumerFactory());
	// }
	//
	// @Bean
	// public ConsumerFactory<String, KafkaFeedCreateRequestDto> searchFeedCreateConsumerFactory() {
	// 	return createConsumerFactory(KafkaFeedCreateRequestDto.class, "feedcreate-group");
	// }
	//
	// @Bean
	// public ConcurrentKafkaListenerContainerFactory<String, KafkaFeedCreateRequestDto> searchFeedCreateContainerFactory() {
	// 	return createListenerContainerFactory(searchFeedCreateConsumerFactory());
	// }
	//
	// @Bean
	// public ConsumerFactory<String, KafkaFeedDeleteRequestDto> searchFeedDeleteConsumerFactory() {
	// 	return createConsumerFactory(KafkaFeedDeleteRequestDto.class, "feeddelete-group");
	// }
	//
	// @Bean
	// public ConcurrentKafkaListenerContainerFactory<String, KafkaFeedDeleteRequestDto> searchFeedDeleteContainerFactory() {
	// 	return createListenerContainerFactory(searchFeedDeleteConsumerFactory());
	// }
	//
	// @Bean
	// public ConsumerFactory<String, KafkaPetCreateRequestDto> PetCreateConsumerFactory() {
	// 	return createConsumerFactory(KafkaPetCreateRequestDto.class, "petcreate-group");
	// }
	//
	// @Bean
	// public ConcurrentKafkaListenerContainerFactory<String, KafkaPetCreateRequestDto> PetCreateContainerFactory() {
	// 	return createListenerContainerFactory(PetCreateConsumerFactory());
	// }
	//
	// @Bean
	// public ConsumerFactory<String, KafkaPetUpdateRequestDto> PetUpdateConsumerFactory() {
	// 	return createConsumerFactory(KafkaPetUpdateRequestDto.class, "petupdate-group");
	// }
	//
	// @Bean
	// public ConcurrentKafkaListenerContainerFactory<String, KafkaPetUpdateRequestDto> PetUpdateContainerFactory() {
	// 	return createListenerContainerFactory(PetUpdateConsumerFactory());
	// }
	//
	// @Bean
	// public ConsumerFactory<String, KafkaPetDeleteRequestDto> PetDeleteConsumerFactory() {
	// 	return createConsumerFactory(KafkaPetDeleteRequestDto.class, "petdelete-group");
	// }
	//
	// @Bean
	// public ConcurrentKafkaListenerContainerFactory<String, KafkaPetDeleteRequestDto> PetDeleteContainerFactory() {
	// 	return createListenerContainerFactory(PetDeleteConsumerFactory());
	// }

}
