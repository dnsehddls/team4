package Semi.board.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import Semi.board.model.dao.BoardDAO;
import Semi.board.model.vo.Board;
import Semi.board.model.vo.MyBoard;
import Semi.board.model.vo.Pagination;
import Semi.board.model.vo.ShowWindowInfo;

public class BoardService {

	private BoardDAO dao = new BoardDAO();

	public List<ShowWindowInfo> mainBoardSelect(String boardType) throws Exception{
		Connection conn = getConnection();
		List<ShowWindowInfo> showList= dao.mainBoardSelect(conn,boardType);
		close(conn);
		return showList ;
	}

	public Board boardDetail(int boardNo) throws Exception{
		Connection conn = getConnection();
		Board result = dao.boardDetail(conn,boardNo);
		close(conn);
		return result;
	}

	/**
	 * 내 글 목록 조회 Service
	 * @param memberNo
	 * @return clist
	 * @throws Exception
	 */
	public List<MyBoard> myContent(int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		Pagination pagination = new Pagination(currentPage);
		
		List<MyBoard> clist = dao.myContent(conn, memberNo);
		
		close(conn);
		
		return clist;
	}
	
}