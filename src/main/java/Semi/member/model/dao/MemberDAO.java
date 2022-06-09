package Semi.member.model.dao;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import Semi.member.model.vo.Member;

public class MemberDAO {
	
	private Properties prop;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/**
	 * 회원가입 DAO
	 * @param conn
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member mem) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("signUp");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem.getMemberId());
			pstmt.setString(2, mem.getMemberPw());
			pstmt.setString(3, mem.getMemberName());
			pstmt.setString(4, mem.getMemberNickname());
			pstmt.setString(5, mem.getMemberEmail());
			pstmt.setString(6, mem.getMemberTel());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

}
