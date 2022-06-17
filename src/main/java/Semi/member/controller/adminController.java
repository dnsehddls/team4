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
	    				    			
	    		}else { // 검색 회원 목록
	    			
	    			String key = req.getParameter("key");
	    			String query = req.getParameter("query");
	    			
	    			map = service.searchMember(key, query, cp);
	    			
	    		}
	    		
	    		
	    		
	    		req.setAttribute("map", map);	    			    		

	    		String path = "/WEB-INF/views/admin/memberList.jsp";
	    		
	    		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
	    		
	    		dispatcher.forward(req, resp);
	    		
	    	}
	    	
	    	
	    	// 관리자 정보 수정
	    	if(command.equals("info")) {
	    		
	    		String path = "/WEB-INF/views/admin/adminPage-info.jsp";
	    		
	    		req.getRequestDispatcher(path).forward(req, resp);
	    		
	    		String memberEmail = req.getParameter("memberEmail");
	    		String memberNickname = req.getParameter("memberNickname");
	    		String memberTel = req.getParameter("memberTel");
	    		
//	    		String[] address = req.getParameterValues("memberAddress");
	    		
	    		HttpSession session = req.getSession();
	    		
	    		Member loginMember = (Member)(session.getAttribute("loginMember"));
	    		
	    		int memberNo = loginMember.getMemberNo();
	    		
	    		Member mem = new Member();
	    		
	    		mem.setMemberNo(memberNo);
	    		mem.setMemberNickname(memberNickname);
	    		mem.setMemberTel(memberTel);
	    		mem.setMemberEmail(memberEmail);
	    		
	    		int result = service.updateAdmin(mem);
	    		
	    		if(result>0) {
	    			session.setAttribute("message", "관리자 정보가 수정 되었습니다");
	    			
	    			loginMember.setMemberNickname(memberNickname);
					loginMember.setMemberTel(memberTel);
	    		}else {
	    			session.setAttribute("message", "관리자 정보 수정 실패");
	    		}	    		
	    		resp.sendRedirect("info");	    		
	    	}
	    	
	    	
	    	// 관리자 비밀번호 수정
	    	if(command.equals("changePw")) {
	    		
	    		String path = "/WEB-INF/views/admin/adminPage-changePw.jsp";
	    		
	    		req.getRequestDispatcher(path).forward(req, resp);
	    		
	    		String currentPw = req.getParameter("currentPw");
	    		String newPw = req.getParameter("newPw");
	    		
	    		HttpSession session = req.getSession();
	    		
	    		Member loginMember = (Member)(session.getAttribute("loginMember"));
	    		
	    		int memberNo = loginMember.getMemberNo();
	    		
	    		int result = service.adminChangePw(currentPw, newPw, memberNo);
	    		
	    		String rePath = null;
	    		
	    		if(result>0) {
	    			session.setAttribute("message", "관리자 비밀번호 변경 성공");
	    			 
	    			rePath = "info";
	    		}else {
	    			session.setAttribute("message", "현재 관리자 비밀번호가 일치하지 않습니다");
	    			
	    			rePath = "changePw";
	    		}
	    		resp.sendRedirect(rePath);
	    		
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
	    	
	    	
	    	
	    	// 게시글 좋아요 수 조회 
	    	if(command.equals("manageStandard")) {
	    		    		
	    		
	    		if(req.getParameter("changeNo") == null) {
	    			
	    			int standardNo = service.manageStandard();
	    			
	    			req.setAttribute("standardNo", standardNo);
	    			
	        		String path = "/WEB-INF/views/admin/admin-standard.jsp";
		    		
		    		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		    		
		    		dispatcher.forward(req, resp); 	
	    			
	    		}else {	    			
	    			
	    			int changeNo = Integer.parseInt(req.getParameter("changeNo"));
	    			
	    			int result = service.likeChange(changeNo);

	    			
	    			HttpSession session = req.getSession();
	    			
	    			String message = null;
	    			
	    			if(result>0) {
	    				message = "변경 성공";
	    				
	    			}else {
	    				message = "변경 실패";
	    			}
	    			
	    			session.setAttribute("message", message);
	    			resp.sendRedirect("manageStandard");
	    			
	    		}
	    		
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