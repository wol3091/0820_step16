package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import member.dao.MemberDAO;

@Controller
public class LoginController{

	@RequestMapping(value="/member/login.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인처리");
		//1. 사용자 입력 정보추출
	 	String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
	 	//DB연동처리
		MemberDAO memberDAO = new MemberDAO();
	 	String name = memberDAO.login(id, pwd);
	 	//3.화면 네이게이션
	 	ModelAndView modelAndView = new ModelAndView();
	 	if(name == null){//로그인 실패
	 		//response.sendRedirect("loginForm.jsp"); 이방식으로 가도 되고
	 		//같은 폴더이기 때문에 포워드로 한다?!	 		
	 		//return "loginForm";
	 		modelAndView.setViewName("loginForm.jsp");
	 	}else{
	 		HttpSession session = request.getSession();
	 		session.setAttribute("memId", id);
	 		session.setAttribute("memName", name);
	 		//forward 방식으로 보내면 폴더명이 안바뀜 같은폴더내가 아니기 때문에	 		
	 		//return "../board/boardList.do?pg=1";
	 		modelAndView.setViewName("redirect:../board/boardList.do?pg=1");
	 	}
	 	return modelAndView;
	}
}











