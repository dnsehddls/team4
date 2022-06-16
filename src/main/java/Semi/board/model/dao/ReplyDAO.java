package Semi.board.model.dao;

import static Semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Semi.board.model.vo.MyBoard;
import Semi.board.model.vo.Pagination;
import Semi.board.model.vo.Reply;
import Semi.member.model.vo.Member;

public class ReplyDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public ReplyDAO() {
		try {
			prop = new Properties();
			String filePath = BoardDAO.class.getResource
					("/Semi/sql/Reply_sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Reply> replyList(Connection conn, int boardNo) throws Exception{
		List<Reply> rList = new ArrayList<Reply>();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("replyList"));
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Reply reply = new Reply();
				reply.setReplyNo(rs.getInt(1));
				reply.setMemberNickname(rs.getString(2));
				reply.setMemberNo(rs.getInt(3));
				reply.setReplyContent(rs.getString(4));
				reply.setCreateDate(rs.getString(5));
				reply.setUpdateDate(rs.getString(6));
				reply.setLikeCount(rs.getInt(7));
				reply.setReportCount(rs.getInt(8));
				rList.add(reply);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return rList;
	}


	/**
	 * 내가 쓴 댓글 수 조회 DAO
	 * @param conn
	 * @param loginMember
	 * @return listCount
	 * @throws Exception
	 */
	public int myrCount(Connection conn, Member loginMember) throws Exception{
		int listCount = 0;
		
		try {
			String sql = prop.getProperty("myrCount");
			
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
	 * 내 댓글 목록 조회
	 * @param conn
	 * @param pagination
	 * @param loginMember
	 * @return replyList
	 * @throws Exception
	 */
	public List<MyBoard> myReplyList(Connection conn, Pagination pagination, Member loginMember) throws Exception{
		List<MyBoard> replyList = new ArrayList<MyBoard>();
		
		try {
			String sql = prop.getProperty("replyContent");
			
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
				board.setReplyContent(rs.getString("REPLY_CONTENT"));
				board.setCreateReplyDate(rs.getString("CREATE_REPLY"));
				board.setReplyNo(rs.getInt("REPLY_NO"));
				
				replyList.add(board);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return replyList;
	}

}