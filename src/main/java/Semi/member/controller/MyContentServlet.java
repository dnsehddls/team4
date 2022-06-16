package Semi.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Semi.board.model.service.BoardService;
import Semi.board.model.vo.MyBoard;
import Semi.member.model.vo.Member;

@WebServlet("/member/myPage/myContent")
public class MyContentServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			BoardService service = new BoardService();
			
			HttpSession session = req.getSession();
			
			Member loginMember = (Member)(session.getAttribute("loginMember"));
			
			int memberNo = loginMember.getMemberNo();
			
			/* List<MyBoard> myContent = service.myContent(memberNo); */
			
			
		}catch(Exception e) {
			
		}
		String path = "/WEB-INF/views/member/myContent.jsp";
		
		
		
		req.getRequestDispatcher(path).forward(req, resp);
	}
	

}
