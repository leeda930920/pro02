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

@WebServlet("/AdminResetUser.do")
public class AdminResetUserCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		UserDAO dao = new UserDAO();
		User1 user = new User1();
		
		user = dao.getTel(id);
		int ln = user.getTel().length();
		String pw2 = user.getTel().substring(ln-4);
		String key = "%03x";
		String passwd = "";
		try {
			passwd = AES256.encryptAES256(pw2, key);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| InvalidKeySpecException | NoSuchPaddingException
				| InvalidParameterSpecException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		
		int cnt = dao.resetPassword(id, passwd);
		
		if(cnt>=1){
			response.sendRedirect("MemberList.do");
		} else {
			response.sendRedirect("MemberList.do");
		}
	}
}
