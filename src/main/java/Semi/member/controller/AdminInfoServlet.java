package Semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Semi.member.model.service.AdminService;
import Semi.member.model.vo.Member;

@WebServlet("/admin/info")
public class AdminInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/admin/adminPage-info.jsp";

		req.getRequestDispatcher(path).forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		String memberEmail = req.getParameter("memberEmail");
		String memberNickname = req.getParameter("memberNickname");
		String memberTel = req.getParameter("memberTel");

//		String[] address = req.getParameterValues("memberAddress");

		HttpSession session = req.getSession();

		Member loginMember = (Member)(session.getAttribute("loginMember"));

		int memberNo = loginMember.getMemberNo();

		try {
			AdminService service = new AdminService();

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

		}catch(Exception e) {
			e.printStackTrace();
		}



	}

}