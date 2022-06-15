package Semi.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Semi.board.model.vo.Board;
import Semi.member.model.service.MemberService;

@WebServlet("/member/myPage/bookmark")
public class BookmarkListServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		try {
			
			HttpSession session = req.getSession();
			
			
			
			List<Board> boardList = new MemberService().bookmarkList();
			
			String path = "/WEB-INF/views/member/bookmark.jsp";

			req.getRequestDispatcher(path).forward(req, resp);

			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
