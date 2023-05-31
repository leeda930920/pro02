package com.ohmyzip.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserTerms.do")
public class UserTermsCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "회원 약관 페이지를 로딩합니다.";
		request.setAttribute("msg", msg);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/user/terms.jsp");
		view.forward(request, response);
	}
}

