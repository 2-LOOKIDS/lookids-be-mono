package lookids.mono.common.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import lookids.mono.feedread.dto.in.BlockKafkaDto;
import lookids.mono.feedread.dto.in.FeedDeleteKafkaDto;
import lookids.mono.feedread.dto.in.FeedKafkaDto;
import lookids.mono.feedread.dto.in.PetImageKafkaDto;
import lookids.mono.feedread.dto.in.PetKafkaDto;
import lookids.mono.feedread.dto.in.TargetKafkaDto;
import lookids.mono.feedread.dto.in.UserImageKafkaDto;
import lookids.mono.feedread.dto.in.UserKafkaDto;
import lookids.mono.feedread.dto.in.UserNickNameKafkaDto;
import lookids.mono.feedread.dto.in.UuidKafkaDto;
import lookids.mono.feedread.dto.out.FavoriteResponseDto;
import lookids.mono.feedread.dto.out.FollowResponseDto;

@EnableKafka
@Configuration
public class FeedReadKafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;

	//producer
	@Bean
	public Map<String, Object> feedReadProducerConfigs() {
		Map<String, Object> producerProps = new HashMap<>();
		producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return producerProps;
	}

	@Bean
	public ProducerFactory<String, UuidKafkaDto> FavoriteUuidNotification() {
		return new DefaultKafkaProducerFactory<>(feedReadProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, UuidKafkaDto> favoriteKafkaTemplate() {
		return new KafkaTemplate<>(FavoriteUuidNotification());
	}

	@Bean
	public ProducerFactory<String, UuidKafkaDto> FollowUuidNotification() {
		return new DefaultKafkaProducerFactory<>(feedReadProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, UuidKafkaDto> followKafkaTemplate() {
		return new KafkaTemplate<>(FollowUuidNotification());
	}

	@Bean
	public ProducerFactory<String, UuidKafkaDto> BlockUuidNotification() {
		return new DefaultKafkaProducerFactory<>(feedReadProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, UuidKafkaDto> blockKafkaTemplate() {
		return new KafkaTemplate<>(BlockUuidNotification());
	}

	@Bean
	public ProducerFactory<String, PetKafkaDto> petProfileNotification() {
		return new DefaultKafkaProducerFactory<>(feedReadProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, PetKafkaDto> petKafkaTemplate() {
		return new KafkaTemplate<>(petProfileNotification());
	}

	// @Bean
	// public ProducerFactory<String, TargetRequestKafkaDto> recommendNotification() {
	// 	return new DefaultKafkaProducerFactory<>(feedReadProducerConfigs());
	// }
	//
	// @Bean
	// public KafkaTemplate<String, TargetRequestKafkaDto> recommendKafkaTemplate() {
	// 	return new KafkaTemplate<>(recommendNotification());
	// }

	//consumer
	private Map<String, Object> commonConsumerProps(String groupId) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000");
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "500");
		props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "300000");
		return props;
	}

	private <T> ConsumerFactory<String, T> createConsumerFactory(Class<T> targetType, String groupId) {
		return new DefaultKafkaConsumerFactory<>(commonConsumerProps(groupId), new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(targetType, false)));
	}

	private <T> ConcurrentKafkaListenerContainerFactory<String, T> createListenerContainerFactory(
		ConsumerFactory<String, T> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConsumerFactory<String, FeedKafkaDto> feedConsumerFactory() {
		return createConsumerFactory(FeedKafkaDto.class, "feed-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FeedKafkaDto> feedEventListenerContainerFactory() {
		return createListenerContainerFactory(feedConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, UserKafkaDto> userProfileConsumerFactory() {
		return createConsumerFactory(UserKafkaDto.class, "feed-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserKafkaDto> userProfileEventListenerContainerFactory() {
		return createListenerContainerFactory(userProfileConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, UserNickNameKafkaDto> userNickNameConsumerFactory() {
		return createConsumerFactory(UserNickNameKafkaDto.class, "feed-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserNickNameKafkaDto> userNickNameEventListenerContainerFactory() {
		return createListenerContainerFactory(userNickNameConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, UserImageKafkaDto> userImageConsumerFactory() {
		return createConsumerFactory(UserImageKafkaDto.class, "feed-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserImageKafkaDto> userImageEventListenerContainerFactory() {
		return createListenerContainerFactory(userImageConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, FavoriteResponseDto> favoritefeedConsumerFactory() {
		return createConsumerFactory(FavoriteResponseDto.class, "feed-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FavoriteResponseDto> favoriteEventListenerContainerFactory() {
		return createListenerContainerFactory(favoritefeedConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, FollowResponseDto> followConsumerFactory() {
		return createConsumerFactory(FollowResponseDto.class, "feed-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FollowResponseDto> followEventListenerContainerFactory() {
		return createListenerContainerFactory(followConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, FeedDeleteKafkaDto> DeleteConsumerFactory() {
		return createConsumerFactory(FeedDeleteKafkaDto.class, "feed-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FeedDeleteKafkaDto> deleteEventListenerContainerFactory() {
		return createListenerContainerFactory(DeleteConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, BlockKafkaDto> userBlockConsumerFactory() {
		return createConsumerFactory(BlockKafkaDto.class, "feed-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, BlockKafkaDto> userBlockEventListenerContainerFactory() {
		return createListenerContainerFactory(userBlockConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, PetImageKafkaDto> petConsumerFactory() {
		return createConsumerFactory(PetImageKafkaDto.class, "feed-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, PetImageKafkaDto> petEventListenerContainerFactory() {
		return createListenerContainerFactory(petConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, PetImageKafkaDto> petImageConsumerFactory() {
		return createConsumerFactory(PetImageKafkaDto.class, "feed-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, PetImageKafkaDto> petProfileEventListenerContainerFactory() {
		return createListenerContainerFactory(petImageConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, UuidKafkaDto> accounedeleteConsumerFactory() {
		return createConsumerFactory(UuidKafkaDto.class, "feed-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UuidKafkaDto> accountDeleteEventListenerContainerFactory() {
		return createListenerContainerFactory(accounedeleteConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, TargetKafkaDto> recommendteConsumerFactory() {
		return createConsumerFactory(TargetKafkaDto.class, "feed-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TargetKafkaDto> recommendEventListenerContainerFactory() {
		return createListenerContainerFactory(recommendteConsumerFactory());
	}
}