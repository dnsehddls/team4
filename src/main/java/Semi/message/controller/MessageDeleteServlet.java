//package Semi.message.controller;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import Semi.message.model.service.MessageService;
//
//
//@WebServlet("messageDelete")
//public class MessageDeleteServlet extends HttpServlet{
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		try {
//			
//			MessageService service = new MessageService();
//
//			String[] arr = req.getParameterValues("data");
//			String data = String.join(",", arr);
//			
//			
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	
//		
//		
//		
//	}
//}
