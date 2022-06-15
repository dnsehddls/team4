package Semi.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Report {
	
	private int reportNo;
	private String reportContent;
	private int boardNo;
	private int memberNo;

}
