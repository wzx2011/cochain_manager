package com.manage.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// event.getSession().setAttribute("sessionUser", "sessionUser");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {

	}

}
