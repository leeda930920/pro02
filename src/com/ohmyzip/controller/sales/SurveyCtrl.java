package com.ohmyzip.controller.sales;

import java.io.IOException;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

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

@WebServlet("/Survey.do")
public class SurveyCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//배송관리
		//판매 정보 로딩
		SalesDAO sdao = new SalesDAO();
		ProductDAO pdao = new ProductDAO();
		UserDAO udao = new UserDAO();
		ArrayList<SalesVO> sList = sdao.getSalesList();
		for(SalesVO sale : sList){ //상품명 넣기
			Product pro = pdao.getProduct(sale.getP_code()); 
			sale.setP_name(pro.getP_name());
		}
		for(SalesVO sale : sList){	//사용자명 넣기
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
		}
		
		request.setAttribute("sList", sList);	//구매 정보 목록
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/surveyList.jsp");
		view.forward(request, response);
	}
}
