package lookids.mono.sync.application.port.in;

import java.util.List;

import lookids.mono.sync.application.port.dto.ChatDto;
import lookids.mono.sync.application.port.dto.CommentDto;
import lookids.mono.sync.application.port.dto.FavoriteDto;
import lookids.mono.sync.application.port.dto.FeedDeleteDto;
import lookids.mono.sync.application.port.dto.FeedDto;
import lookids.mono.sync.application.port.dto.FollowDto;
import lookids.mono.sync.application.port.dto.ReplyDto;
import lookids.mono.sync.application.port.dto.UserDeleteDto;
import lookids.mono.sync.application.port.dto.UserProfileDto;

public interface SyncServicePort {
	void userDelete(UserDeleteDto userDeleteDto);

	void createComment(CommentDto commentDto);

	void createReply(ReplyDto replyDto);

	void deleteComment(CommentDto commentCreateDto);

	void deleteReply(ReplyDto replyCreateDto);

	void createChatMessage(ChatDto chatDto);

	void createUserProfile(UserProfileDto userProfileDto);

	void updateUserProfileImage(UserProfileDto userProfileDto);

	void updateUserProfileNickname(UserProfileDto userProfileDto);

	void createFavorite(FavoriteDto favoriteDto);

	void updateFavorite(FavoriteDto favoriteDto);

	void createFeed(FeedDto feedDto);

	void deleteFeed(FeedDeleteDto feedDeleteDto);

	String readImageByPetCode(String petCode);

	List<String> getFollowUuidList(String uuid);

	List<String> getBlockUuidList(String uuid);

	List<String> getFavoriteFeedCodes(String uuid);

	void createFollow(FollowDto followDto);

	void deleteFollow(FollowDto followDto);
}
