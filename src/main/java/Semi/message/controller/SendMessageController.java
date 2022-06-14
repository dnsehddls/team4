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


// 닉네임 번호 + 내용
@WebServlet("/sendData")
public class SendMessageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(); // 세션 얻어오기
		
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		int memberNo = loginMember.getMemberNo();
		
		try {
			
			String sendNick = loginMember.getMemberNickname();
			String receiveNick = "테스트닉네임2";
					//req.getParameter("receiveNickname");
			String inputMessage = req.getParameter("inputMessage");
			
			System.out.println(sendNick + ", " + receiveNick + ", " + inputMessage);
			MessageService service = new MessageService();
			
			int result = service.SendMessage(sendNick, receiveNick, inputMessage);
			
			if(result > 0) {
			
				session.setAttribute("message", "쪽지 보내기 완료");
				
			}
		
			String path = "/WEB-INF/views/message/sendMessage.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			
			dispatcher.forward(req, resp);
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}





