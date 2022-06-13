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
	    		
//	    		List<Member> list = service.selectAll();
	    		
	    		String path = "/WEB-INF/views/admin/admin-memberList.jsp";
	    		
	    		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
	    		
	    		dispatcher.forward(req, resp);
	    		
	    		int cp = 1;
	    		
	    		if(req.getParameter("cp") != null) {
	    			cp = Integer.parseInt(req.getParameter("cp"));
	    		}
	    		
	    		
	    		Map<String, Object> map = null;
	    		
	    		if(req.getParameter("memberEmail") == null) { // 일반 회원 목록
	    			
	    			map = service.selectAll(cp);
	    				    			
	    		}else {
	    			
	    			String memberEmail = req.getParameter("memberEmail");
	    			
	    			map = service.searchMember(memberEmail);
	    			
	    		}
	    		
	    		req.setAttribute("map", map);	    			    		

	    		
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
	    	
	    	
	    	// 회원 탈퇴 / 복구, 회원 상세 조회
	    	if(command.equals("memberDetail")) {
	    		
	    		String path = "/WEB-INF/views/admin/memberDetail.jsp";
	    		
	    		req.getRequestDispatcher(path).forward(req, resp);
	    		
	    		HttpSession session = req.getSession();
	    		
	    		String memberEmail = req.getParameter("memberEmail");
	    		
	    		
	    		Member memberDetail = service.memberDetail(memberEmail);
	    		
	    		req.setAttribute("memberDetail", memberDetail);
	    		
	    		int result = service.changeSecession(memberEmail);
	    		
	    		if(result>0) {
	    			session.setAttribute("message",	"회원 탈퇴 변경 성공");
	    			
	    			path = "/WEB-INF/views/admin/memberDetail.jsp";
	    			
	    		}else {
	    			session.setAttribute("message", "회원 탈퇴 변경 실패");
	    			
	    			path = "/WEB-INF/views/admin/memberDetail.jsp";
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
