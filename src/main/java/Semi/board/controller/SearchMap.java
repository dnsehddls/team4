package Semi.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import Semi.common.MyRenamePolicy;

@WebServlet("/board/searchMap")
public class SearchMap extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String key = req.getParameter("confmkey");
		String url = req.getParameter("returnUrl");
		String type = req.getParameter("resultType");
		
		resp.sendRedirect(req.getContextPath());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/board/mapApi/jusoPopup.jsp";
		
	}
}
