package Semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Semi.board.model.vo.Report;
import Semi.member.model.service.AdminService;
import Semi.member.model.vo.Member;

@WebServlet("/admin/changePw")
public class AdminChangePwServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/admin/adminPage-changePw.jsp";

		req.getRequestDispatcher(path).forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String currentPw = req.getParameter("currentPw");
		String newPw = req.getParameter("newPw");

		HttpSession session = req.getSession();

		Member loginMember = (Member)(session.getAttribute("loginMember"));

		int memberNo = loginMember.getMemberNo();

		System.out.println(memberNo);

		try {


			AdminService service = new AdminService();

			int result = service.adminChangePw(currentPw, newPw, memberNo);


			String path = null;

			if(result>0) {
				session.setAttribute("message", "관리자 비밀번호 변경 성공");
				path = "/admin/info";
			}else {
				session.setAttribute("message", "현재 관리자 비밀번호가 일치하지 않습니다");
				path = "/admin/changePw";
			}
			resp.sendRedirect(req.getContextPath()+path);

		}catch(Exception e) {
			e.printStackTrace();

		}

	}

}