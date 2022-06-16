package Semi.board.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import Semi.board.model.dao.ReplyDAO;
import Semi.board.model.vo.Reply;
import Semi.common.Util;

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
		reply.setReplyContent(Util.XSSHandling(reply.getReplyContent()));
		reply.setReplyContent(Util.newLineHandling(reply.getReplyContent()));
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

	public int replyUpdate(int replyNo, String replyContent) throws Exception {
		Connection conn = getConnection();
		String replyC = Util.XSSHandling(replyContent);
		replyC = Util.newLineHandling(replyContent);
		int result = dao.replyUpdate(conn,replyNo,replyC);
		if(result>0)		commit(conn);
		else				rollback(conn);
		return result ;
	}


}
