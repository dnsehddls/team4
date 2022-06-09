package Semi.member.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;

import Semi.member.model.dao.MemberDAO;
import Semi.member.model.vo.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	/**
	 * 회원가입 Service
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member mem) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, mem);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}

	/**
	 * 이메일 중복 검사 Service
	 * @param memberEmail
	 * @return result
	 * @throws Exception
	 */
	public int emailDupCheck(String memberEmail) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.emailDupCheck(conn, memberEmail);
		
		close(conn);
		
		return result;
	}

	/**
	 * 닉네임 중복 검사 Service
	 * @param memberNickname
	 * @return result
	 * @throws Exception
	 */
	public int nicknameDupCheck(String memberNickname) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.nicknameDupCheck(conn, memberNickname);
		
		close(conn);
		
		return result;
	}

	/**
	 * 아이디 중복 검사 Service
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String memberId) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.idDupCheck(conn, memberId);
		
		close(conn);
		
		return result;
	}

}
