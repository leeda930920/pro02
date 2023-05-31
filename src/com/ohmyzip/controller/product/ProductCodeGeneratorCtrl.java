package com.ohmyzip.controller.product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ohmyzip.model.ProductDAO;
import org.json.JSONObject;

@WebServlet("/ProductCodeGenerator.do")
public class ProductCodeGeneratorCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cate = request.getParameter("cate");
		String msg = "새로운 상품코드를 발생하였습니다.";
		
		ProductDAO dao = new ProductDAO();
		String p_code = dao.getProductCodeGenerator(cate);
		
		request.setAttribute("p_code", cate+p_code);
		request.setAttribute("msg", msg);
		
		response.setContentType("text/html; charset=UTF-8");
		JSONObject json = new JSONObject();
		json.put("p_code", cate+p_code);
		json.put("msg", msg);
		PrintWriter out = response.getWriter();
		out.println(json.toString());	
	}

}
