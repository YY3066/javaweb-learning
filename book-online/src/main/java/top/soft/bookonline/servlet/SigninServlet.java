package top.soft.bookonline.servlet;

import top.soft.bookonline.entity.User;
import top.soft.bookonline.service.UserService;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.bookonline.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取表单数据
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        //调用注册功能
        User user = userService.signUp(account, password);
        //将用户对象写入数据库
        if (user != null) {
            //注册成功，跳转登录界面
            //重定向回到/login-page，进入LoginServlet
            resp.sendRedirect("/login-page");
        } else {
            //注册失败，设置好响应对象字符集和响应类型
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("<script>alert('注册失败！');location.href='/';</script>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
