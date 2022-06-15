package Semi.board.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import Semi.board.model.dao.ReplyDAO;
import Semi.board.model.vo.Reply;

public class ReplyService {

	private ReplyDAO dao = new ReplyDAO();
	
	public List<Reply> replyList(int boardNo) throws Exception {
		Connection conn = getConnection();
		List<Reply> rList = dao.replyList(conn,boardNo);
		close(conn);
		return rList;
	}

	public int replyInsert(Reply reply) throws Exception {
		Connection conn = getConnection();
		int result = dao.replyInsert(conn, reply);
		if(result>0)		commit(conn);
		else				rollback(conn);
		return result;
	}

	public int replyDelete(int replyNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.replyDelete(conn,replyNo);
		if(result>0)		commit(conn);
		else				rollback(conn);
		return result;
	}

	public int replyUpdate(int replyNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.replyUpdate(conn,replyNo);
		if(result>0)		commit(conn);
		else				rollback(conn);
		return result ;
	}


}
