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

@WebServlet("/member/*")
public class NicknameDupCheckServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String nick = uri.substring( (contextPath + "/member/").length() );
		
		MemberService service = new MemberService();
		HttpSession session = req.getSession();
		
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		String memberNickname = req.getParameter("memberNickname");
				
		
		try {
			
			int result = 0;
			
			// 회원가입 닉네임 중복확인
			if(nick.equals("nicknameDupCheck")) {
				result = service.nicknameDupCheck(memberNickname);
				resp.getWriter().print(result);
			}
			
			// 내정보 닉네임 중복확인
			if(nick.equals("myPage/nicknameDupcheck")) {
				result = service.infoNicknameDupCheck(memberNickname, loginMember);
				resp.getWriter().print(result);
			}
			
			

			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
