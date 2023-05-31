package com.ohmyzip.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserLogin.do")
public class UserLoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String msg = "아이디와 비밀번호를 입력하여 로그인하시기 바랍니다.";
		request.setAttribute("msg", msg);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/user/login.jsp");
		view.forward(request, response);
	}
}


