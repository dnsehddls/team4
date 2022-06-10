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


@WebServlet("/user/*")
public class MainRequestController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
	    String contextPath = req.getContextPath();
	    String command = uri.substring((contextPath + "/user/").length());
	    try {
	    	//로그인 요청
	    	MemberService service = new MemberService();
	    	if(command.equals("login")) {
		    	String id = req.getParameter("ID");
		    	String pw = req.getParameter("PW");
		    	Member member = new Member();
		    	member.setMemberId(id);
		    	member.setMemberPw(pw);
		    	
		    	HttpSession session = req.getSession();
		    	
		    	Member loginSession = service.login(member);
		    	if(loginSession !=null) {
		    		session.setAttribute("loginMember", loginSession);
		    		session.setMaxInactiveInterval(3600);
		    	}else {
		    		session.setAttribute("message", "아이디 또는 비밀번호를 확인해주세요");
		    	}
		    	resp.sendRedirect(req.getContextPath());
	    	}
	    	//회원가입
	    	
	    	//계정찾기
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
