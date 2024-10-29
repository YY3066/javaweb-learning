package com.example.class05ajaxaxios;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ajaxServlet")
public class AjaxServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("Hello Ajax");
    }
}
