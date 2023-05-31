package com.ohmyzip.controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.ohmyzip.model.ProductDAO;
import com.ohmyzip.vo.CategoryVO;

@WebServlet("/CategoryLoading.do")
public class CategoryLoadingCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cate1 = request.getParameter("cate1");
		String msg = "새로운 분류 카테고리를 로딩합니다.";
		
		ProductDAO dao = new ProductDAO();
		ArrayList<CategoryVO> ctList = dao.getSecondCategoryList(cate1); 
		
		request.setAttribute("ctList", ctList);
		request.setAttribute("msg", msg);
		
		response.setContentType("text/html; charset=UTF-8");
		JSONObject json = new JSONObject();
		json.put("ctList", ctList);
		json.put("msg", msg);
		PrintWriter out = response.getWriter();
		out.println(json.toString());
	}

}
