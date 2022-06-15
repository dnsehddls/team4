package Semi.message.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Semi.message.model.dao.MessageDAO;

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
	public int SendMessage(String sendNick, String receiveNick, String inputMessage) throws Exception {

		Connection conn = getConnection();
		
		// 1. 다음 작성할 게시글 번호 얻어오기
		// -> BOARD 테이블 INSERT  /  BOARD_IMG 테이블 INSERT  / 반환값 (상세조회 번호)
		int result = dao.SendMessage(conn, sendNick, receiveNick, inputMessage);
		
		// 트랜잭션 처리
		if(result > 0) {
			commit(conn);
	
		}else { // 2, 3번에서 한 번이라도 실패한 경우
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
 	
 }


