package com.ohmyzip.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ohmyzip.dto.Notice;
import com.ohmyzip.model.NoticeDAO;

@WebServlet("/NoticeList.do")
public class GetNoticeListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO ndao = new NoticeDAO();
		ArrayList<Notice> notiList = new ArrayList<Notice>();
		notiList = ndao.noticeListAll();
		request.setAttribute("notiList", notiList);
		
		//디스패치로 view를 생성하여 noticeList.jsp로 요청 받은 notiList를 포워드
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/notice/notiList.jsp");
		view.forward(request, response);
	}
}
