package com.ohmyzip.controller.sales;

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

import com.ohmyzip.dto.Product;
import com.ohmyzip.dto.User1;
import com.ohmyzip.model.ProductDAO;
import com.ohmyzip.model.SalesDAO;
import com.ohmyzip.model.UserDAO;
import com.ohmyzip.vo.SalesVO;

@WebServlet("/SurveyLoad.do")
public class SurveyLoadCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String o_code = request.getParameter("o_code");
		
		SalesDAO sdao = new SalesDAO();
		ProductDAO pdao = new ProductDAO();
		UserDAO udao = new UserDAO();
		
		//특정 주문정보의 전체 판매+결제 내용 로딩 
		SalesVO sale = sdao.getSales(o_code);
		
		//상품명 로딩
		Product pro = pdao.getProduct(sale.getP_code()); 
		sale.setP_name(pro.getP_name());
		
		//사용자 이름 로딩
		User1 user = new User1();
		try {
			user = udao.myInfo(sale.getId());
			sale.setUsername(user.getName());
		} catch (InvalidKeyException | NoSuchPaddingException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		} 
		
		request.setAttribute("sale", sale);	//구매 정보
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/surveyLoad.jsp");
		view.forward(request, response);
	}
}
