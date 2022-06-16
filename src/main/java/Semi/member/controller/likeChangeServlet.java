package Semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Semi.member.model.service.AdminService;

@WebServlet("")
public class likeChangeServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/admin/admin-standard.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int changeNo = Integer.parseInt("changeNo");
		
		int standardNo = req.get
		
		try {
			AdminService service = new AdminService();
			
			int standardNo = service.manageStandard();
			
			req.setAttribute("standardNo", standardNo);
			
			int result = service.likeChange(changeNo);
			
    		HttpSession session = req.getSession();
    		
    		String message = null;
    		
    		if(result>0) {
    			message = "변경 성공!";
    		}else {
    			message = "변경 실패";
    		}
    		
    		session.setAttribute("message", message);
    		
    		resp.sendRedirect("manageStandard");
			
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		
			
	}

}
