package Semi.board.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import Semi.board.model.dao.BoardDAO;
import Semi.board.model.vo.Board;
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
	
}
