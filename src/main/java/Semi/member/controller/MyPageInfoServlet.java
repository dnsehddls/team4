package Semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Semi.member.model.service.MemberService;
import Semi.member.model.vo.Member;

@WebServlet("/member/myPage/info")
public class MyPageInfoServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/myInfo.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	// 내정보 수정 요청
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memeberNickname = req.getParameter("memberNickname");
		String memberTel = req.getParameter("memberTel");
		String inputPw = req.getParameter("inputPw");
		
		System.out.println(inputPw);
		
		HttpSession session = req.getSession();
		
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		int memberNo = loginMember.getMemberNo();
		
		Member mem = new Member();
		
		mem.setMemberNo(memberNo);
		mem.setMemberNickname(memeberNickname);
		mem.setMemberTel(memberTel);
		
		try {
			
			int result = new MemberService().updateMember(mem, inputPw);
			
			if(result > 0) {
				loginMember.setMemberNickname(memeberNickname);
				loginMember.setMemberTel(memberTel);
				
				session.setAttribute("message", "회원정보가 수정되었습니다.");
				resp.sendRedirect(req.getContextPath() + "/member/myPage/info");


			}else {
				session.setAttribute("message", "현재 비밀번호가 일치하지 않습니다");
				resp.sendRedirect(req.getContextPath() + "/member/myPage/info");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
