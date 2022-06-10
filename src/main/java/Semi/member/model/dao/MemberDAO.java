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

	/**
	 * 이메일 중복 검사 DAO
	 * @param conn
	 * @param memberEmail
	 * @return result
	 * @throws Exception
	 */
	public int emailDupCheck(Connection conn, String memberEmail) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("emailDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	/**
	 * 닉네임 중복 검사 DAO
	 * @param conn
	 * @param memberNickname
	 * @return result
	 * @throws Exception
	 */
	public int nicknameDupCheck(Connection conn, String memberNickname) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("nicknameDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNickname);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	/**
	 * 아이디 중복 검사 DAO
	 * @param conn
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String memberId) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("idDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	/**
	 * 인증번호, 발급일 수정 DAO
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return result
	 * @throws Exception
	 */
	public int updateCertification(Connection conn, String inputEmail, String cNumber) throws Exception{
		
		int result = 0;
	      
	      try {
	         String sql = prop.getProperty("updateCertification");
	         
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, cNumber);
	         pstmt.setString(2, inputEmail);
	         
	         result = pstmt.executeUpdate();
	         
	      }finally {
	         close(pstmt);
	      }
		
		return result;
	}

	/**
	 * 인증번호 생성 DAO
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return result
	 * @throws Exception
	 */
	public int insertCertification(Connection conn, String inputEmail, String cNumber) throws Exception{
		int result = 0;
	      
	      try {
	         String sql = prop.getProperty("insertCertification");
	         
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, inputEmail);
	         pstmt.setString(2, cNumber);
	         
	         result = pstmt.executeUpdate();
	         
	      }finally {
	         close(pstmt);
	      }
	      
	      return result;
	}

	/**
	 * 인증번호 확인 DAO
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return result
	 * @throws Exception
	 */
	public int checkNumber(Connection conn, String inputEmail, String cNumber) throws Exception{
		int result = 0;
	      
	      try {
	         
	         String sql = prop.getProperty("checkNumber");
	         
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, inputEmail);
	         pstmt.setString(2, cNumber);
	         pstmt.setString(3, inputEmail);
	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	            result = rs.getInt(1);
	         }
	         
	      }finally {
	         close(pstmt);
	      }
	      
	      return result;
	}

}
