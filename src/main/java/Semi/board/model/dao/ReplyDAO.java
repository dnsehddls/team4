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

import Semi.board.model.vo.Reply;

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


	public int replyInsert(Connection conn, Reply reply) throws Exception{
		int result = -10;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertReply"));
			pstmt.setInt(1, reply.getMemberNo());
			pstmt.setString(2, reply.getReplyContent());
			pstmt.setInt(3, reply.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int replyDelete(Connection conn, int replyNo) throws Exception{
		int result = -10;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("replyDelete"));
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int replyUpdate(Connection conn, int replyNo, String replyContent) throws Exception{
		int result = -10;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("replyUpdate"));
			pstmt.setString(1, replyContent);
			pstmt.setInt(2,replyNo);
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

}