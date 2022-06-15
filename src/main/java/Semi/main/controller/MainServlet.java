package Semi.main.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Semi.board.model.service.BoardService;
import Semi.board.model.vo.ShowWindowInfo;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			BoardService service = new BoardService();
			String boardType = null;
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i=0;i<6;i++) {
				//i는 게시판 갯수
				switch(i) {
					case 0 : boardType = "hot";
						break;
					case 1 : boardType = "recency";
						break;
					case 2 : boardType = "exercise";
						break;
					case 3 : boardType = "free";
						break;
					case 4 : boardType = "announcement";
						break;
					default : boardType = "met";
				}
				List<ShowWindowInfo> sList = service.mainBoardSelect(boardType);
				map.put(boardType, sList);
			}
			req.setAttribute("map", map);
		} catch(Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
		
		// 서비스 호출
		// req.setAttribute()
	
		// main.jsp 포워드
	}
}
