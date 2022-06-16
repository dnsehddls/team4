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
	private String createDate;

}
