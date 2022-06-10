package Semi.member.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Semi.board.model.vo.Pagination;
import Semi.member.model.dao.MemberDAO;
import Semi.member.model.vo.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	public Member login(Member user) throws Exception{
		Connection conn = getConnection();
		Member result = dao.login(conn,user);
		close(conn);
		return result;
	}
	

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


	/** 회원 정보 조회 Service
	 * @param memberEmail
	 * @return member
	 * @throws Exception
	 */
	public Member selectOne(String memberEmail) throws Exception {

		Connection conn = getConnection();
		
		Member member = dao.selectOne(conn, memberEmail);
		
		close(conn);
		
		return member;
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
	
	
}
