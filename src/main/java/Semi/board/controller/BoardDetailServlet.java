package Semi.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Semi.board.model.service.BoardService;
import Semi.board.model.vo.Board;


@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardNo = Integer.parseInt(req.getParameter("no"));
		try {
			BoardService service = new BoardService();
			Board boardDetail = service.boardDetail(boardNo);
			req.setAttribute("boardDetail", boardDetail);
			req.getRequestDispatcher("/WEB-INF/views/board/boardDetail.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
