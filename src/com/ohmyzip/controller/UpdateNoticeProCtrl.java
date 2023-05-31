package com.ohmyzip.controller;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ohmyzip.dto.Notice;
import com.ohmyzip.model.NoticeDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/UpdateNoticePro.do")
public class UpdateNoticeProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String savePath = "/data";	//업로드할 디렉토리
		int uploadFileSizeLimit = 10 * 1024 * 1024;	//업로드할 파일 크기 제한
		String encType = "UTF-8";		//멀티파트 데이터의 인코딩 설정
		ServletContext context = getServletContext();	//현재 서블릿(프로젝트)의 위치 저장
		String uploadFilePath = context.getRealPath(savePath);  //서버 상에 실제 업로드되는 디렉토리 지정
		System.out.println("지정된 업로드 디렉토리 : "+savePath);
		System.out.println("서버 상의 실제 업로드되는 디렉토리 : "+uploadFilePath);
		
		String title = "";
		String content = "";
		String author = "";
		String fileName = "";
		int idx = 0;
		//MultipartRequest의 옵션 내용
		//1. request : 요청 받은 객체
		//2. uploadFilePath : 서버상의 실제 디렉토리
		//3. uploadFileSizeLimit : 최대 업로드 파일 크기
		//4. encType : 인코딩 방법
		//5. new DefaultFileRenamePolicy() : 동일한 이름이 존재하면 새로운 이름이 부여되며, 생략하면, 덮어쓰기 됨
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, 
					uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
			fileName = multi.getFilesystemName("file1"); // 업로드하고, 업로드된 파일의 이름 얻기
			if (fileName == null) { // 파일이 업로드 되지 않았을때
				System.out.print("파일 업로드 실패~!");
			}  // 파일이 업로드 되었을때
			idx = Integer.parseInt(multi.getParameter("idx"));
			author = multi.getParameter("author");
			title = multi.getParameter("title");
			content = multi.getParameter("content");
		} catch (Exception e) {
			System.out.print("예외 발생 : " + e);
		}
		
		NoticeDAO ndao = new NoticeDAO();
		Notice noti = new Notice();
		noti.setIdx(idx);
		noti.setTitle(title);
		noti.setContent(content);
		noti.setFile1(fileName);
		noti.setAuthor(author);
		int cnt = ndao.updateNoticePro(noti);	
		if(cnt==0){ //글 수정 실패
			String msg = "공지사항 글을 수정하지 못했습니다.";
			request.setAttribute("msg", msg);
			response.sendRedirect("UpdateNotice.do?idx="+noti.getIdx());
		} else { //수정이 성공하면 글 목록으로 이동
			response.sendRedirect("NoticeList.do");
		}
	}
}