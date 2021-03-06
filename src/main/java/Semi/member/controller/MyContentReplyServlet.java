package Semi.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Semi.board.model.service.ReplyService;
import Semi.member.model.vo.Member;

@WebServlet("/member/myPage/myContent-reply")
public class MyContentReplyServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int cp = 1;
			
			if(req.getParameter("cp") != null) {
				cp = Integer.parseInt(req.getParameter("cp"));
			}
			
			HttpSession session = req.getSession();
			
			Member loginMember = (Member)(session.getAttribute("loginMember"));
			
			Map<String, Object> map = new ReplyService().myr(cp, loginMember);
			
			req.setAttribute("map", map);

			String path = "/WEB-INF/views/member/myContent-reply.jsp";

			req.getRequestDispatcher(path).forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		

	}
}