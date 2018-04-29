package com.cschool.shop.managment.server.servlet.service;

import javax.servlet.ServletContext;

import com.cschool.shop.managment.client.service.LoginServiceRPC;
import com.cschool.shop.managment.server.listener.ApplicationStartupListener;
import com.cschool.shop.managment.server.service.UserService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServletService extends RemoteServiceServlet implements LoginServiceRPC {

	@Override
	public boolean login(String login, char[] password) {
//	DLA PRZYSPIESZENIA TESTOWANIA LOGOWANIE WYLACZONE
//		return getUserService().findByLoginAndPassword(login, password) != null;
		return true;
	}

	
	private UserService getUserService() {
		return (UserService) getServletContext().getAttribute(ApplicationStartupListener.USER_SERVICE_ATTRIBUTE);
	}
}
