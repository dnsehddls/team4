package Semi.board.model.dao;

import static Semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import Semi.board.model.vo.Board;
import Semi.board.model.vo.BoardDetail;
import Semi.board.model.vo.BoardImage;
import Semi.board.model.vo.MyBoard;
import Semi.board.model.vo.Pagination;
import Semi.board.model.vo.ShowWindowInfo;
import Semi.member.model.vo.Member;

public class BoardDAO {


	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	private Properties prop;
	public BoardDAO() {
		try {
			prop = new Properties();
			String filePath = BoardDAO.class.getResource("/Semi/sql/board-sql.xml").getPath();

			prop.loadFromXML(new FileInputStream(filePath));


		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<ShowWindowInfo> mainBoardSelect(Connection conn,String boardType) throws Exception{
		List<ShowWindowInfo> sList = new ArrayList<ShowWindowInfo>();
		try {
			String sql = prop.getProperty(boardType+"Board");
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, 0);
//			rs = pstmt.executeQuery();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ShowWindowInfo show = new ShowWindowInfo();
				show.setBoardNo(rs.getInt("BOARD_NO"));
				show.setBoardTitle(rs.getString("BOARD_TITLE"));
				show.setLikeCount(rs.getInt("L_C"));
				show.setReplyCount(rs.getInt("R_C"));
				show.setMemberNick(rs.getString("MEMBER_NICK"));
				show.setDate(rs.getString("CREATE_DT"));
				//announcement
				//exercise
				//free
				//met
				show.setBoardType(rs.getInt("BOARD_CD"));

				sList.add(show);
			}

		} finally {
			close(rs);
			close(stmt);
		}
		return sList;
	}

