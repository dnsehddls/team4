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
import Semi.board.model.vo.MyBoard;
import Semi.board.model.vo.Pagination;
import Semi.board.model.vo.ShowWindowInfo;
import Semi.common.Util;

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

			// 占쏙옙占쏙옙 占쏙옙占� 占쏙옙회 Service, DAO, SQL占쏙옙 占쏙옙占쏙옙占싹면서 占쏙옙占쏙옙
			// 1. 占쌉쏙옙占쏙옙 占싱몌옙 占쏙옙회 DAO 호占쏙옙
			String boardName = dao.selectBoardName(conn, type);

			// 2. SQL 占쏙옙占쏙옙占쏙옙占쏙옙 占쌩곤옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙(key, query 占쏙옙占�)
			String condition = null;// 占쏙옙占쏙옙

			switch(key) {
						case "t"  : condition = " AND BOARD_TITLE LIKE '%"+query+"%' ";  break;
						case "c"  : condition = " AND BOARD_CONTENT LIKE '%"+query+"%' ";  break;
						case "tc" : condition = " AND (BOARD_TITLE LIKE '%"+query+"%' OR BOARD_CONTENT LIKE '%"+query+"%') ";  break;
						case "w"  : condition = " AND MEMBER_NICK LIKE '%"+query+"%' "; break;
						}

			// 3-1. 특占쏙옙 占쌉쏙옙占실울옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 占쌉시깍옙 占쏙옙 占쏙옙회
			int listCount = dao.searchListCount(conn, type, condition);

			// 3-2. listCount  + 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙(cp)占쏙옙 占싱울옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占싱쇽옙 占쏙옙체 占쏙옙占쏙옙
			Pagination pagination = new Pagination(cp, listCount);


			// 4. 특占쏙옙 占쌉쏙옙占실울옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 占쌉시깍옙 占쏙옙占� 占쏙옙회
			List<Board> boardList = dao.searchBoardList(conn, pagination, type, condition);

			// 5. 占쏙옙占� 占쏙옙占쏙옙 占싹놂옙占쏙옙 Map占쏙옙 占쏙옙티占� 占쏙옙환
			Map<String, Object> map = new HashMap<>();

			map.put("boardName", boardName);
			map.put("pagination", pagination);
			map.put("boardList", boardList);

			close(conn);

			return map;
	}

	/**
	 * �궡 湲� 紐⑸줉 議고쉶 Service
	 * @param memberNo
	 * @return clist
	 * @throws Exception
	 */
	/*
	 * public List<MyBoard> myContent(int memberNo) throws Exception{
	 * 
	 * Connection conn = getConnection();
	 * 
	 * // Pagination pagination = new Pagination(currentPage);
	 * 
	 * List<MyBoard> clist = dao.myContent(conn, memberNo);
	 * 
	 * close(conn);
	 * 
	 * return clist; }
	 */

	public BoardDetail selectBoardDetail(int boardNo) throws Exception{
		
		Connection conn = getConnection();
		

		BoardDetail detail = dao.selectBoardDetail(conn, boardNo);
		
		
		if(detail != null) { 
	
			List<BoardImage> imageList = dao.selectImageList(conn, boardNo);
			
			
			detail.setImageList(imageList);
			
		}

		close(conn);
		
		return detail;
	}
	
	
	public int insertBoard(BoardDetail detail, List<BoardImage> imageList, int boardCode) throws Exception{
		
		Connection conn = getConnection();
		
		int boardNo = dao.nextBoardNo(conn);
		
		detail.setBoardNo(boardNo);
		
		detail.setBoardTitle(  Util.XSSHandling( detail.getBoardTitle()   )  );
		detail.setBoardContent(  Util.XSSHandling( detail.getBoardContent()   )  );
		
		detail.setBoardContent(  Util.newLineHandling( detail.getBoardContent()   )  );
		
		int result = dao.insertBoard(conn, detail, boardCode);
		
		
		if(result > 0) {
			
			for(BoardImage image : imageList) {
				image.setBoardNo(boardNo); 
				
				result = dao.insertBoardImage(conn, image);
				
				if(result == 0) { 
					break;
				}
			}
			
		}
		
		if(result > 0) {
			commit(conn);
	
		}else { 
			rollback(conn);
			boardNo = 0; 
		}
		
		close(conn);
		
		return boardNo;
	}
	
	
	public int updateBoard(BoardDetail detail, List<BoardImage> imageList, String deleteList) throws Exception {
		
		Connection conn = getConnection();

		detail.setBoardTitle( Util.XSSHandling( detail.getBoardTitle() ) );
		detail.setBoardContent( Util.XSSHandling( detail.getBoardContent() ) );
		
		detail.setBoardContent( Util.newLineHandling( detail.getBoardContent() ) );

		int result = dao.updateBoard(conn, detail);
		
		if(result > 0) {
			
			for( BoardImage img : imageList ) {
				
				img.setBoardNo(detail.getBoardNo());
				
				result = dao.updateBoardImage(conn, img);

				if(result == 0) {
					result = dao.insertBoardImage(conn, img);
				}
				
			} 
			
			
			if( !deleteList.equals("") ) { 
				result = dao.deleteBoardImage(conn, deleteList, detail.getBoardNo());
			}
			
		
		} 
		
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		
		close(conn);
		
		return result;
	}



}
