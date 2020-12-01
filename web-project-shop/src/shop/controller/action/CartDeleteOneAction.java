package shop.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.CartDAO;

public class CartDeleteOneAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int delnum = Integer.parseInt(request.getParameter("num"));
		CartDAO cDAO = CartDAO.getInstance();
		cDAO.deleteOneFromCartByNum(delnum);
		new ShopCartFormAction().execute(request, response);
		
	}
	
}
