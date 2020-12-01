package shop.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.MemberDAO;
import shop.dto.MemberVO;

public class MemberJoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDAO mDAO = MemberDAO.getInstance();
		MemberVO mVo = new MemberVO();
		mVo.setId(request.getParameter("id")); 
		mVo.setPw(request.getParameter("password"));
		mVo.setName(request.getParameter("name"));
		mVo.setAddress(request.getParameter("address"));
		mVo.setTel(request.getParameter("tel"));
		mDAO.insertUser(mVo);
		
		new MemberLoginFormAction().execute(request, response);
	}

}
