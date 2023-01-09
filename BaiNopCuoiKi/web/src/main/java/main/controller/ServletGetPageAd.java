package main.controller;

import main.bean.Products;
import main.bean.TypeProducts;
import main.services.ProductsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "getPageAD", value = "/getPageAD")
public class ServletGetPageAd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        int page2  ;
        if(page == null ){
            page2 = 1;
        } else {
            page2 = Integer.parseInt(page);
        }
        ArrayList<TypeProducts> typePro = (ArrayList<TypeProducts>) ProductsService.getInstance().getTypeProduct();
        ArrayList<Products> producBySale = (ArrayList<Products>) ProductsService.getInstance().getAllproductBySale();

        ArrayList<Products> products = (ArrayList<Products>) ProductsService.getInstance().getAllproducts();
        ArrayList<Products> proPage = (ArrayList<Products>) ProductsService.getInstance().getAllProductByPage(page2);

        request.setAttribute("allproducts2", products);
        request.setAttribute("allproducts", proPage);
        request.setAttribute("alltype", typePro);
        request.setAttribute("page",page2);
        request.setAttribute("productBySale", producBySale);



        request.getRequestDispatcher("SanPham.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}