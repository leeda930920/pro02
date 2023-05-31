package com.ohmyzip.view;

import java.io.IOException;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ohmyzip.dto.User1;
import com.ohmyzip.model.UserDAO;

@WebServlet("/MyPage.do")
public class MyPageCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		String id = (String) ses.getAttribute("sid");
		
		UserDAO dao = new UserDAO();
		User1 user = new User1();
		String msg = "";
		try {
			user = dao.myInfo(id);
		} catch (InvalidKeyException | NoSuchPaddingException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		if(user==null){
			ses.invalidate();
			msg = "현재 로그인이 되어 있지 않습니다. 로그인 하시기 바랍니다.";
			response.sendRedirect("UserLogin.do?msg="+msg);	
		} else {
			msg = "현재 본인 정보를 로딩하였습니다.";
			request.setAttribute("user", user);
			request.setAttribute("msg", msg);

			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/user/myPage.jsp");
			view.forward(request, response);
		}
	}
}
