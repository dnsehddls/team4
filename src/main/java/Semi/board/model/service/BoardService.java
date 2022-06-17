package Semi.board.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Semi.board.model.dao.BoardDAO;
import Semi.board.model.vo.Board;
import Semi.board.model.vo.BoardDetail;
import Semi.board.model.vo.BoardImage;
import Semi.common.Util;

import Semi.board.model.vo.MyBoard;
import Semi.board.model.vo.Pagination;
import Semi.board.model.vo.ShowWindowInfo;
import Semi.common.Util;
import Semi.member.model.vo.Member;

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
	 * 내 글 목록 조회
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> myc(int cp, Member loginMember) throws Exception{

		Connection conn = getConnection();

		// 내 글 게시글 수 조회
		int listCount = dao.mycCount(conn, loginMember);

		// 페이지네이션
		Pagination pagination = new Pagination(cp, listCount);

		// 게시글 목록 조회
		List<MyBoard> contentList = dao.myContentList(conn, pagination, loginMember);

		// Map에 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("pagination", pagination);
		map.put("contentList", contentList);

		close(conn);

		return map;
	}

	/**
	 * 북마크 목록 조회
	 * @param cp
	 * @param loginMember
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> bookmarkList(int cp, Member loginMember) throws Exception{
		Connection conn = getConnection();

		int listCount = dao.bookmarkCount(conn, loginMember);

		Pagination pagination = new Pagination(cp, listCount);

		List<MyBoard> bookmarkList = dao.bookmarkList(conn, pagination, loginMember);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("pagination", pagination);
		map.put("bookmarkList", bookmarkList);

		close(conn);

		return map;
	}


	
	public Map<String, Object> likeList(int cp, Member loginMember) throws Exception{
		Connection conn = getConnection();

		int listCount = dao.likeCount(conn, loginMember);

		Pagination pagination = new Pagination(cp, listCount);

		
		List<MyBoard> likeList = dao.likeList(conn, pagination, loginMember);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("pagination", pagination);
		map.put("likeList", likeList);

		close(conn);
		
		
		return map;
	}
	
	
	public int insertBoard(BoardDetail detail, int boardCode) throws Exception{
		
		Connection conn = getConnection();
		
		int boardNo = dao.nextBoardNo(conn);
		
		detail.setBoardNo(boardNo);
		
		detail.setBoardTitle(  Util.XSSHandling( detail.getBoardTitle()   )  );
		detail.setBoardContent(  Util.XSSHandling( detail.getBoardContent()   )  );
		
		detail.setBoardContent(  Util.newLineHandling( detail.getBoardContent()   )  );
		
		int result = dao.insertBoard(conn, detail, boardCode);
		
		
		if(result > 0) {
			commit(conn);
	
		}else { 
			rollback(conn);
			boardNo = 0; 
		}
		
		close(conn);
		
		return boardNo;
	}
	
	
	public BoardDetail selectBoardDetail(int boardNo) throws Exception{
		
		Connection conn = getConnection();
		
		// 1) 게시글(BOARD 테이블) 관련 내용만 조회
		BoardDetail detail = dao.selectBoardDetail(conn, boardNo);
		


		close(conn);
		
		return detail;
	}
	
	
	
	public int updateBoard(BoardDetail detail,  String deleteList) throws Exception {
		
		Connection conn = getConnection();
		
		detail.setBoardTitle( Util.XSSHandling( detail.getBoardTitle() ) );
		detail.setBoardContent( Util.XSSHandling( detail.getBoardContent() ) );
		
		detail.setBoardContent( Util.newLineHandling( detail.getBoardContent() ) );
		
		int result = dao.updateBoard(conn, detail);
		
		if(result > 0) { 
			
		
		}
		
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		
		close(conn);
		
		return result;
	}


	public int deleteBoard(int boardNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.deleteBoard(conn, boardNo);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		return result;
	}


}
