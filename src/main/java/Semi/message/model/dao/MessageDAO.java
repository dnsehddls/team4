package Semi.message.model.dao;

import static Semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Semi.member.model.dao.MemberDAO;
import Semi.message.model.vo.Message;



public class MessageDAO {
	
	private Properties prop;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MessageDAO() {
		try {
			prop = new Properties();
			String filePath = MemberDAO.class.getResource
					("/Semi/sql/Message_sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** 특정 회원 조회 DAO
	 * @param conn
	 * @param memberNickname
	 * @return receiveName
	 * @throws Exception
	 */
	public int selectUser(Connection conn, String memberNickname) throws Exception {
 		
	int receiveNo = 0;
	
	try {
		
		String sql = prop.getProperty("findUser");
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, memberNickname);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			receiveNo = rs.getInt(1);

		}

	} finally {
		close(rs);
		close(pstmt);
	}
	return receiveNo;
	}


	/** 쪽지보내기 DAO
	 * @param conn
	 * @param sendNickname
	 * @param receiveNickname
	 * @param saveCheck
	 * @param inputMessage
	 * @return result
	 * @throws Exception
	 */
	public int SendMessage(Connection conn, String sendNick, String receiveNick, String inputMessage) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("sendMessage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sendNick);
			pstmt.setString(2, inputMessage);
			pstmt.setString(3, receiveNick);
			
			rs = pstmt.executeQuery();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
}
	
 		
 		
 		