package Semi.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MyBoard {
	
	private int boardNo;
	private String boardName;
	private String boardTitle;
	private String createDate; // 게시글 작성일
	private String createReplyDate; // 댓글 작성일
	private int replyNo;
	private String replyContent;
	private String memberName;
	private String memberNickname;
	private int rNum;
	private int boardType;
}