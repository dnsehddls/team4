package Semi.member.model.service;

import static Semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Semi.board.model.vo.Board;
import Semi.board.model.vo.Pagination;
import Semi.member.model.dao.MemberDAO;
import Semi.member.model.vo.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	/** 로그인 
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public Member login(Member user) throws Exception{
		Connection conn = getConnection();
		Member result = dao.login(conn, user);
		close(conn);
		return result;
	}
	
	
	
	/**
	 * 회원가입 Service
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member mem) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, mem);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}

	/**
	 * 이메일 중복 검사 Service
	 * @param memberEmail
	 * @return result
	 * @throws Exception
	 */
	public int emailDupCheck(String memberEmail) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.emailDupCheck(conn, memberEmail);
		
		close(conn);
		
		return result;
	}

	/**
	 * 닉네임 중복 검사 Service
	 * @param memberNickname
	 * @return result
	 * @throws Exception
	 */
	public int nicknameDupCheck(String memberNickname) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.nicknameDupCheck(conn, memberNickname);
		
		close(conn);
		
		return result;
		
		
	}
	
	/**
	 * 내정보 수정 닉네임 중복 검사 Service
	 * @param memberNickname
	 * @return result
	 * @throws Exception
	 */
	public int infoNicknameDupCheck(String memberNickname, Member loginMember) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.nicknameDupCheck(conn, memberNickname);
		
		if(result == 1) {
			if(memberNickname.equals(loginMember.getMemberNickname())) {
				result = 0;
			}
		}
		
		close(conn);
		
		return result;
	}
	

	/**
	 * 회원가입 아이디 중복 검사 Service
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String memberId) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.idDupCheck(conn, memberId);
		
		close(conn);
		
		return result;
	}
	
	

	/**
	 * 인증번호 DB 추가 Service
	 * @param inputEmail
	 * @param cNumber
	 * @return result
	 * @throws Exception
	 */
	public int insertCertification(String inputEmail, String cNumber) throws Exception{
		
		Connection conn = getConnection();
		
		// 1) 입력한 이메일과 일치하는 값이 있으면 수정 (UPDATE)
		int result = dao.updateCertification(conn, inputEmail, cNumber);
		
		// 2) 일치하는 이메일이 없는 경우 
		if(result == 0) {
			result = dao.insertCertification(conn, inputEmail, cNumber);
		}
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}

	/**
	 * 인증번호 확인 Service
	 * @param inputEmail
	 * @param cNumber
	 * @return result
	 * @throws Exception
	 */
	public int checkNumber(String inputEmail, String cNumber) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.checkNumber(conn, inputEmail, cNumber);
		
		close(conn);
		
		return result;
	}

	/** 계정찾기 (이메일)
	 * @param memberEmail
	 * @param memberTel
	 * @return
	 * @throws Exception
	 */
//	public Member accountfind(String memberEmail, String memberTel) throws Exception{
//		Connection conn = getConnection();
//		Member accountInfo = dao.accountFind(conn, memberEmail, memberTel);
//		close(conn);
//		return accountInfo;
//	}


	/**
	 * 회원 정보 수정 Service
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member mem) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, mem);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/**
	 * 회원 탈퇴 Service
	 * @param memberNo
	 * @param inputPW
	 * @return result
	 * @throws Exception
	 */
	public int secession(int memberNo, String inputPW) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.secession(conn, memberNo, inputPW);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/**
	 * 비밀번호 변경 Service
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int changePw(String currentPw, String newPw, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.changePw(conn, currentPw, newPw, memberNo);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}


	public List<Board> bookmarkList() {
		// TODO Auto-generated method stub
		return null;
	}


	

	

}
