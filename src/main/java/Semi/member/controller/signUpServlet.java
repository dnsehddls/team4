package Semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Semi.member.model.service.MemberService;
import Semi.member.model.vo.Member;

@WebServlet("/member/signUp")
public class SignUpServlet extends HttpServlet{	
	
	// GET방식 요청 시 JSP로 바로 응답할 수 있도록 요청 위임
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/signUp.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	// POST 방식 요청 시 회원가입 서비스 수행
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 전달된 파라미터를 모두 변수에 저장
		String memberId = req.getParameter("memberId");
		String memberPw = req.getParameter("memberPW");
		String memberName = req.getParameter("memberName");
		String memberNickname = req.getParameter("memberNickname");
		String memberTel = req.getParameter("memberTel");
		String memberEmail = req.getParameter("memberEmail");
		
		// 파라미터를 하나의 Member 객체에 저장
		Member mem = new Member();
		
		mem.setMemberID(memberId);
		mem.setMemberName(memberName);
		mem.setMemberNickname(memberNickname);
		mem.setMemberTel(memberTel);
		mem.setMemberEmail(memberEmail);
		mem.setMemberPW(memberPw);
	
		
		try {
			
			MemberService service = new MemberService();
			
			// 회원가입 서비스 호출 후 결과 반환 받기
			int result = service.signUp(mem);
			
			// 서비스 결과에 따라서 message를 다르게 하여 메인 페이지 재요청(redirect)
			HttpSession session = req.getSession();
			
			if(result>0) { // 성공
				session.setAttribute("message", "회원 가입 성공!");
			}else { // 실패
				session.setAttribute("message", "회원 가입 실패...");
			}
			
			resp.sendRedirect(req.getContextPath());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