	public Board boardDetail(Connection conn, int boardNo) throws Exception{
		Board boardDetail = null;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("boardDetail"));
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardDetail = new Board();
				boardDetail.setBoardNo(rs.getInt(1));
				boardDetail.setBoardTitle(rs.getString(2));
				boardDetail.setBoardContent(rs.getString(3));
				boardDetail.setReadCount(rs.getInt(4));
				boardDetail.setMemberNickname(rs.getString(5));
				boardDetail.setCreateDate(rs.getString(6));
				if(rs.getString(7)!=null) {
					boardDetail.setUpdateDate(rs.getString(7));
				}
				boardDetail.setBoardType(rs.getString(8));
				boardDetail.setMemberNo(rs.getInt(9));
			}

		} finally {
			close(rs);
			close(pstmt);
		}
		return boardDetail;
	}
	public String selectBoardName(Connection conn, int type) throws Exception {
		String boardName = null;

		try {
			String sql = prop.getProperty("selectBoardName");
			String queryAdd = "WHERE BOARD_CD = ?";
			if(type<=4) {
				//타입이 5 (인기 게시판) , 6 (최근 게시글)으로 쿼리로 나뉘여야함
				pstmt = conn.prepareStatement(sql+queryAdd);
				
				pstmt.setInt(1, type);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					boardName = rs.getString(1);
				}
			}
			if(type==5) {
				boardName = "인기 게시판";
			}
			if(type==6) {
				boardName = "최근 게시판";
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return boardName;
	}


	public int getListCount(Connection conn, int type) throws Exception{
		int listCount = 0;

		try {

			String sql = prop.getProperty("getListCount");
			String queryAdd = "AND BOARD_CD = ?";

			if(type==5) {
				queryAdd = " AND (SELECT COUNT(*) "
						+ "      FROM LIKE_BOARD C "
						+ "      WHERE C.BOARD_NO = A.BOARD_NO) >= 5";
			}

			if(type==6) {
				queryAdd = "";
			}
			
			pstmt = conn.prepareStatement(sql+queryAdd);
			
			if(queryAdd.equals("AND BOARD_CD = ?")){
					pstmt.setInt(1, type);
			}


			rs = pstmt.executeQuery();

			if(rs.next()) {
				listCount = rs.getInt(1);
			}

		}finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public List<Board> selectBoardList(Connection conn, Pagination pagination, int type) throws Exception {

		List<Board> boardList = new ArrayList<Board>();

		try {
			int start =  ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			if(type <= 4) {
				String sql = prop.getProperty("selectBoardList");
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, type);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				
				rs = pstmt.executeQuery();
			}
			
			if(type == 5) {
				String sql = prop.getProperty("hotBoardSelectList");
				pstmt = conn.prepareStatement(sql);
				
				//관리자 머시기 머시기
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				
				rs = pstmt.executeQuery();
			}
			
			if(type == 6) {
				String sql = prop.getProperty("newBoardSelectList");
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				
				rs = pstmt.executeQuery();
			}
			
			
			while(rs.next()) {
				Board board = new Board();

				board.setBoardNo( rs.getInt("BOARD_NO") );
				board.setBoardTitle( rs.getString("BOARD_TITLE") );
				board.setMemberNickname( rs.getString("MEMBER_NICK") );
				board.setCreateDate( rs.getString("CREATE_DT") );
				board.setReadCount( rs.getInt("READ_COUNT") );

				boardList.add(board);
			}


		}finally {
			close(rs);
			close(pstmt);
		}

		return boardList;
	}


	public int searchListCount(Connection conn, int type, String condition) throws Exception {

		int listCount = 0;

		try {
			String sql = prop.getProperty("searchListCount")  + condition ;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}

		}finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public List<Board> searchBoardList(
			Connection conn, Pagination pagination, int type, String condition)
					throws Exception{

		List<Board> boardList = new ArrayList<Board>();

		try {
			String sql = prop.getProperty("searchBoardList1")
					   + condition
					   + prop.getProperty("searchBoardList2");
			int start =  ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, type);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				Board board = new Board();

				board.setBoardNo( rs.getInt("BOARD_NO") );
				board.setBoardTitle( rs.getString("BOARD_TITLE") );
				board.setMemberNickname( rs.getString("MEMBER_NICK") );
				board.setCreateDate( rs.getString("CREATE_DT") );
				board.setReadCount( rs.getInt("READ_COUNT") );

				boardList.add(board);
			}


		}finally {
			close(rs);
			close(pstmt);
		}


		return boardList;
	}

	/**
	 * 내가 쓴 글 수 조회 DAO
	 * @param conn
	 * @param loginMember
	 * @return listCount
	 * @throws Exception
	 */
	public int mycCount(Connection conn, Member loginMember) throws Exception{
		int listCount = 0;

		try {
			String sql = prop.getProperty("mycCount");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMember.getMemberNo());

			rs = pstmt.executeQuery();

			if(rs.next()) {
				listCount = rs.getInt(1);
			}

		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	/**
	 * 내 글 목록 조회
	 * @param conn
	 * @param pagination
	 * @param loginMember
	 * @return
	 * @throws Exception
	 */
	public List<MyBoard> myContentList(Connection conn, Pagination pagination, Member loginMember) throws Exception{
		List<MyBoard> contentList = new ArrayList<MyBoard>();

		try {
			String sql = prop.getProperty("contentList");

			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, loginMember.getMemberNo());
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				MyBoard board = new MyBoard();

				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardName(rs.getString("BOARD_NM"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setCreateDate(rs.getString("CREATE_DT"));

				contentList.add(board);
			}

		}finally {
			close(rs);
			close(pstmt);
		}
		return contentList;
	}

	/**
	 * 북마크한 게시글 수 조회
	 * @param conn
	 * @param loginMember
	 * @return listCount
	 * @throws Exception
	 */
	public int bookmarkCount(Connection conn, Member loginMember) throws Exception{
		int listCount = 0;

		try {
			String sql = prop.getProperty("bookmarkCount");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMember.getMemberNo());

			rs = pstmt.executeQuery();

			if(rs.next()) {
				listCount = rs.getInt(1);
			}


		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	/**
	 * 북마크한 게시글 목록 조회
	 * @param conn
	 * @param pagination
	 * @param loginMember
	 * @return bookmarkList
	 * @throws Exception
	 */
	public List<MyBoard> bookmarkList(Connection conn, Pagination pagination, Member loginMember) throws Exception{
		List<MyBoard> bookmarkList = new ArrayList<MyBoard>();

		try {
			String sql = prop.getProperty("bookmarkList");

			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, loginMember.getMemberNo());
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				MyBoard board = new MyBoard();

				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNickname(rs.getString("MEMBER_NICK"));
				board.setCreateDate(rs.getString("CREATE_DT"));

				bookmarkList.add(board);
			}

		}finally {
			close(rs);
			close(pstmt);
		}

		return bookmarkList;
	}

	/**
	 * 좋아요한 게시글 수 조회
	 * @param conn
	 * @param loginMember
	 * @return listCount
	 * @throws Exception
	 */
	public int likeCount(Connection conn, Member loginMember) throws Exception{
		int listCount = 0;

		try {
			String sql = prop.getProperty("likeCount");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMember.getMemberNo());

			rs = pstmt.executeQuery();

			if(rs.next()) {
				listCount = rs.getInt(1);
			}

		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	/**
	 * 좋아요한 게시글 목록 조회
	 * @param conn
	 * @param pagination
	 * @param loginMember
	 * @return likeList
	 * @throws Exception
	 */
	public List<MyBoard> likeList(Connection conn, Pagination pagination, Member loginMember) throws Exception{
		
		List<MyBoard> likeList = new ArrayList<MyBoard>();

		try {
			String sql = prop.getProperty("likeList");

			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, loginMember.getMemberNo());
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				MyBoard board = new MyBoard();

				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNickname(rs.getString("MEMBER_NICK"));
				board.setCreateDate(rs.getString("CREATE_DT"));

				likeList.add(board);
			}

		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return likeList;
	}

	
	public BoardDetail selectBoardDetail(Connection conn, int boardNo) throws Exception {
		
		BoardDetail detail = null;
		
		try {
			String sql = prop.getProperty("selectBoardDetail");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				detail = new BoardDetail();
				
				detail.setBoardNo(rs.getInt(1));
				detail.setBoardTitle(rs.getString(2));
				detail.setBoardContent(rs.getString(3));
				detail.setCreateDate(rs.getString(4));
				detail.setUpdateDate(rs.getString(5));
				detail.setReadCount(rs.getInt(6));
				detail.setMemberNickname(rs.getString(7));
				detail.setProfileImage(rs.getString(8));
				detail.setMemberNo(rs.getInt(9));
				detail.setBoardName(rs.getString(10));
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return detail;
	}
	
	
	public List<BoardImage> selectImageList(Connection conn, int boardNo) throws Exception {
		List<BoardImage> imageList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectImageList");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardImage image = new BoardImage();
				
				image.setImageNo(rs.getInt(1));
				image.setImageReName(rs.getString(2));
				image.setImageOriginal(rs.getString(3));
				image.setImageLevel(rs.getInt(4));
				image.setBoardNo(rs.getInt(5));
				
				imageList.add(image);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return imageList;
	}
	
	
	public int nextBoardNo(Connection conn) throws Exception{
		int boardNo = 0;
		
		try {
			String sql = prop.getProperty("nextBoardNo");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return boardNo;
	}
	
	public int insertBoard(Connection conn, BoardDetail detail, int boardCode) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, detail.getBoardNo());
			pstmt.setString(2, detail.getBoardTitle());
			pstmt.setString(3, detail.getBoardContent());
			pstmt.setInt(4, detail.getMemberNo());
			pstmt.setInt(5, boardCode);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
	
	public int insertBoardImage(Connection conn, BoardImage image) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoardImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, image.getImageReName());
			pstmt.setString(2, image.getImageOriginal());
			pstmt.setInt(3, image.getImageLevel());
			pstmt.setInt(4, image.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	
	public int updateBoard(Connection conn, BoardDetail detail) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, detail.getBoardTitle());
			pstmt.setString(2, detail.getBoardContent());
			pstmt.setInt(3, detail.getBoardNo());
				
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int updateBoardImage(Connection conn, BoardImage img) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoardImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, img.getImageReName());
			pstmt.setString(2, img.getImageOriginal());
			pstmt.setInt(3, img.getBoardNo());
			pstmt.setInt(4, img.getImageLevel());
		
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	


	
	public int deleteBoardImage(Connection conn, String deleteList, int boardNo) throws Exception{

		int result = 0;
		
		try {
			// 							완성되지 않은 SQL
			String sql = prop.getProperty("deleteBoardImage")  + deleteList  + ")";
			// "DELETE FROM BOARD_IMG WHERE BOARD_NO = ? AND IMG_LEVEL IN ( 1,0 ) "
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int deleteBoard(Connection conn, int boardNo) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBoard");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int increaseReadCount(Connection conn, int boardNo) throws Exception {
		
		int result = 0;

		try {
			
		 String sql = prop.getProperty("increaseReadCount");	
			
		 pstmt = conn.prepareStatement(sql);
		 
		 pstmt.setInt(1, boardNo);
		 
		} finally {
			close(pstmt);
		}
		
		return result;
	}



}