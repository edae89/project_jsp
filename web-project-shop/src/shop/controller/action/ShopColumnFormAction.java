package shop.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ShopDAO;
import shop.dto.ShopVO;

public class ShopColumnFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = (String)request.getParameter("type");
		String column = (String)request.getParameter("column");
		System.out.println(type);
		System.out.println(column);
		ShopDAO sDAO = ShopDAO.getInstance();
		
		if(type==null) {
			if(column.equals("recent")) {
				List<ShopVO> goodsList = sDAO.selectAllGoodsRecent();
				request.setAttribute("goodsList", goodsList);
			}else if(column.equals("bestclicked")) {
				List<ShopVO> goodsList = sDAO.selectAllGoodsBestClicked();
				request.setAttribute("goodsList", goodsList);
			}
		}else {
			if(column.equals("recent")) {
				List<ShopVO> goodsList = sDAO.selectGoodsRecentByType(type);
				request.setAttribute("goodsList", goodsList);
			}else if(column.equals("bestclicked")){
				List<ShopVO> goodsList = sDAO.selectGoodsBestClickedByType(type);
				request.setAttribute("goodsList", goodsList);
			}
		}
		String url = "/shop.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
