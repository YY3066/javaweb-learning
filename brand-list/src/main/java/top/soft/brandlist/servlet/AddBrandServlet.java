package top.soft.brandlist.servlet;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.brandlist.entity.Brand;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addBrand")
public class AddBrandServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader br = req.getReader();
        while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
        }
        List<Brand> brands = new ArrayList<>();
        brands.addAll((List<Brand>)req.getServletContext().getAttribute("brands"));

        Brand brand = JSON.parseObject(stringBuilder.toString(), Brand.class);
        brands.add(brand);
        req.getServletContext().setAttribute("brands",brands);

    }
}
