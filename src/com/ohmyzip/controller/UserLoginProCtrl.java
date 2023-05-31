package com.ohmyzip.controller;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
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
import javax.servlet.http.HttpSession;
import com.ohmyzip.model.UserDAO;
@WebServlet("/UserLoginPro.do")
public class UserLoginProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
	
		UserDAO dao = new UserDAO();
		int cnt = 0;

		try {
			cnt = dao.loginCheck(id, pw);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| InvalidKeySpecException | NoSuchPaddingException
				| InvalidParameterSpecException | BadPaddingException
				| IllegalBlockSizeException
				| InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		HttpSession ses = request.getSession();
		String msg = "";
		if(cnt==1){
			msg = "로그인 성공";
			ses.setAttribute("sid", id);
			response.sendRedirect(request.getContextPath());
		} else if(cnt==9){
			msg = "아이디 또는 비밀번호가 틀립니다.";
			response.sendRedirect("UserLogin.do?msg="+msg);	
		} else {
			msg = "존재하지 않는 아이디입니다.";
			response.sendRedirect("UserLogin.do?msg="+msg);
		}
	}
}

