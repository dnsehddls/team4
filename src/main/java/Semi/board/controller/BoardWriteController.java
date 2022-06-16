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


// 컨트롤러 : 요청에 따라 알맞은 Service를 호출하고 결과에 따라 응답을 제어
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
				
				req.setAttribute("detail", detail); // jsp에서 사용할 수 있도록 req에 값 세팅
				
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
			
			int maxSize = 1024 * 1024 * 100; 
			
			HttpSession session = req.getSession(); 
			String root = session.getServletContext().getRealPath("/"); 
			String folderPath = "/resources/images/board/"; // 파일 저장 폴더 경로
			String filePath = root + folderPath;
			
			String encoding = "UTF-8"; // 파라미터 중 파일 제외 파라미터(문자열)의 인코딩 지정
			
			MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());                
			
		
			Enumeration<String> files = mpReq.getFileNames(); 
			
			
			// * 업로드된 이미지의 정보를 모아둘 List 생성
			List<BoardImage> imageList = new ArrayList<BoardImage>();
			
			
			while(files.hasMoreElements()) { 
				String name = files.nextElement(); 
				
				String rename = mpReq.getFilesystemName(name);   // 변경된 파일명
				String original = mpReq.getOriginalFileName(name); // 원본 파일명
				

				
				if(rename != null) { 
					BoardImage image = new BoardImage();
					
					image.setImageOriginal(original); 
					image.setImageReName(folderPath + rename); 
					image.setImageLevel( Integer.parseInt(name) ); 
				
					imageList.add(image); 
				
				} 
				
			} 
			
			String boardTitle = mpReq.getParameter("boardTitle");
			String boardContent = mpReq.getParameter("boardContent");
			int boardCode = Integer.parseInt( mpReq.getParameter("type") );
			
			
			Member loginMember = (Member)session.getAttribute("loginMember"); 
			int memberNo = loginMember.getMemberNo(); // 회원 번호
			BoardDetail detail = new BoardDetail();
			
			detail.setBoardTitle(boardTitle);
			detail.setBoardContent(boardContent);
			detail.setMemberNo(memberNo);
			
			BoardService service = new BoardService();
			String mode = mpReq.getParameter("mode") ; 
			
			if(mode.equals("insert")) { 
				
				int boardNo = service.insertBoard(detail, imageList, boardCode);
				
				
				String path = null;
				if(boardNo > 0) { 
					session.setAttribute("message", "게시글이 등록되었습니다.");
					path = "detail?no=" + boardNo + "&type=" + boardCode;
					
				}else { 
					session.setAttribute("message", "게시글 등록 실패");
					path = "write?mode=" + mode + "&type=" + boardCode;
				}
				
				resp.sendRedirect(path); 
				
			}
			
			
			
			if(mode.equals("update")) { 

				int boardNo = Integer.parseInt( mpReq.getParameter("no") );
				
				int cp = Integer.parseInt( mpReq.getParameter("cp") );
				
				String deleteList = mpReq.getParameter("deleteList"); 
				
				detail.setBoardNo(boardNo);

				int result = service.updateBoard(detail, imageList, deleteList);
				
				String path = null;
				String message = null;
				
				if(result > 0) { 
					path = "detail?no=" + boardNo + "&type=" + boardCode + "&cp=" + cp;
					message = "게시글이 수정되었습니다.";
					
				}else { 
					path = req.getHeader("referer");
					message = "게시글 수정 실패";
				}
				
				session.setAttribute("message", message);
				
				resp.sendRedirect(path);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	
}