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

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("ID");
		String pw = req.getParameter("PW");
		Member memberCheck = new Member();
		memberCheck.setMemberID(id);
		memberCheck.setMemberPW(pw);
		try {
			MemberService service = new MemberService();
			Member loginMember = service.login(memberCheck);
			HttpSession session = req.getSession();
			
			session.setAttribute("loginMember", loginMember);
			
			resp.sendRedirect(req.getContextPath());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
