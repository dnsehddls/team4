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

import Semi.member.model.vo.Pagination;
import Semi.member.model.vo.Member;




public class AdminDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;	
	private Properties prop = null;
	
	public AdminDAO() {
		
		try {
			prop = new Properties();
			
			String filePath = AdminDAO.class.getResource("/Semi/sql/Admin_sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
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
				
				mem.setMemberNo(rs.getInt("MEMBER_NO"));
				mem.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				mem.setMemberNickname(rs.getString("MEMBER_NICK"));
				mem.setRegistDate(rs.getString("REG_DATE"));
				mem.setSecessionFlag(rs.getString("SECESSION_FL").charAt(0));
				
				memberList.add(mem);
				
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return memberList;
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


	/** 회원 탈퇴 / 복구
	 * @param conn
	 * @param memberEmail
	 * @param secessionFlag
	 * @return result
	 * @throws Exception
	 */
	public int changeSecession(Connection conn, String memberEmail) throws Exception {
		int result = 0;
		
		
		try {
			String sql = prop.getProperty("changeSecession");
			
			pstmt = conn.prepareStatement(sql);		
		
			pstmt.setString(1, memberEmail);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);			
		}
		
		return result;
	}


	/** 회원 상세 조회		
	 * @param conn
	 * @param memberEmail
	 * @return memberDetail
	 * @throws Exception
	 */
	public Member memberDetail(Connection conn, String memberEmail) throws Exception {
		
		Member memberDetail = null;
		
		try {
			String sql = prop.getProperty("memberDetail");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberEmail);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				memberDetail = new Member();
				
				memberDetail.setMemberID(rs.getString("MEMBER_ID"));
				memberDetail.setMemberName(rs.getString("MEMBER_NAME"));
				memberDetail.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				memberDetail.setSecessionFlag(rs.getString("SECESSION_FL").charAt(0));
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return memberDetail;
	}


	/** 회원 검색 DAO
	 * @param conn
	 * @param memberEmail
	 * @return	searchMember
	 * @throws Exception
	 */
//	public Member searchMember(Connection conn, String memberEmail) throws Exception {
//		
//		Member searchMember = null;
//		
//		try {
//			String sql = prop.getProperty("searchMember");
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, memberEmail);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				
//				searchMember = new Member();
//				
//				searchMember.setMemberNo(rs.getInt("MEMBER_NO"));
//				searchMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
//				searchMember.setMemberName(rs.getString("MEMBER_NAME"));
//				searchMember.setSecessionFlag(rs.getString("SECESSION_FL").charAt(0));
//				
//			}
//			
//		}finally {
//			close(rs);
//			close(pstmt);
//		}
//		
//		return searchMember;
//		
//	}


	/** 조건을 만족하는 회원의 수 조회 
	 * @param conn
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int searchListCount(Connection conn, String condition) throws Exception {
		
		int listCount = 0;
		
		try {
			String sql = prop.getProperty("searchListCount") + condition;
			
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


	/** 조건을 만족하는 회원 목록 조회 
	 * @param conn
	 * @param pagination
	 * @param condition
	 * @return memberList
	 * @throws Exception
	 */
	public List<Member> searchMemberList(Connection conn, Pagination pagination, String condition) throws Exception {
		
		List<Member> memberList = new ArrayList<Member>();
		
		try {
			String sql = prop.getProperty("searchMemberList1") + condition + prop.getProperty("searchMemberList2");
			
			int start = (pagination.getCurrentPage() -1) * pagination.getLimit() +1;
			int end = start + pagination.getLimit() -1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Member member = new Member();
				
				member.setMemberNo(rs.getInt("MEMBER_NO"));
				member.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				member.setMemberNickname(rs.getString("MEMBER_NICK"));
				member.setRegistDate(rs.getString("REG_DATE"));
				member.setSecessionFlag(rs.getString("SECESSION_FL").charAt(0));
				
				memberList.add(member);
				
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return memberList;
	}

}
