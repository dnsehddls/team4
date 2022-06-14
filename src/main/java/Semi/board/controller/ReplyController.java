package Semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Semi.board.model.service.ReplyService;
import Semi.board.model.vo.Reply;


@WebServlet("/reply/*")
public class ReplyController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
	    String contextPath = req.getContextPath();
	    String command = uri.substring((contextPath + "/reply/").length());
	    
	    ReplyService service = new ReplyService();
	    try {
	    	
	    	if(command.equals("replyList")) {
	    		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
	    		List<Reply> rList = service.replyList(boardNo);
	    		new Gson().toJson(rList,resp.getWriter());
	    	}
	    	
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
