package com.manage.util;

import org.springframework.core.env.Environment;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SessionFilter extends OncePerRequestFilter {
	
	//过滤器排除项
	public static final Map<String, Boolean> CHECK_ESCAPE_SESSION_MAP = new HashMap<String, Boolean>() {
		public static final long serialVersionUID = 3619197939981232851L;
	    {
	        put("COWITP", false);
	        put("goLogin", false);
	        put("doLogin", false);
	        put("doRegister", false);
	        put("goDemoPage", false);
	        put("doRetrospectDemo", false);
	        put("goSendGetPage", false);
	        put("doSendGetDemo", false);
	        put("goUpChainPage", false);
			put("getRoleInfoList", false);
			put("getRoleTypeId", false);
	        put("/", false);
	    }
	};

	@Override
	public void setEnvironment(Environment environment) {
		super.setEnvironment(environment);
	}

	@Override
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 请求的url
		String requestURI = request.getRequestURI();

		// 请求的url
		String[] urls = requestURI.split("/");
		String url = requestURI;

		// 是否过滤
		boolean doFilter = false;

		int urlsLen = urls.length;
		if (urls != null && urlsLen > 0) {
			String checkSession = urls[urlsLen - 1];
			// 判断请示是否需要检查登录
			Boolean isEscapUrl = CHECK_ESCAPE_SESSION_MAP
					.get(checkSession);
			if (isEscapUrl != null) {
				doFilter = isEscapUrl;
			} else {
				doFilter = true;
			}
		}

		// css不进行过滤
		if (url.indexOf(".") != -1) {
			doFilter = false;
		}

		// 接口不进行过滤
		if (url.indexOf(Const.interface_prefix) != -1) {
			doFilter = false;
		}
		
		if (doFilter) {
			// 需要验证
			Object loginSession = request.getSession().getAttribute(
					"sessionUser");
			if (loginSession != null) {
				// 已登录，继续执行
				filterChain.doFilter(request, response);
				return;
			} else {
				response.setStatus(403);
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setHeader("content-type", "text/html;charset=UTF-8");
				PrintWriter pw = response.getWriter();

				StringBuffer sb = new StringBuffer();

				sb.append("<title>抱歉，出错了</title>");
				sb.append("<meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\">");
				sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://res.wx.qq.com/connect/zh_CN/htmledition/style/wap_err1a9853.css\">");
				sb.append("<div class=\"page_msg\">");
				sb.append("<div class=\"inner\">");
				sb.append("<span class=\"msg_icon_wrp\"><i class=\"icon80_smile\"></i></span><div class=\"msg_content\"><a href=\""
						+ request.getContextPath()
						+ "/goLogin\" style=\"font-size:16px;text-decoration:none;\">网页过期，请重新登录！</a></div>");
				sb.append("</div>");
				sb.append("</div>");

				pw.print(sb.toString());
				return;
			}
		}

		// 如果不执行过滤，则继续
		filterChain.doFilter(request, response);
	}
}
