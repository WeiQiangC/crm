package com.chen.crm.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"*.do","*.jsp"})
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse	resp = (HttpServletResponse) response;
		
		String path = req.getServletPath();
		//�����������������˵�
		if("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)) {
			chain.doFilter(request, response);
		}else {
			HttpSession session = req.getSession();
			if(session.getAttribute("user") != null) {
				chain.doFilter(request, response);
			}else {
				
				/*
				 * ��ʹ�õ��Ǿ���·��
				 * ת��ʹ�õ���һ������ľ���·����ʹ�÷�ʽ����ǰ�治��/��Ŀ��������·������֮Ϊ�ڲ�·��
				 *  /login.jsp
				 *  
				 *  �ض���ʹ�õ��Ǵ�ͳ�ľ���·����д����ǰ�����Ҫ��/��Ŀ����ͷ��������������Դ·��
				 *   /maven-crm/login.jsp
				 *   
				 *   ʹ���ض������ʹ��ת����ԭ��
				 *   ʹ��ת����ַ����·������ı䣬ֻ��ͣ���ڵ�ǰҳ���·��
				 *   ��ʹ���ض������ַ����·���ᷢ���ı�
				 */
				//ʹ��req.getContextPath���ж�̬�Ļ�ȡ��Ŀ��
				resp.sendRedirect(req.getContextPath()+"/login.jsp");
			}
		}
	}

}
