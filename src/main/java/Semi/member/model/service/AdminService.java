package Semi.member.model.service;


import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Semi.board.model.vo.Pagination;
import Semi.member.model.dao.AdminDAO;
import Semi.member.model.vo.Member;



public class AdminService {
	
	private AdminDAO dao = new AdminDAO();

//	public Member login(Member user) throws Exception{
//		Connection conn = getConnection();
//		Member result = dao.login(conn,user);
//		close(conn);
//		return result;
//	}
	

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
		map.put("list", memberList);
		
		
		
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


	/** 회원 탈퇴 / 복구
	 * @param memberEmail
	 * @return	result
	 * @throws Exception
	 */
	public int changeSecession(String memberEmail) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.changeSecession(conn, memberEmail);
		
		close(conn);
		
		return result;
	}


	/** 회원 검색 Service
	 * @param memberEmail
	 * @return searchMember
	 * @throws Exception
	 */
	public Map<String, Object> searchMember(String memberEmail) throws Exception {

		Connection conn = getConnection();
		
		Member searchMember = dao.searchMember(conn, memberEmail);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("searchMember", searchMember);
		
		close(conn);
		
		return map;
		
	}




}
