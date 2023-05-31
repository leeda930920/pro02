package com.ohmyzip.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ohmyzip.model.NoticeDAO;

@WebServlet("/DelNotice.do")
public class DelNoticeCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		NoticeDAO ndao = new NoticeDAO();
		
		int cnt = ndao.deleteNotice(idx);
		if(cnt==0){ //실패하면, 공지사항 글 상세보기로 다시 이동
			String msg = "공지사항 글을 삭제하지 못했습니다.";
			request.setAttribute("msg", msg);
			response.sendRedirect("GetNotice.do?idx="+idx);
		} else { //삭제 처리가 성공하면, 공지사항 목록으로 이동
			response.sendRedirect("NoticeList.do");
		}
	}
}
