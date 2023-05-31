package com.ohmyzip.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ohmyzip.model.ProductDAO;
import com.ohmyzip.vo.CategoryVO;

@WebServlet("/InsertProduct.do")
public class InsertProductCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "새로운 상품을 등록합니다.";
		
		ProductDAO dao = new ProductDAO();
		ArrayList<CategoryVO> cateList = dao.getFirstCategoryList(); 
		
		request.setAttribute("cateList", cateList);
		request.setAttribute("msg", msg);
		
		//디스패치로 view를 생성하여 noticeList.jsp로 요청 받은 notiList를 포워드
		RequestDispatcher view = request.getRequestDispatcher("/product/insertProduct.jsp");
		view.forward(request, response);
	}

}
