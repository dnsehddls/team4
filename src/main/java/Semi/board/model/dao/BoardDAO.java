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
//			관리자 페이지에 기능을 추가할 시 pstmt
			
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
				show.setBoardType(rs.getInt("BOARD_CD"));
				sList.add(show);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		return sList;
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
			
			// BETWEEN 구문에 들어갈 범위 계산
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
			Connection conn, Pagination pagination, int type, String condition) throws Exception{
		
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			String sql = prop.getProperty("searchBoardList1")
					   + condition
					   + prop.getProperty("searchBoardList2");
			
			// BETWEEN 구문에 들어갈 범위 계산
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
