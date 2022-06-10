package Semi.member.model.vo;

<<<<<<< HEAD
=======
import lombok.AllArgsConstructor;
>>>>>>> origin/BSH
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
<<<<<<< HEAD
@NoArgsConstructor
@ToString
public class Member {
	private int memberNo;
	private String memberID;
	private String memberPW;
=======
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPw;
>>>>>>> origin/BSH
	private String memberName;
	private String memberNickname;
	private String memberEmail;
	private String memberTel;
<<<<<<< HEAD
	private String memberProfile;
	private int point;
	private String grade;
	private String registDate;
	private char secessionFlag;
=======
	private String profileImage;
	private int gradeCode;
	private int point;
	private String regDate;
	private String secessionFlag;
	private String secessionDate;
	
>>>>>>> origin/BSH
	
}
