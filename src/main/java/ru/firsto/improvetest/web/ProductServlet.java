package ru.firsto.improvetest.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.firsto.improvetest.LoggerWrapper;
import ru.firsto.improvetest.model.Catalog;
import ru.firsto.improvetest.model.Product;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * User: razor
 * Date: 24.12.2015
 */
public class ProductServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(ProductServlet.class);

    private ConfigurableApplicationContext springContext;
    private ProductRestController productController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        productController = springContext.getBean(ProductRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            String id = request.getParameter("id");
            Product product = new Product(id.isEmpty() ? null : Integer.valueOf(id),
                    request.getParameter("name"),
                    new Catalog(Integer.valueOf(request.getParameter("catId")), ""),
                    priceParam(request.getParameter("price"))
            );
            if (product.isNew()) {
                LOG.info("Create {}", product);
                productController.create(product);
            } else {
                LOG.info("Update {}", product);
                productController.update(product);
            }
            response.sendRedirect("products");
        } else if (action.equals("filter")) {
            String catName = resetParam("catName", request);
            String name = resetParam("name", request);
            BigDecimal priceFrom = priceParam(resetParam("priceFrom", request));
            BigDecimal priceTo = priceParam(resetParam("priceTo", request));
            request.setAttribute("productList", productController.getFileteredBy(catName, name, priceFrom, priceTo));
            request.getRequestDispatcher("/productList.jsp").forward(request, response);
        }
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }

    private BigDecimal priceParam(String param) {
        return param == null || param.isEmpty() ? BigDecimal.ZERO : new BigDecimal(param);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("productList", productController.getAll());
            request.getRequestDispatcher("/productList.jsp").forward(request, response);
        }
        else if (action.equals("delete")) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            productController.delete(id);
            response.sendRedirect("products");
        } else {
            final Product product = action.equals("create") ?
                    new Product() :
                    productController.get(getId(request));
            request.setAttribute("product", product);
            request.setAttribute("catalogList", productController.getCatalogs());
            request.getRequestDispatcher("productEdit.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
