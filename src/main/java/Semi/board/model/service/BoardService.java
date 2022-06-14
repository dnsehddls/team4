package Semi.board.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Semi.board.model.dao.BoardDAO;
import Semi.board.model.vo.Board;
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
			
			// 기존 목록 조회 Service, DAO, SQL을 참고하면서 진행
			// 1. 게시판 이름 조회 DAO 호출
			String boardName = dao.selectBoardName(conn, type);
			
			// 2. SQL 조건절에 추가될 구문 가공(key, query 사용)
			String condition = null;// 조건
			
			switch(key) {
						case "t"  : condition = " AND BOARD_TITLE LIKE '%"+query+"%' ";  break;
						case "c"  : condition = " AND BOARD_CONTENT LIKE '%"+query+"%' ";  break;
						case "tc" : condition = " AND (BOARD_TITLE LIKE '%"+query+"%' OR BOARD_CONTENT LIKE '%"+query+"%') ";  break;
						case "w"  : condition = " AND MEMBER_NICK LIKE '%"+query+"%' "; break;
						}
			
			// 3-1. 특정 게시판에서 조건을 만족하는 게시글 수 조회
			int listCount = dao.searchListCount(conn, type, condition);
					
			// 3-2. listCount  + 현재 페이지(cp)를 이용해 페이지네이션 객체 생성
			Pagination pagination = new Pagination(cp, listCount);		
			
			
			// 4. 특정 게시판에서 조건을 만족하는 게시글 목록 조회
			List<Board> boardList = dao.searchBoardList(conn, pagination, type, condition);
			
			// 5. 결과 값을 하나의 Map에 모아서 반환
			Map<String, Object> map = new HashMap<>();
			
			map.put("boardName", boardName);
			map.put("pagination", pagination);
			map.put("boardList", boardList);
			
			close(conn);
			
			return map;
	}
}
