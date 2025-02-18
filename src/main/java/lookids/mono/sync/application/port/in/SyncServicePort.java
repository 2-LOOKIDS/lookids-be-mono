package lookids.mono.sync.application.port.in;

import lookids.mono.sync.application.port.dto.ChatDto;
import lookids.mono.sync.application.port.dto.CommentDto;
import lookids.mono.sync.application.port.dto.FavoriteDto;
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
}
