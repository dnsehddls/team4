package Semi.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Board {

	private int boardNo;
	private String boardTitle;
	private String memberNickname;
	private String boardContent;
	private int readCount;
	private String createDate;
	private String boardFlag;
}
