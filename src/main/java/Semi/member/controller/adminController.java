package Semi.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Semi.member.model.service.AdminService;
import Semi.member.model.vo.Member;




@WebServlet("/admin/*") // admin으로 시작하는 모든 요청
public class adminController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath + "/admin/").length());
		
		AdminService service = new AdminService();
		
		try {
			
			
			// cp 추가하기
			// 회원목록 조회
	    	if(command.equals("memberList")) {
	    			    		
	    		
	    		int cp = 1;
	    		
	    		if(req.getParameter("cp") != null) {
	    			cp = Integer.parseInt(req.getParameter("cp"));
	    		}
	    		
	    		
	    		Map<String, Object> map = null;
	    		
	    		if(req.getParameter("key") == null) { // 일반 회원 목록
	    			
	    			map = service.selectAll(cp);
	    				    			
	    		} else { // 검색 회원 목록
	    			
	    			String key = req.getParameter("key");
	    			String query = req.getParameter("query");
	    			
	    			map = service.searchMember(key, query, cp);
	    			
	    		}
	    		
	    		req.setAttribute("map", map);	    			    		

	    		String path = "/WEB-INF/views/admin/memberList.jsp";
	    		
	    		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
	    		
	    		dispatcher.forward(req, resp);
	    		
	    	}
	    	
	 
	    	
	    	
	    	// 회원 상세 조회
	    	if(command.equals("memberDetail")) {  		
	    		
	    		String memberEmail = req.getParameter("memberEmail");
	    		
	    		Member memberDetail = service.memberDetail(memberEmail);	    		
	    		
	    		
	    		req.setAttribute("memberDetail", memberDetail);
	    		
	    		String path = "/WEB-INF/views/admin/memberDetail.jsp";
    		
	    		req.getRequestDispatcher(path).forward(req, resp);    		
	    				    			    			    			    		
	    	}
	    	
	    	
	    	// 회원 탈퇴
	    	if(command.equals("flagY")) {
	    		
	    		String memberEmail = req.getParameter("memberEmail");
	    		
	    		int result = service.memberFlagY(memberEmail);
	    		
	    		HttpSession session = req.getSession();
	    		
	    		String path = null;
	    		String message = null;
	    		
	    		if(result>0) {
	    			message = "회원 탈퇴 성공";
	    			path = "memberDetail?memberEmail=" + memberEmail;	
	    			
	    		}else {
	    			message = "회원 탈퇴 실패";
	    			path = "memberList";		
	    		}
	    		
	    		session.setAttribute("message", message);
	    		
	    		resp.sendRedirect(path);
	    		
	    	}
	    	
	    	
	    	
	    	// 탈퇴한 회원 복구
	    	if(command.equals("flagN")) {
	    		
	    		String memberEmail = req.getParameter("memberEmail");
	    		
	    		int result = service.memberFlagN(memberEmail);
	    		
	    		HttpSession session = req.getSession();
	    		
	    		String path = null;
	    		String message = null;
	    		
	    		if(result>0) {
	    			message = "탈퇴 회원 복구 성공";
	    			path = "memberDetail?memberEmail=" + memberEmail;	
	    			
	    		}else {
	    			message = "탈퇴 회원 복구 실패";
	    			path = "memberList";		
	    		}
	    		
	    		session.setAttribute("message", message);
	    		
	    		resp.sendRedirect(path);
	    		
	    	}
	    	
	    	
	
	    	// 신고 게시글 조회
	    	
	    	if(command.equals("reportList")) {
	    		
	    		
	    		int cp = 1;
	    		
	    		if(req.getParameter("cp") != null) {
	    			cp = Integer.parseInt(req.getParameter("cp"));
	    		}
	    		
	    		
	    		Map<String, Object> map = null;
	    		
	    		if(req.getParameter("key") == null) { // 일반 회원 목록
	    			
	    			map = service.reportedList(cp);
	    				    			
	    		}else { // 검색 신고 게시글 목록
	    			
	    			String key = req.getParameter("key");
	    			String query = req.getParameter("query");

	    			
	    			map = service.searchReported(key, query, cp);
	    			
	    		}
	    		
	    		req.setAttribute("map", map);	    			    		

	    		String path = "/WEB-INF/views/admin/admin-reported.jsp";
	    		
	    		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
	    		
	    		dispatcher.forward(req, resp);
	    		
	    	}
	    
	      	
	    	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
		
	}
	
}
