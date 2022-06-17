package Semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.border.Border;

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
	    	int boardNo = -10;
	    	if(req.getParameter("boardNo") != null) {
	    		boardNo = Integer.parseInt(req.getParameter("boardNo"));
	    	}
	    	int result = -10;
	    	
	    	if(command.equals("replyList")) {
	    		List<Reply> rList = service.replyList(boardNo);
	    		new Gson().toJson(rList,resp.getWriter());
	    	}
	    	
	    	if(command.equals("updateReply")) {
	    		int replyNo = Integer.parseInt(req.getParameter("replyNo"));
	    		String replyContent = req.getParameter("replyContent");
	    		result = service.replyUpdate(replyNo,replyContent);
	    	}
	    	
	    	if(command.equals("deleteReply")) {
	    		int replyNo = Integer.parseInt(req.getParameter("replyNo"));
	    		result = service.replyDelete(replyNo);
	    	}
	    	
	    	if(command.equals("insertReply")) {
	    		Reply reply = new Reply();
	    		
	    		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
	    		reply.setMemberNo(memberNo);
	    		
	    		String content = req.getParameter("replyContent");
	    		reply.setReplyContent(content);
	    		reply.setBoardNo(boardNo);
	    		result = service.replyInsert(reply);
	    	}
	    	
	    	if(!command.equals("replyList")) {
	    		resp.getWriter().print(result);
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