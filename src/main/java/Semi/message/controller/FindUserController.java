package Semi.message.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Semi.member.model.vo.Member;
import Semi.message.model.service.MessageService;


// 특정 회원 조회
@WebServlet("/findUser")
public class FindUserController extends HttpServlet {

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		try {
//			System.out.println("1234");
////			HttpSession session = req.getSession();
////			Member aaa = (Member)session.getAttribute("loginMember");
////			String nnn = aaa.getMemberNickname();
////			System.out.println("nnnnnnn : " + nnn);
//			String memberNickname = req.getParameter("memberNickname");
//			System.out.println("nickname : " + memberNickname);
//			
//			MessageService service = new MessageService();
//			
//			int receiveNo = service.selectUser(memberNickname);
//			System.out.println("receiveNo : " + receiveNo);
//			
//			if(receiveNo > 0) {
//				
//				req.setAttribute("receiveName", receiveNo);
//			}
//		
//			// 화면만 띄어서 테스트
//			String path = "/WEB-INF/views/message/sendMessage.jsp";
//			
//			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
//			
//			dispatcher.forward(req, resp);
//			
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring(  (contextPath + "/findUser/").length()  );
		
		MessageService service = new MessageService();
		
		try {
			
			if(command.equals("findNo")) {
				
				String memberNickname = req.getParameter("memberNickname");

				int receiveNo = service.selectUser(memberNickname);
				
				if(receiveNo > 0) {
					req.setAttribute("memberNickname", memberNickname);
					req.setAttribute("receiveNo", receiveNo);
					resp.getWriter().print(memberNickname);
				}
				
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}





