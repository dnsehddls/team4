package Semi.message.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Semi.member.model.vo.Member;
import Semi.message.model.service.MessageService;
import Semi.message.model.vo.MessageDetail;


// 닉네임 번호 + 내용
@WebServlet("/sendData")
public class SendMessageController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(); // 세션 얻어오기
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		
		try {
				
			int myNo = loginMember.getMemberNo();
			String inputMessage = req.getParameter("inputMessage");
			int yourNo = Integer.parseInt(req.getParameter("receiveNo"));
			String msDate = req.getParameter("msDate");
			
			System.out.println(myNo + ", " + inputMessage + ", " + yourNo);
			
			MessageDetail messageDetail = new MessageDetail();
			messageDetail.setSendNo(myNo);
			messageDetail.setMessageContent(inputMessage);
			messageDetail.setReceiveNo(yourNo);
			
			MessageService service = new MessageService();
			
			int result = service.SendMessage(myNo, inputMessage, yourNo);
			
			if(result > 0) {
				session.setAttribute("message", "쪽지 보내기 완료");
				 req.setAttribute("", loginMember);
				
				
			} else {
				session.setAttribute("message", "쪽지 보내기 실패");				
			}
		
			String path = "/WEB-INF/views/message/sendMessage.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			
			dispatcher.forward(req, resp);
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}





