package shop.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ShopDAO;
import shop.dto.ShopVO;

public class ShopShopFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShopDAO sDAO = ShopDAO.getInstance();
		List<ShopVO> goodsList = sDAO.selectAllGoodsRecent();
		request.setAttribute("goodsList", goodsList);
		String url = "/shop.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
