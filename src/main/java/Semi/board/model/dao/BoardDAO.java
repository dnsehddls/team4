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
			String filePath = BoardDAO.class.getResource
					("/Semi/sql/Board_sql.xml").getPath();
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
//			������ �������� ����� �߰��� �� pstmt
			
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


}
