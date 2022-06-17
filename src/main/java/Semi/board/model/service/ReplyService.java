package Semi.board.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Semi.board.model.dao.ReplyDAO;
import Semi.board.model.vo.MyBoard;
import Semi.board.model.vo.Pagination;
import Semi.board.model.vo.Reply;
import Semi.common.Util;
import Semi.member.model.vo.Member;

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
	
	/**
	 * 내 댓글 목록 조회
	 * @param cp
	 * @param loginMember
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> myr(int cp, Member loginMember) throws Exception{
		
		Connection conn = getConnection();
		
		int listCount = dao.myrCount(conn, loginMember);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<MyBoard> replyList = dao.myReplyList(conn, pagination, loginMember);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pagination", pagination);
		map.put("replyList", replyList);
		
		close(conn);
		
		return map;
	}



}
