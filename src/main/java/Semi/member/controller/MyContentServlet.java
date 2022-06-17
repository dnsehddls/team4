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
import Semi.board.model.vo.MyBoard;
import Semi.member.model.vo.Member;

@WebServlet("/member/myPage/myContent")
public class MyContentServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int cp = 1;
			
			if(req.getParameter("cp") != null) { 
				cp = Integer.parseInt(req.getParameter("cp"));
			}
			
			
			
			// 세션에서 로그인 정보를 얻어옴
			HttpSession session = req.getSession();
			Member loginMember = (Member)(session.getAttribute("loginMember"));
			
			// 페이지네이션과 조회된 게시글 목록(List)을 Map에 담아옴
			Map<String, Object> map = new BoardService().myc(cp, loginMember);
			
			req.setAttribute("map", map);
			

			String path = "/WEB-INF/views/member/myContent.jsp";

			
			req.getRequestDispatcher(path).forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}