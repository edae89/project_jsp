package shop.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import shop.dao.MemberDAO;
import shop.dto.MemberVO;

public class MemberLoginAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 시도>DB에서 확인 후 통과되면 id를 세션처리하고 home띄워주기
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		MemberDAO mDAO = MemberDAO.getInstance();
		int chkFlag = mDAO.loginChk(id, pw);
		
		if(chkFlag==1) {
			HttpSession session = request.getSession();
			session.setAttribute("nowid", id);
			request.setAttribute("loginResult", chkFlag);
			new ShopHomeFormAction().execute(request, response);
		}else if(chkFlag==2) {
			request.setAttribute("loginResult", "비밀번호 불일치");
			new MemberLoginFormAction().execute(request, response);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("login_form.jsp");
//			dispatcher.forward(request, response);
		}else if(chkFlag==3) {
			request.setAttribute("loginResult", "등록되지 않은 아이디");
			new MemberLoginFormAction().execute(request, response);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("login_form.jsp");
//			dispatcher.forward(request, response);
		}
		
		
		
//		String url = "ShopServlet?command=shop_home_form";
//		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//		dispatcher.forward(request, response);
		
	}

}
