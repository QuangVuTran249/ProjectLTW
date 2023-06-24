package main.controller;

import main.bean.Products;
import main.bean.TypeProducts;
import main.services.ProductsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletSearchControl", value = "/SearchControl")
public class ServletSearchControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String txt = (String) request.getParameter("txt");
        String page = request.getParameter("page");
        int page2 ;
        int left;
        int right;
        if(page == null ){
            page2 = 0;
            left = 0;
            right = 1;
        } else {
            page2 = Integer.parseInt(page)*12;
            left = Integer.parseInt(page) - 1 ;
            right = Integer.parseInt(page) +1;
        }
        int pagee = ProductsService.getInstance().getPageAllPro();
        ArrayList<Products> products = (ArrayList<Products>) ProductsService.getInstance().getAllproductSearch(txt ,page2);
        ArrayList<TypeProducts> typeProD = (ArrayList<TypeProducts>) ProductsService.getInstance().getTypeProduct();
        ArrayList<Products> producBySale = (ArrayList<Products>) ProductsService.getInstance().getAllproductBySale();


        request.setAttribute("allproducts", products);
        request.setAttribute("alltype", typeProD);
        request.setAttribute("productBySale", producBySale);
        request.setAttribute("page", pagee);
        request.setAttribute("left", left);
        request.setAttribute("right", right);
        request.getRequestDispatcher("/shop-grid.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
