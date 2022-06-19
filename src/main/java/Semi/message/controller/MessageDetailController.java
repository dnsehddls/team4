package Semi.message.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Semi.message.model.service.MessageService;
import Semi.message.model.vo.Message;

@WebServlet("/messageContent")
public class MessageDetailController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String t = req.getParameter("t"); // r/s
			System.out.println("tt : " + t);
			
			int messageNo = Integer.parseInt(req.getParameter("messageNo"));
			
			MessageService service = new MessageService();
			
			Message mContent = service.MessageDetail(messageNo, t);
			
			if(mContent.getReceiveDate() == null) {
				service.changeDate(messageNo);
			}
			
			req.setAttribute("mContent", mContent);
			req.setAttribute("t", t);
			
			String path = "/WEB-INF/views/message/messageContent.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			
			dispatcher.forward(req, resp);
						
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
