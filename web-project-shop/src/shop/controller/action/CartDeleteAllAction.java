package shop.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.dao.CartDAO;

public class CartDeleteAllAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("nowid");
		CartDAO cDAO = CartDAO.getInstance();
		cDAO.deleteAllFromCartById(id);
		new ShopCartFormAction().execute(request, response);
		
	}

}
