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
import Semi.board.model.vo.MyBoard;
import Semi.board.model.vo.Pagination;
import Semi.board.model.vo.ShowWindowInfo;

public class BoardDAO {


	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	private Properties prop;
	public BoardDAO() {
		try {
			prop = new Properties();
			String filePath = BoardDAO.class.getResource("/Semi/sql/Board-sql.xml").getPath();
			
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

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				boardName = rs.getString(1);
			}

		}finally {
			close(rs);
			close(pstmt);
		}

		return boardName;
	}


	public int getListCount(Connection conn, int type) throws Exception{
		int listCount = 0;

		try {

			String sql = prop.getProperty("getListCount");

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

	public List<Board> selectBoardList(Connection conn, Pagination pagination, int type) throws Exception {

		List<Board> boardList = new ArrayList<Board>();

		try {
			String sql = prop.getProperty("selectBoardList");
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
	 * �궡 湲� 紐⑸줉 議고쉶 DAO
	 * @param conn
	 * @param memberNo
	 * @return clist
	 * @throws Exception
	 */
	public List<MyBoard> myContent(Connection conn, int memberNo) throws Exception{
		List<MyBoard> clist = null;
		try {

			String sql = prop.getProperty("myContent");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				MyBoard mb = new MyBoard();

				mb.setBoardNo(rs.getInt(1));
				mb.setBoardName(rs.getString(2));
				mb.setBoardTitle(rs.getString(3));
				mb.setCreateDate(rs.getString(4));

				clist.add(mb);
			}


		}finally {
			close(rs);
			close(pstmt);
		}
		return clist;
	}

}
