package Semi.member.model.dao;

import static Semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Semi.board.model.vo.Pagination;
import Semi.member.model.vo.Member;

public class MemberDAO {

	private Properties prop;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public MemberDAO() {
		try {
			prop = new Properties();

			String filePath = MemberDAO.class.getResource
					("/Semi/sql/member-sql.xml").getPath();

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

			pstmt.setString(1, mem.getMemberID());
			pstmt.setString(2, mem.getMemberPW());
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

	/** 회원 목록 조회 DAO
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<Member> selectAll(Connection conn, Pagination pagination) throws Exception {

		List<Member> memberList = new ArrayList<Member>();

		try {
			String sql = prop.getProperty("selectAll");

			// BETWEEN 구문에 들어갈 범위 계산
			int start = (pagination.getCurrentPage() -1) * pagination.getLimit() +1;
			int end = start + pagination.getLimit() -1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Member mem = new Member();

				mem.setMemberNo(rs.getInt(1));
				mem.setMemberEmail(rs.getString(2));
				mem.setMemberName(rs.getString(3));
				mem.setRegistDate(rs.getString(4));

				memberList.add(mem);

			}

		}finally {
			close(rs);
			close(stmt);
		}

		return memberList;
	}


	/** 전체 회원 수 조회 DAO
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int searchListCount(Connection conn) throws Exception {

		int listCount = 0;

		try {
			String sql = prop.getProperty("getListCount");

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if(rs.next()) {
				listCount = rs.getInt(1);
			}

		}finally {
			close(rs);
			close(stmt);
		}

		return listCount;
	}


	/** 회원 정보 조회 DAO
	 * @param conn
	 * @param memberEmail
	 * @return member
	 * @throws Exception
	 */
	public Member selectOne(Connection conn, String memberEmail) throws Exception {

		Member member = null;

		try {
			String sql = prop.getProperty("selectOne");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberEmail);

			rs = pstmt.executeQuery();

			if(rs.next()) {

				member = new Member();

				member.setMemberNo(rs.getInt(1));
				member.setMemberEmail(rs.getString(2));
				member.setMemberName(rs.getString(3));
				member.setRegistDate(rs.getString(4));

			}

		}finally {
			close(rs);
			close(pstmt);
		}

		return member;
	}


	/** 관리자 정보 수정 DAO
	 * @param conn
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int updateAdmin(Connection conn, Member mem) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("updateAdmin");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberNickname());
			pstmt.setString(2, mem.getMemberTel());
			pstmt.setInt(3, mem.getMemberNo());

			result = pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}

		return result;
	}


	/** 관리자 비밀번호 수정
	 * @param conn
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int adminChangePw(Connection conn, String currentPw, String newPw, int memberNo) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("adminChangePw");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, newPw);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, currentPw);

			result = pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}

		return result;
	}


	/**
	 * 회원 정보 수정 DAO
	 * @param conn
	 * @param mem
	 * @return
	 * @throws Exception
	 */
	public int updateMember(Connection conn, Member mem) throws Exception{

		int result = 0;

		try {
			String sql = prop.getProperty("updateMember");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberPW());
			pstmt.setString(2, mem.getMemberNickname());
			pstmt.setString(3, mem.getMemberTel());
			pstmt.setString(4, mem.getMemberEmail());
			pstmt.setInt(5, mem.getMemberNo());

			result = pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 회원 탈퇴 DAO
	 * @param conn
	 * @param memberNo
	 * @param inputPW
	 * @return result
	 * @throws Exception
	 */
	public int secession(Connection conn, int memberNo, String inputPW) throws Exception{
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("secession");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, inputPW);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


}
