package Semi.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
	
	private int memberNo;
	private String memberID;
	private String memberPW;
	private String memberName;
	private String memberNickname;
	private String memberEmail;
	private String memberTel;
	private String memberProfile;
	private int point;
	private String grade;
	private String registDate;
	private String secessionFlag;
	private String profileImage;
	private int gradeCode;
	private String regDate;
	
}
