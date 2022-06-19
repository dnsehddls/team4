package Semi.message.model.dao;

import static Semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public int SendMessage(Connection conn, int myNo, String inputMessage, int yourNo) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("sendMessage");
			
			pstmt = conn.prepareStatement(sql);
			
	        pstmt.setInt(1, myNo);
	        pstmt.setString(2, inputMessage);
	        pstmt.setInt(3, yourNo);
	
			result = pstmt.executeUpdate();

	    	}finally {
	 	  	close(pstmt);
		    }
		
		return result;
	}


	/** 쪽지 목록 조회 DAO
	 * @param conn
	 * @param myNo
	 * @param type
	 * @return mList
	 * @throws Exception 
	 */
	public List<Message> MessageList(Connection conn, int myNo, String type) throws SQLException {
		
		List<Message> mList = new ArrayList<Message>();
		
		try {
			System.out.println("type : " + type);
			System.out.println("myNo : " + myNo);
			
			int convType = 0;
			if(type.equals("s")) convType = 1;
			else convType = 2;
			
			System.out.println("convType : " + convType);
			
			String sql = prop.getProperty("messageList");
			
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, type);
//			pstmt.setString(2, type);
//			pstmt.setString(3, type);
//			pstmt.setInt(4, myNo);
//			pstmt.setString(5, type);
//			pstmt.setInt(6, myNo);
			
			
			pstmt.setInt(1, convType);
			pstmt.setInt(2, convType);
			pstmt.setInt(3, convType);
			pstmt.setInt(4, convType);
			pstmt.setInt(5, convType);
			pstmt.setInt(6, myNo);
			pstmt.setInt(7, convType);
			pstmt.setInt(8, myNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Message m = new Message();
				
				m.setMessageNo(rs.getInt(1));
				m.setSendNo(rs.getInt(2));
				m.setMessageDate(rs.getString(3));
				System.out.println("len : " + rs.getString(4).length());
				if(rs.getString(4).length() > 20) {
					m.setMessageContent(rs.getString(4).substring(0, 19));
				} else {
					m.setMessageContent(rs.getString(4));
				}
				m.setSendDelete(rs.getString(5).charAt(0));
				m.setReceiveNo(rs.getInt(6));
				m.setReceiveDate(rs.getString(7));
				m.setReceiveDelete(rs.getString(8).charAt(0));
				m.setMemberNickname(rs.getString(9));
				
//				m.setMessageNo(rs.getInt("MS_NO"));
//				m.setSendNo(rs.getInt("SEND_NO"));
//				m.setMessageDate(rs.getString("MS_DT"));
//				m.setMessageContent(rs.getString("MS_CONTENT");
//				m.setSendDelete(rs.getString("SEND_DEL_ST");
//				m.setReceiveNo(rs.getInt("RECEIVE_NO"));
//				m.setReceiveDate(rs.getString("RECEIVE_DATE"));
//				m.setReceiveDelete(rs.getString("RECEIVE_DEL_ST");

				System.out.println("rs.getint : " + rs.getString(7));
				
				mList.add(m);
			}
			System.out.println("count : " + mList.size());
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return mList;
	}


	/** 쪽지 detail 조회
	 * @param messageNo
	 * @return mContent
	 * @throws Exception
	 */
	public Message MessageDetail(Connection conn, int messageNo, String t) throws Exception {

		Message mContent = new Message();
		
		try {
			
			int convType = 0;
			if(t.equals("s")) convType = 1;
			else convType = 2;
			
			String sql = prop.getProperty("messageDetail");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, convType);
			pstmt.setInt(2, convType);
			pstmt.setInt(3, messageNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				
//				m.setMessageNo(rs.getInt("MS_NO"));
//				m.setSendNo(rs.getInt("SEND_NO"));
//				m.setReceiveNo(rs.getInt("RECEIVE_NO"));
//				m.setSendDelete(rs.getString("SEND_DEL_ST").charAt(0));
//				m.setReceiveDelete(rs.getString("RECEIVE_DEL_ST").charAt(0));
				
				mContent.setMessageDate(rs.getString("MESSAGE_DT"));
				mContent.setMessageContent(rs.getString("MS_CONTENT"));
				mContent.setReceiveDate(rs.getString("RECEIVE_DATE"));
				mContent.setMemberNickname(rs.getString("MEMBER_NICK"));
				
				System.out.println(mContent.getReceiveDate());
			}

		} finally {
			close(rs);
			close(pstmt);
		}
		return mContent;
		}


	/** 수신확인 변경
	 * @param conn
	 * @param messageNo
	 * @return result
	 * @throws Exception
	 */
	public int changeDate(Connection conn, int messageNo) throws Exception {
		
		int result = 0;
		
		 try {
			String sql = prop.getProperty("changeDate");
				
			pstmt = conn.prepareStatement(sql);
			
	        pstmt.setInt(1, messageNo);
	
			result = pstmt.executeUpdate();

    	}finally {
	    		
	 	  	close(pstmt);
		   
    	}
			
		
		return result;
	}
		
	


//	/** 받은 쪽지 삭제 DAO
//	 * @param conn
//	 * @param boardNo
//	 * @return result
//	 * @throws Exception
//	 */
//	public int deleteMessage1(Connection conn, int messageNo) throws Exception {
//		
//		int result = 0;
//		
//		try {
//			
//			String sql = prop.getProperty("deleteMessage1");
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, messageNo);
//			
//			result = pstmt.executeUpdate();
//			
//		}finally {
//			close(pstmt);
//		}
//		
//		return result;
//		
//		}
//
//
//	/** 보낸 쪽지 삭제 DAO
//	 * @param conn
//	 * @param messageNo
//	 * @return result
//	 * @throws Exception
//	 */
//	public int deleteMessage2(Connection conn, int messageNo) throws Exception{
//
//		int result = 0;
//		
//		try {
//			
//			String sql = prop.getProperty("deleteMessage2");
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, messageNo);
//			
//			result = pstmt.executeUpdate();
//			
//		}finally {
//			close(pstmt);
//		}
//		
//		return result;
//	}




}
 		