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

}
