package Semi.member.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;

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
	
	
}
