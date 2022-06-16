package Semi.board.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Semi.board.model.dao.BoardDAO;
import Semi.board.model.vo.Board;
import Semi.board.model.vo.Pagination;
import Semi.board.model.vo.MyBoard;
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
	public Map<String, Object> selectBoardList(int type, int cp) throws Exception{
		
		Connection conn = getConnection();
		
		

		String boardName = dao.selectBoardName(conn, type);
		
		int listCount = dao.getListCount(conn, type);		
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Board> boardList = dao.selectBoardList(conn, pagination, type);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		close(conn);
		
		return map; 
	}
	
	public Map<String, Object> searchBoardList(int type, int cp, String key, String query) throws Exception {
			
			Connection conn = getConnection();
			
			// ���� ��� ��ȸ Service, DAO, SQL�� �����ϸ鼭 ����
			// 1. �Խ��� �̸� ��ȸ DAO ȣ��
			String boardName = dao.selectBoardName(conn, type);
			
			// 2. SQL �������� �߰��� ���� ����(key, query ���)
			String condition = null;// ����
			
			switch(key) {
						case "t"  : condition = " AND BOARD_TITLE LIKE '%"+query+"%' ";  break;
						case "c"  : condition = " AND BOARD_CONTENT LIKE '%"+query+"%' ";  break;
						case "tc" : condition = " AND (BOARD_TITLE LIKE '%"+query+"%' OR BOARD_CONTENT LIKE '%"+query+"%') ";  break;
						case "w"  : condition = " AND MEMBER_NICK LIKE '%"+query+"%' "; break;
						}
			
			// 3-1. Ư�� �Խ��ǿ��� ������ �����ϴ� �Խñ� �� ��ȸ
			int listCount = dao.searchListCount(conn, type, condition);
					
			// 3-2. listCount  + ���� ������(cp)�� �̿��� ���������̼� ��ü ����
			Pagination pagination = new Pagination(cp, listCount);		
			
			
			// 4. Ư�� �Խ��ǿ��� ������ �����ϴ� �Խñ� ��� ��ȸ
			List<Board> boardList = dao.searchBoardList(conn, pagination, type, condition);
			
			// 5. ��� ���� �ϳ��� Map�� ��Ƽ� ��ȯ
			Map<String, Object> map = new HashMap<>();
			
			map.put("boardName", boardName);
			map.put("pagination", pagination);
			map.put("boardList", boardList);
			
			close(conn);
			
			return map;
	}

	/**
	 * 내 글 목록 조회 Service
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<MyBoard> myContent(int memberNo) throws Exception{
		
		
		return null;
	}
	
}
