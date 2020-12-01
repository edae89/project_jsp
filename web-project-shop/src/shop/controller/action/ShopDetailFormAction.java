package shop.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ShopDAO;
import shop.dto.ShopVO;

public class ShopDetailFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("goodsid");
		ShopDAO sDAO = ShopDAO.getInstance();
		sDAO.updateReadCount(id);
		
		ShopVO sVo = sDAO.selectOneGoodsById(id);
		request.setAttribute("viewgoods", sVo);
		String url = "/shop-detail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
