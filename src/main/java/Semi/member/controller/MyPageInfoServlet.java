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

@WebServlet("member/myPage/info")
public class MyPageInfoServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/myInfo.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	// 내정보 수정 요청
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String newPw = req.getParameter("newPw2");
		String memeberNickname = req.getParameter("memberNick");
		String memberTel = req.getParameter("memberTel");
		String memberEmail = req.getParameter("memberEmail");
		
		HttpSession session = req.getSession();
		
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		int memberNo = loginMember.getMemberNo();
		
		Member mem = new Member();
		
		mem.setMemberNo(memberNo);
		mem.setMemberPW(newPw);
		mem.setMemberNickname(memeberNickname);
		mem.setMemberTel(memberTel);
		mem.setMemberEmail(memberEmail);
		
		try {
			
			int result = new MemberService().updateMember(mem);
			
			if(result > 0) {
				loginMember.setMemberPW(newPw);
				loginMember.setMemberNickname(memeberNickname);
				loginMember.setMemberTel(memberTel);
				loginMember.setMemberEmail(memberEmail);
			}else {
				session.setAttribute("message", "회원 정보 수정 실패");
			}
			
			resp.sendRedirect(req.getContextPath() + "/member/myPage/info");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
