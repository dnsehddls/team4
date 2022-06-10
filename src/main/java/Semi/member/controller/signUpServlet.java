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
public class signUpServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/signUp.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memberId = req.getParameter("memberId");
		String memberPw = req.getParameter("memberPw");
		String memberName = req.getParameter("memberName");
		String memberNickname = req.getParameter("memberNickname");
		String memberTel = req.getParameter("memberTel");
		String memberEmail = req.getParameter("memberEmail");
		
		Member mem = new Member();
		
		mem.setMemberId(memberId);
		mem.setMemberPw(memberPw);
		mem.setMemberName(memberName);
		mem.setMemberNickname(memberNickname);
		mem.setMemberTel(memberTel);
		mem.setMemberEmail(memberEmail);
		
		try {
			
			MemberService service = new MemberService();
			
			int result = service.signUp(mem);
			
			HttpSession session = req.getSession();
			
			if(result > 0) { // 성공
				session.setAttribute("message", "회원 가입 성공!");
			}else { // 실패
				session.setAttribute("message", "회원 가입 실패");
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
