package Semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Semi.member.model.service.MemberService;
import Semi.member.model.vo.Member;

@WebServlet("/member/userAccountFind/*")
public class UserAccountFindController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String requestPath = uri.substring((contextPath+"/userAcountFind/").length());
		
		MemberService service = new MemberService();
		try {						//경로 수정예정
			if(requestPath.equals("계정찾기")) {
				String memberEmail = req.getParameter("이메일");
				String memberTel = req.getParameter("전화번호 들어가겟찌");
//				Member findAccount = service.accountfind(memberEmail,memberTel);
				
			}
			
			if(requestPath.equals("비번찾기")) {
				
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
