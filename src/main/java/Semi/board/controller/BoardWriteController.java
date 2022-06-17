package Semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import Semi.board.model.service.BoardService;
import Semi.board.model.vo.BoardDetail;
import Semi.board.model.vo.BoardImage;
import Semi.common.MyRenamePolicy;
import Semi.member.model.vo.Member;


@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			String mode = req.getParameter("mode"); 
			
			if(mode.equals("update")) {
				
				int boardNo =  Integer.parseInt( req.getParameter("no") );
				
				BoardDetail detail = new BoardService().selectBoardDetail(boardNo);
				
				detail.setBoardContent(  detail.getBoardContent().replaceAll("<br>", "\n") );
				
				req.setAttribute("detail", detail); 
				
			}
			
			String path ="/WEB-INF/views/board/boardWriteForm.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			
			String boardTitle = req.getParameter("boardTitle");
			String boardContent = req.getParameter("boardContent");
			int boardCode = Integer.parseInt( req.getParameter("type") );
			
			HttpSession session = req.getSession();
			
			
			Member loginMember = (Member)session.getAttribute("loginMember"); 
			int memberNo = loginMember.getMemberNo();
			BoardDetail detail = new BoardDetail();
			
			detail.setBoardTitle(boardTitle);
			detail.setBoardContent(boardContent);
			detail.setMemberNo(memberNo);
			
			BoardService service = new BoardService();
			String mode = req.getParameter("mode") ; 
			
			if(mode.equals("insert")) { 
				
				int boardNo = service.insertBoard(detail, boardCode);
				
				
				String path = null;
				if(boardNo > 0) { 
					session.setAttribute("message", "게시글이 작성되었습니다.");
					path = "detail?no=" + boardNo + "&type=" + boardCode;
					
				}else { 
					session.setAttribute("message", "게시글 등록이 취소되었습니다.");
					path = "write?mode=" + mode + "&type=" + boardCode;
				}
				
				resp.sendRedirect(path); 
				
			}
			
			
			
			if(mode.equals("update")) { 

				int boardNo = Integer.parseInt( req.getParameter("no") );
				
				int cp = Integer.parseInt( req.getParameter("cp") );
				
				String deleteList = req.getParameter("deleteList"); 
				
				detail.setBoardNo(boardNo);

				int result = service.updateBoard(detail, deleteList);
				
				String path = null;
				String message = null;
				
				if(result > 0) { 
					path = "detail?no=" + boardNo + "&type=" + boardCode + "&cp=" + cp;
					message = "수정이 완료되었습니다.";
					
				}else { 
					path = req.getHeader("referer");
					message = "수정실패";
				}
				
				session.setAttribute("message", message);
				
				resp.sendRedirect(path);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	
}