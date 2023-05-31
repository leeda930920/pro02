package com.ohmyzip.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crypto.util.AES256;
import com.ohmyzip.dto.User1;
import com.ohmyzip.model.UserDAO;

@WebServlet("/UserJoinPro.do")
public class UserJoinProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		User1 user = new User1();
		String key = "%03x";
		
		String pw = request.getParameter("pw");
		String passwd = "";
		try {
			passwd = AES256.encryptAES256(pw, key);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| InvalidKeySpecException | NoSuchPaddingException
				| InvalidParameterSpecException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		
		user.setId(request.getParameter("id"));
		user.setPw(passwd);
		user.setName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));
		user.setTel(request.getParameter("tel"));
		user.setAddr(request.getParameter("address1")+" "+request.getParameter("address2"));
		
		UserDAO dao = new UserDAO();
		int cnt = dao.insertUser(user);
		if(cnt>=1){
			response.sendRedirect("UserLogin.do");
		} else {
			response.sendRedirect("UserSignUp.do");
		}
	}
}
