package ru.firsto.improvetest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.firsto.improvetest.LoggerWrapper;
import ru.firsto.improvetest.model.Catalog;
import ru.firsto.improvetest.model.Product;
import ru.firsto.improvetest.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * User: razor
 * Date: 25.12.2015
 */
@Controller
public class ProductRestController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(ProductRestController.class);

    @Autowired
    private ProductService service;

    public Product get(int id) {
        LOG.info("get product {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        LOG.info("delete product {}", id);
        service.delete(id);
    }

    public List<Product> getAll() {
        LOG.info("getAll Products");
        return service.getAll();
    }

    public void update(Product product) {
        LOG.info("update {}", product);
        service.edit(product);
    }

    public void create(Product product) {
        LOG.info("create {}", product);
        service.add(product);
    }

    public List<Product> getFileteredBy(String catName, String name, BigDecimal priceFrom, BigDecimal priceTo) {
        LOG.info("filtered by catalog {}. name \"{}\" and price from {} to {}", catName, name, priceFrom, priceTo);
        List<Product> products = getAll();

        Stream<Product> result = products.parallelStream();
        if (catName != null && !catName.isEmpty()) result = result.filter(product -> product.getCatalog().getName().toLowerCase().startsWith(catName.toLowerCase()));
        if (name != null && !name.isEmpty()) result = result.filter(product -> product.getName().toLowerCase().startsWith(name.toLowerCase()));
        if (priceFrom != null && !priceFrom.equals(BigDecimal.ZERO)) result = result.filter(product -> priceFrom.compareTo(product.getPrice()) <= 0);
        if (priceTo != null && !priceTo.equals(BigDecimal.ZERO)) result = result.filter(product -> priceTo.compareTo(product.getPrice()) >= 0);

        return result.collect(Collectors.toList());
    }

    public List<Catalog> getCatalogs() {
        return service.getCatalogs();
    }
}