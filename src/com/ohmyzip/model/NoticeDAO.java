package com.ohmyzip.model;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ohmyzip.dto.Notice;
import com.ohmyzip.dto.User1;

public class NoticeDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	UserDAO udao = new UserDAO();
	
	public ArrayList<Notice> noticeListAll(){
		ArrayList<Notice> notiList = new ArrayList<Notice>();
		//notice 테이블에서 모든 레코드를 검색하여 반환된 ResultSet을 notiList에 add를 한다.
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.NOTICE_SELECT_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				User1 user = new User1();
				Notice noti = new Notice();
				noti.setIdx(rs.getInt("idx"));
				noti.setTitle(rs.getString("title"));
				noti.setContent(rs.getString("content"));
				noti.setAuthor(rs.getString("author"));
				
				user = udao.myInfo(rs.getString("author"));
				noti.setName(user.getName());
				
				noti.setFile1(rs.getString("file1"));
				noti.setResdate(rs.getString("resdate"));
				noti.setReadcnt(rs.getInt("readcnt"));
				notiList.add(noti);
			}
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(rs, pstmt, con);
		return notiList;
	}
	
	public Notice getNotice(int idx){
		updateReadCount(idx);
		Notice noti = new Notice();
		User1 user = new User1();
		//idx를 매개변수로 던져서 그에 해당하는 레코드 한 건만 반환받아 noti에 저장
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.NOTICE_SELECT_ONE);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()){
				noti.setIdx(rs.getInt("idx"));
				noti.setTitle(rs.getString("title"));
				noti.setContent(rs.getString("content"));
				noti.setAuthor(rs.getString("author"));
				
				user = udao.myInfo(rs.getString("author"));
				noti.setName(user.getName());
				
				noti.setFile1(rs.getString("file1"));
				noti.setResdate(rs.getString("resdate"));
				noti.setReadcnt(rs.getInt("readcnt"));
			}
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(rs, pstmt, con);
		return noti;
	}
	
	public void updateReadCount(int idx){
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.NOTICE_READCOUNT_UPDATE);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
	}
	
	public int insertNotice(Notice noti){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.INSERT_NOTICE);
			pstmt.setString(1, noti.getTitle());
			pstmt.setString(2, noti.getContent());
			pstmt.setString(3, noti.getAuthor());
			pstmt.setString(4, "data/"+noti.getFile1());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}
	
	public Notice updateNotice(int idx){
		Notice noti = new Notice();
		User1 user = new User1();
		//idx를 매개변수로 던져서 그에 해당하는 레코드 한 건만 반환받아 noti에 저장
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.NOTICE_SELECT_ONE);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()){
				noti.setIdx(rs.getInt("idx"));
				noti.setTitle(rs.getString("title"));
				noti.setContent(rs.getString("content"));
				noti.setAuthor(rs.getString("author"));
				
				user = udao.myInfo(rs.getString("author"));
				noti.setName(user.getName());
				
				noti.setFile1(rs.getString("file1"));
				noti.setResdate(rs.getString("resdate"));
				noti.setReadcnt(rs.getInt("readcnt"));
			}
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(rs, pstmt, con);
		return noti;
	}

	public int updateNoticePro(Notice noti) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			if(noti.getFile1()==null) {
				pstmt = con.prepareStatement(Oracle11.UPDATE_NOTICE2);
				pstmt.setString(1, noti.getTitle());
				pstmt.setString(2, noti.getContent());
				pstmt.setInt(3, noti.getIdx());
			} else {
				pstmt = con.prepareStatement(Oracle11.UPDATE_NOTICE);
				pstmt.setString(1, noti.getTitle());
				pstmt.setString(2, noti.getContent());
				pstmt.setString(3, "data/"+noti.getFile1());
				pstmt.setInt(4, noti.getIdx());
			}
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}

	public int deleteNotice(int idx) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.DELETE_NOTICE);
			pstmt.setInt(1, idx);

			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}
}
