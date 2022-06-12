package Semi.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberNickname;
	private String memberEmail;
	private String memberTel;
	private String memberProfile;
	private int point;
	private String grade;
	private String registDate;
	private char secessionFlag;
	private String profileImage;
	private int gradeCode;
	private String regDate;
	
}
