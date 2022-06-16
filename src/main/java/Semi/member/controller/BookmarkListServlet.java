package Semi.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Semi.board.model.service.BoardService;
import Semi.board.model.vo.Board;
import Semi.member.model.service.MemberService;
import Semi.member.model.vo.Member;

@WebServlet("/member/myPage/bookmark")
public class BookmarkListServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int cp = 1;
			
			if(req.getParameter("cp") != null) {
				cp = Integer.parseInt(req.getParameter("cp"));
			}
			
			HttpSession session = req.getSession();
			
			Member loginMember = (Member)(session.getAttribute("loginMember"));
			
			Map<String, Object> map = new BoardService().bookmarkList(cp, loginMember);
			
			req.setAttribute("map", map);
						
			String path = "/WEB-INF/views/member/bookmark.jsp";

			req.getRequestDispatcher(path).forward(req, resp);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
