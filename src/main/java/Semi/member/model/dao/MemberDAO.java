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



}
