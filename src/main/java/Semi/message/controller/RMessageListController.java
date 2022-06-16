package Semi.message.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Semi.member.model.vo.Member;
import Semi.message.model.service.MessageService;
import Semi.message.model.vo.Message;

@WebServlet("/messageList")
public class RMessageListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String type = req.getParameter("t"); // r/s
			HttpSession session = req.getSession(); // 세션 얻어오기

			Member loginMember = (Member)session.getAttribute("loginMember");

			System.out.println(type);
			
			MessageService service = new MessageService();
			
			// 받은쪽지 조회 서비스
			int myNo = loginMember.getMemberNo();
			
			List<Message> mList = service.MessageList(myNo, type);
			
			System.out.println("쪽지 내용 확인 : " + mList.get(0).getMessageContent());
			System.out.println(mList.size());
			new Gson().toJson(mList, resp.getWriter());
			req.setAttribute("mList", mList);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
