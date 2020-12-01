package shop.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ShopDAO;
import shop.dto.ShopVO;

public class ShopCategoryFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// table에서 type으로 select 하여 list 불러오기
		String type = (String)request.getParameter("type");
		System.out.println(type);
		ShopDAO sDAO = ShopDAO.getInstance();
		List<ShopVO> goodslist = sDAO.selectGoodsRecentByType(type);
		request.setAttribute("goodsList", goodslist);
		request.setAttribute("type", type);
		String url = "/shop.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
