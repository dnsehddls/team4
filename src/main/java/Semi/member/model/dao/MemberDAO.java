package Semi.member.model.dao;

import static Semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import Semi.member.model.vo.Member;

public class MemberDAO {

	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public MemberDAO() {
		try {
			prop = new Properties();
			String filePath = MemberDAO.class.getResource
					("/edu/kh/SemiProject/sql/User_sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public Member login(Connection conn, Member user) throws Exception {
		Member result = null;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("login"));
			pstmt.setString(1, user.getMemberID());
			pstmt.setString(2, user.getMemberPW());
			rs = pstmt.executeQuery();
			
			//vo에 데이터 저장할 애들
			if(rs.next()) {
				result = new Member();
				result.setMemberNo(rs.getInt(1));
				result.setMemberID(rs.getString(2));
				result.setMemberEmail(rs.getString(3));
				result.setMemberName(rs.getString(4));
				result.setMemberTel(rs.getString(5));
				result.setMemberProfile(rs.getString(6));
				result.setGrade(rs.getString(7));
				result.setPoint(rs.getInt(8));
				result.setRegistDate(rs.getString(9));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

}
