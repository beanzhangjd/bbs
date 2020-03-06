package cn.zhangjd.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cn.zhangjd.bean.User;
import cn.zhangjd.ex.NotAuthorityException;
import cn.zhangjd.mapper.UserMapper;
import cn.zhangjd.util.JwtUtil;
@WebFilter(filterName="webFiter",urlPatterns= {"/api/*"})
public class WebFiter implements Filter{
	@Autowired
	private UserMapper userMapper;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	@Override
	public void doFilter(ServletRequest request1, ServletResponse response1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)request1;
		HttpServletResponse response=(HttpServletResponse)response1;
		try{
			Cookie[] cookies= request.getCookies();
			if(cookies==null||cookies.length==0) {
				response.sendRedirect("/showLogin");
				return;
			}
			for (int i=0;i<cookies.length;i++) {
				Cookie cookie=cookies[i];
				if("Authority".equals(cookie.getName())) {
					String token=cookie.getValue();
					if (token==null||token.trim().equals("")){
						response.sendRedirect("/showLogin");
						return;
					}
					String[] strs=JwtUtil.checkToken("user", token);
					User user=userMapper.selectById(Integer.parseInt(strs[0]));
					if(user!=null&&user.getGrade()>=1) {
						chain.doFilter(request1, response1);
						return;
					}else {
						response.sendRedirect("/showLogin");
						return;
					}
				}
			}
			response.sendRedirect("/showLogin");
		}catch (RuntimeException e){
			//e.printStackTrace();
			response.sendRedirect("/showLogin");
		}
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
