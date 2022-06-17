package Semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Semi.member.model.service.MemberService;
import Semi.member.model.vo.Member;

@WebServlet("/member/myPage/secession")
public class SecessionServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/secession.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String inputPW = req.getParameter("inputPw");
		HttpSession session = req.getSession();	
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		int memberNo = loginMember.getMemberNo();
		
		try {
			
			int result = new MemberService().secession(memberNo, inputPW);
			
			String path = null;
			
			if(result > 0) {
				
				session.invalidate(); // 세션 무효화
				
				session = req.getSession(); // 세션 다시 얻어오기
								
				session.setAttribute("message", "탈퇴되었습니다.");
				path = req.getContextPath();
				
				Cookie c = new Cookie("savedId", "");
				c.setMaxAge(0);
				c.setPath(req.getContextPath());
				resp.addCookie(c);
				
			}else {
				session.setAttribute("message", "비밀번호가 일치하지 않습니다.");
				path = req.getContextPath() + "/member/myPage/secession";
			}
			
			resp.sendRedirect(path);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}