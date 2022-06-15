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
import Semi.member.model.vo.Member;

public class ReplyService {

	private ReplyDAO dao = new ReplyDAO();
	
	public List<Reply> replyList(int boardNo) throws Exception {
		Connection conn = getConnection();
		List<Reply> rList = dao.replyList(conn,boardNo);
		close(conn);
		return rList;
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
