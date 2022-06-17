package Semi.message.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Semi.message.model.dao.MessageDAO;
import Semi.message.model.vo.Message;

public class MessageService {
		 
	private MessageDAO dao = new MessageDAO();

	/** 특정 회원 조회 Service
	 * @param memberNickname
	 * @return receiveNo
	 * @throws Exception
	 */
	public int selectUser(String memberNickname) throws Exception{
	
		Connection conn = getConnection();
		
		int receiveNo = dao.selectUser(conn, memberNickname);
 
		close(conn);
		
		return receiveNo;
		
	}
	
	

	/** 쪽지보내기 Service
	 * @param sendNickname
	 * @param receiveNickname
	 * @param saveCheck
	 * @param inputMessage
	 * @return result
	 * @throws Exception
	 */
	public int SendMessage(int myNo, String inputMessage, int yourNo) throws Exception {

		Connection conn = getConnection();
		
		// 1. 다음 작성할 게시글 번호 얻어오기
		// -> BOARD 테이블 INSERT  /  BOARD_IMG 테이블 INSERT  / 반환값 (상세조회 번호)
		int result = dao.SendMessage(conn, myNo, inputMessage, yourNo);
		
		// 트랜잭션 처리
		if(result > 0) {
			commit(conn);
	
		}else { // 2, 3번에서 한 번이라도 실패한 경우
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}



	/** 쪽지 목록 조회 Service
	 * @param myNo
	 * @param type
	 * @return mList
	 * @throws Exception
	 */
	public List<Message> MessageList(int myNo, String type) throws Exception {
		
		Connection conn = getConnection();
		
		List<Message> mList = dao.MessageList(conn, myNo, type);
		
		close(conn);
		
		return mList;
	}



	/** 쪽지 detail 조회
	 * @param messageNo
	 * @return m
	 * @throws Exception
	 */
	public Message MessageDetail(int messageNo) throws Exception {
		
		Connection conn = getConnection();
		
		Message mContent = dao.MessageDetail(conn, messageNo);
		
		close(conn);
		
		return mContent;
	}
	
	
	
//	/** 받은 쪽지 삭제 Service
//	 * @param boardNo
//	 * @return result
//	 * @throws Exception
//	 */
//	public int deleteMessage1(int messageNo) throws Exception{
//		
//		Connection conn = getConnection();
//		
//		int result = dao.deleteMessage1(conn, messageNo);
//		
//		if(result > 0)	commit(conn);
//		else			rollback(conn);
//		
//		close(conn);
//		return result;
//	}
//	
//	/** 보낸 쪽지 삭제 Service
//	 * @param boardNo
//	 * @return result
//	 * @throws Exception
//	 */
//	public int deleteMessage2(int messageNo) throws Exception{
//		
//		Connection conn = getConnection();
//		
//		int result = dao.deleteMessage2(conn, messageNo);
//		
//		if(result > 0)	commit(conn);
//		else			rollback(conn);
//		
//		close(conn);
//		return result;
//	}
//	
	
 }





