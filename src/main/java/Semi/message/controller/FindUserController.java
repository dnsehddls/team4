package Semi.message.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import Semi.member.model.vo.Member;
import Semi.message.model.service.MessageService;
import Semi.message.model.vo.MessageDetail;


// 특정 회원 조회
@WebServlet("/sendView/*")
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
		String command = uri.substring(  (contextPath + "/sendView/").length()  );
		System.out.println(uri);
		MessageService service = new MessageService();
		
		try {
			System.out.println("findUser");
			
			if(command.equals("findNo")) {
				
				String memberNickname = req.getParameter("memberNickname");
				System.out.println("memberNickname : " + memberNickname);
				int receiveNo = service.selectUser(memberNickname);
				
				
//					Member mm = new Member();
//					mm.setMemberNickname(memberNickname);
//					mm.setMemberNo(receiveNo);
//					req.setAttribute("memberNickname", memberNickname);
//					req.setAttribute("receiveNo", receiveNo);
//					resp.getWriter().print(mm);
//					//resp.getWriter().print(receiveNo);
//					//JSONParser parser = new JSONParser();
//					//Object obj = parser.parse(resp);
//					
//					//new Gson().toJson(rList, resp.getWriter());

					
					// 2) JSON-Simple 라이브러리에서 제공하는 JSONObject 사용
				if(receiveNo > 0) {
					JSONObject obj = new JSONObject(); // Map 형식의 객체
					obj.put("memberNickname", memberNickname);
					obj.put("receiveNo", receiveNo);
					
					
					// JSONObject의 toString() 메서드는
					// JSON 형태로 출력될 수 있도록 오버라이딩이 되어있다!
					resp.getWriter().print(obj.toString());
					
				} else {
					resp.getWriter().print(receiveNo); // null
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

		
		
//		try {
//			
//			if(command.equals("sendData")){
//				
//				HttpSession session = req.getSession(); // 세션 얻어오기
//				
//				Member loginMember = (Member)session.getAttribute("loginMember");
//				
//				int myNo = loginMember.getMemberNo();
//				String inputMessage = req.getParameter("inputMessage");
//				int yourNo = Integer.parseInt(req.getParameter("receiveNo"));
//				
//				System.out.println(myNo + ", " + inputMessage + ", " + yourNo);
//				
//				int result = service.SendMessage(myNo, inputMessage, yourNo);
//				
//				if(result > 0) {
//					
//					JSONObject obj = new JSONObject(); // Map 형식의 객체
//					obj.put("myNo", myNo);
//					obj.put("inputMessage", inputMessage);
//					obj.put("yourNo", yourNo);
//					
//										
//					// JSONObject의 toString() 메서드는
//					// JSON 형태로 출력될 수 있도록 오버라이딩이 되어있다!
//					resp.getWriter().print(obj.toString());			
//				}
//			}
//			
//				
//
//		
//			String path = "/WEB-INF/views/message/sendMessage.jsp";
//			
//			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
//			
//			dispatcher.forward(req, resp);
//			
//			
//		} catch(Exception e){
//			e.printStackTrace();
//		}
	

	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//	String uri = req.getRequestURI();
//	String contextPath = req.getContextPath();
//	String command = uri.substring(  (contextPath + "/sendView/").length()  );
//	System.out.println(uri);
//	
//	MessageService service = new MessageService();
//
//	try {
//	
//	if(command.equals("sendData")){
//		
//		HttpSession session = req.getSession(); // 세션 얻어오기
//		
//		Member loginMember = (Member)session.getAttribute("loginMember");
//		
//		int myNo = loginMember.getMemberNo();
//		String inputMessage = req.getParameter("inputMessage");
//		int yourNo = Integer.parseInt(req.getParameter("receiveNo"));
//		
//		System.out.println(myNo + ", " + inputMessage + ", " + yourNo);
//		
//		int result = service.SendMessage(myNo, inputMessage, yourNo);
//		
//		if(result > 0) {
//			
//			JSONObject obj = new JSONObject(); // Map 형식의 객체
//			obj.put("myNo", myNo);
//			obj.put("inputMessage", inputMessage);
//			obj.put("yourNo", yourNo);
//			
//								
//			// JSONObject의 toString() 메서드는
//			// JSON 형태로 출력될 수 있도록 오버라이딩이 되어있다!
//			resp.getWriter().print(obj.toString());			
//			}
//		}
//	
//		HttpSession session = req.getSession(); // 세션 얻어오기
//		
//		Member loginMember = (Member)session.getAttribute("loginMember");
//				
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}

}
