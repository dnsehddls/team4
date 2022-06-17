package Semi.member.model.service;


import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Semi.member.model.vo.Pagination;
import Semi.board.model.vo.Report;
import Semi.member.model.dao.AdminDAO;
import Semi.member.model.vo.Member;



public class AdminService {
	
	private AdminDAO dao = new AdminDAO();

	

	/** 회원 목록 조회 Service
	 * @return list
	 * @throws Exception
	 */
	public Map<String, Object> selectAll(int cp) throws Exception {
		
		Connection conn = getConnection();
		
		// 전체 회원 수 조회
		int listCount = dao.searchListCount(conn);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		// 전체 회원 조회
		List<Member> memberList = dao.selectAll(conn, pagination);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pagination", pagination);
		map.put("memberList", memberList);	
		
		
		close(conn);

		return map;
	}


	/** 관리자 정보 수정 Service
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int updateAdmin(Member mem) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateAdmin(conn, mem);
		
		if(result>0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 관리자 비밀번호 수정 Service
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int adminChangePw(String currentPw, String newPw, int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.adminChangePw(conn, currentPw, newPw, memberNo);
		
		if(result>0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 회원 상세 조회
	 * @param memberEmail
	 * @param secessionFlag
	 * @return result
	 * @throws Exception
	 */
	public Member memberDetail(String memberEmail) throws Exception {

		Connection conn = getConnection();
		
		
		// 회원 상세 조회
		Member memberDetail = dao.memberDetail(conn, memberEmail);
		
		
		close(conn);
		
		return memberDetail;
	}








	/** 회원 검색 조회
	 * @param key
	 * @param query
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> searchMember(String key, String query, int cp) throws Exception {
		
		Connection conn = getConnection();
		
		String condition = null;
		
		switch(key) {		
		case "e" : condition = "AND MEMBER_EMAIL LIKE '%"+query+"%' "; break;
		case "n" : condition = "AND MEMBER_NICK LIKE '%"+query+"%' "; break;
		case "id" : condition = "AND MEMBER_ID LIKE '%"+query+"%' "; break;	
		}
		
		int listCount = dao.searchListCount(conn, condition);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Member> memberList = dao.searchMemberList(conn, pagination, condition);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("memberList", memberList);
		
		close(conn);
		
		return map;
	}


	/** 회원 탈퇴 Service
	 * @param memberEmail
	 * @return result
	 * @throws Exception
	 */
	public int memberFlagY(String memberEmail) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.memberFlagY(conn, memberEmail);
		
		if(result>0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 탈퇴 회원 복구
	 * @param memberEmail
	 * @return result
	 * @throws Exception
	 */
	public int memberFlagN(String memberEmail) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.memberFlagN(conn, memberEmail);
		
		if(result>0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 신고 게시글 조회 Service
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> reportedList(int cp) throws Exception {
		
		Connection conn = getConnection();
		
		// 신고된 게시글 수 조회
		int listCount = dao.reportedListCount(conn);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		// 게시글, 댓글 목록 조회
		List<Report> reportList = dao.reportList(conn, pagination);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pagination", pagination);
		map.put("reportList", reportList);
		
		close(conn);
		
		return map;
	}


	/** 신고 게시글 검색 조회
	 * @param key
	 * @param query
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> searchReported(String key, String query, int cp) throws Exception {

		Connection conn = getConnection();
		
		String condition = null;
		
		switch(key) {		
		case "rNo" : condition = "AND REPORT_NO LIKE '%"+query+"%' "; break;
		case "mNo" : condition = "AND MEMBER_NO LIKE '%"+query+"%' "; break;
		case "bNo" : condition = "AND BOARD_NO LIKE '%"+query+"%' "; break;	
		}
		
		int listCount = dao.searchReportedListCount(conn, condition);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Report> reportedList = dao.searchReportedList(conn, pagination, condition);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("reportedList", reportedList);
		
		close(conn);
		
		return map;
		
	}


	/** 좋아요 수 조회
	 * @return standardNo
	 * @throws Exception
	 */
	public int manageStandard() throws Exception {

		Connection conn = getConnection();
		
		int standardNo = dao.manageStandard(conn);
		
		close(conn);
		
		return standardNo;
	}


	/** 좋아요 수 변경하기
	 * @param changeNo
	 * @return result
	 * @throws Exception
	 */
	public int likeChange(int changeNo) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.likeChange(conn, changeNo);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		

		close(conn);
		
		
		
		return result;
	}







}