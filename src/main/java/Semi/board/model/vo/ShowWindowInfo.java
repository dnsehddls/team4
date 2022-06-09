package Semi.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ShowWindowInfo {
	
	private int boardNo;
	private String boardTitle;
	private int likeCount;
	private int replyCount;
	private String memberNick;
	private String date;
	
	private int boardType;
	
	
}
