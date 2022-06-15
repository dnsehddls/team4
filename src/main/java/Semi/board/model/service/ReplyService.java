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


}
