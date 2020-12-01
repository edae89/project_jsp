package shop.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.dao.CartDAO;
import shop.dto.CartViewVO;

public class ShopCartFormAction implements Action{

	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션에서 id가져오기
		//select로 장바구니 목록 불러오기
		HttpSession session = request.getSession();
		String nowid = (String)session.getAttribute("nowid");
		CartDAO cDAO = CartDAO.getInstance();
		
		List<CartViewVO> cartlist = cDAO.selectAllById(nowid);
		request.setAttribute("cartList", cartlist);
		String url = "/cart.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
	
}
