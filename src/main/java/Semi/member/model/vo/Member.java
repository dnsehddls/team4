package Semi.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberNickname;
	private String memberEmail;
	private String memberTel;
	private String profileImage;
	private int gradeCode;
	private int point;
	private String regDate;
	private String secessionFlag;
	private String secessionDate;
	
	
}
