package shop.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.MemberDAO;

public class MemberJoinIdchkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String joinID = request.getParameter("id");
		MemberDAO mVo = MemberDAO.getInstance();
		int chkFlag = mVo.joinIdChk(joinID);
		
		request.setAttribute("id", joinID);
		request.setAttribute("chkFlag", chkFlag);
		RequestDispatcher dispatcher = request.getRequestDispatcher("idchk_form.jsp");
		dispatcher.forward(request, response);
	}

}
