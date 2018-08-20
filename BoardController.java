package board.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

@Controller
public class BoardController {
	@RequestMapping(value="/board/boardList.do")
	public ModelAndView boardList(HttpServletRequest request, BoardDAO boardDAO, ModelAndView modelAndView) {
		System.out.println("글 목록 처리");
		// 1. 사용자 입력 정보 추출
		int pg = Integer.parseInt(request.getParameter("pg"));
		// 2. DB 연동 처리	
		//DB - 1페이지당 5개씩
		int endNum = pg * 5;		// 5, 10, 15....
		int startNum = endNum - 4;	// 1, 6,  11....
		//BoardDAO boardDAO = new BoardDAO();
		ArrayList<BoardDTO> list = boardDAO.boardList(startNum, endNum);
		// 페이징 처리
		int totalA = boardDAO.getTotalA();	// 총글 수
		int totalP = (totalA+4)/5;			// 총페이지 수
		// 페이징 3개씩 보여주기
		int startPage = (pg-1)/3*3+1;	// 1, 4, 7...
		int endPage = startPage + 2;	// 3, 6, 9...
		if(endPage > totalP) endPage = totalP;	
		// 3. 검색 결과를 세션에 저장하고 목록 화면으로 이동한다.
//		request.setAttribute("list", list);
//		request.setAttribute("startPage", startPage);
//		request.setAttribute("endPage", endPage);
//		request.setAttribute("totalP", totalP);
//		request.setAttribute("pg", pg);
//		return "boardList";
		//ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", list);
		modelAndView.addObject("startPage", startPage);
		modelAndView.addObject("endPage", endPage);
		modelAndView.addObject("totalP", totalP);
		modelAndView.addObject("pg", pg);
		modelAndView.setViewName("boardList.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/board/boardView.do")
	public ModelAndView boardView(HttpServletRequest request) {
		System.out.println("글 상세 조회 처리");
		//본다음에 봣던 페이지로 돌아가야하기때문에 seq + page를 넘겨 받음
	    //1.사용자 입력 정보 추출
	    int seq = Integer.parseInt(request.getParameter("seq"));
	    int pg = Integer.parseInt(request.getParameter("pg"));
	    //2. DB 연동처리
	    BoardDAO boardDAO = new BoardDAO();
	    //조회수 증가
	    boardDAO.updateHit(seq);
	    //상세보기
	    BoardDTO boardDTO = boardDAO.boardView(seq);
	    //3. 검색 결과를 request에 저장하고 상세 화면으로 이동한다.
//		request.setAttribute("boardDTO", boardDTO);
//		request.setAttribute("seq", seq);
//		request.setAttribute("pg", pg);
//		return "boardView";
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("boardDTO", boardDTO);
	    modelAndView.addObject("seq", seq);
	    modelAndView.addObject("pg", pg);
	    modelAndView.setViewName("boardView.jsp");
	    return modelAndView;
	}
	
	@RequestMapping(value="/board/boardWriteForm.do")
	public ModelAndView boardWriteForm(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("글 입력 폼 처리");
		//return "boardWriteForm";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("boardWriteForm.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/board/boardWrite.do")
	public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 등록 처리");
		//1.사용자 입력 정보 추출
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		HttpSession session = request.getSession();
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String id = (String)session.getAttribute("memId");
		String name = (String)session.getAttribute("memName");
		//DB 연동처리
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setName(name);
		boardDTO.setId(id);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		
		BoardDAO boardDAO = new BoardDAO();
		int su = boardDAO.boardWrite(boardDTO);
		//3.화면 네비게이션
//		request.setAttribute("su", su);
//		return "boardWrite"; // ./boardWrite.jsp => ViewResolver를 통해서 완성
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("su", su);
		modelAndView.setViewName("boardWrite.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/board/boardDelete.do")
	public ModelAndView boardDelete(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 삭제 처리");
		//1.사용자 입력 정보 추출
		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg =1;
		if(request.getParameter("pg") != null){
			pg = Integer.parseInt(request.getParameter("pg"));
		}
		//DB연동처리
		BoardDAO boardDAO = new BoardDAO();
		int su = boardDAO.boardDelete(seq);
		//3.화면 네비게이션
//		request.setAttribute("su", su);
//		request.setAttribute("pg", pg);
//		return "boardDelete";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("su", su);
		modelAndView.addObject("pg", pg);
		modelAndView.setViewName("boardDelete.jsp");
		return modelAndView;
	}
}
















