package shop.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.dao.CartDAO;
import shop.dto.CartVO;

public class ShopAddCartAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("nowid");
		String goodsId = request.getParameter("goodsid");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		CartDAO cDAO = CartDAO.getInstance();
		CartVO cVo = new CartVO();
		cVo.setUserId(userId);
		cVo.setGoodsId(goodsId);
		cVo.setQuantity(quantity);
		cDAO.insertToCart(cVo);
		
		new ShopCartFormAction().execute(request, response);
		
		
	}

}
