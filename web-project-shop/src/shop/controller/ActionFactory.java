package shop.controller;

import shop.controller.action.Action;
import shop.controller.action.CartDeleteAllAction;
import shop.controller.action.CartDeleteOneAction;
import shop.controller.action.MemberJoinAction;
import shop.controller.action.MemberJoinIdchkAction;
import shop.controller.action.MemberLoginAction;
import shop.controller.action.MemberLoginFormAction;
import shop.controller.action.MemberLogoutAction;
import shop.controller.action.ShopAddCartAction;
import shop.controller.action.ShopCartFormAction;
import shop.controller.action.ShopDetailFormAction;
import shop.controller.action.ShopHomeFormAction;
import shop.controller.action.ShopCategoryFormAction;
import shop.controller.action.ShopColumnFormAction;
import shop.controller.action.ShopShopFormAction;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();
	
	ActionFactory(){
		super();
	}
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if (command.equals("shop_home_form")) {
			action = new ShopHomeFormAction();
		}else if (command.equals("shop_shop_form")) {
			action = new ShopShopFormAction();
		}else if (command.equals("shop_category_form")) {
			action = new ShopCategoryFormAction();
		}else if (command.equals("shop_column_form")) {
			action = new ShopColumnFormAction();
		}else if (command.equals("shop_detail_form")) {
			action = new ShopDetailFormAction();
		}else if (command.equals("shop_cart_form")) {
			action = new ShopCartFormAction();
		}else if (command.equals("shop_add_cart")) {
			action = new ShopAddCartAction();	
		}else if (command.equals("member_login_form")) {
			action = new MemberLoginFormAction();
		}else if (command.equals("member_login")) {
			action = new MemberLoginAction();
		}else if (command.equals("member_logout")) {
			action = new MemberLogoutAction();
		}else if (command.equals("member_join_idchk")) {
			action = new MemberJoinIdchkAction();
		}else if (command.equals("member_join")) {
			action = new MemberJoinAction();
		}else if (command.equals("cart_delete_one")) {
			action = new CartDeleteOneAction();
		}else if (command.equals("cart_delete_all")) {
			action = new CartDeleteAllAction();
		}
		
		return action;
	}
	
}
