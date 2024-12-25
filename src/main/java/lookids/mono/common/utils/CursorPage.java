package lookids.mono.common.utils;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CursorPage<T> {
	private List<T> content;
	private String nextCursor;
	private Boolean hasNext;
	private Integer pageSize;
	private Integer page;

	@Builder
	public CursorPage(List<T> content, String nextCursor, Boolean hasNext, Integer pageSize, Integer page) {
		this.content = content;
		this.nextCursor = nextCursor;
		this.hasNext = hasNext;
		this.pageSize = pageSize;
		this.page = page;
	}

	public boolean hasNext() {
		return nextCursor != null;
	}
}