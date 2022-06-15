package Semi.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String memberNickname;
	private String boardContent;
	private int readCount;
	private String createDate;
	private String updateDate;

	private int boardCode;
	private String boardType;
	private String goodCount; // 좋아요 갯수
	
}