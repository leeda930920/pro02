package com.ohmyzip.controller.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ohmyzip.dto.Buy;
import com.ohmyzip.model.SalesDAO;

@WebServlet("/SurveyPro.do")
public class SurveyProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String o_code = request.getParameter("o_code");
		Buy buy = new Buy();
		buy.setO_code(request.getParameter("o_code"));
		buy.setD_name(request.getParameter("d_name"));
		buy.setD_code(request.getParameter("d_code"));
		buy.setD_state(request.getParameter("d_state"));
		
		SalesDAO dao = new SalesDAO();
		int cnt = dao.surveyUpdate(buy);
		if(cnt>0){
			response.sendRedirect("Survey.do");
		} else {
			response.sendRedirect("SurveyLoad.do?o_code="+o_code);
		}
	}
}
