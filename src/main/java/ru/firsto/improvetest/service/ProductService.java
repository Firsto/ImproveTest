package ru.firsto.improvetest.service;

import org.springframework.stereotype.Service;
import ru.firsto.improvetest.LoggerWrapper;
import ru.firsto.improvetest.dao.ProductDAO;
import ru.firsto.improvetest.model.Catalog;
import ru.firsto.improvetest.model.Product;

import javax.transaction.Transactional;
import java.util.List;

/**
 * User: razor
 * Date: 26.12.15
 */
@Service("productService")
@Transactional
public class ProductService {

    private static final LoggerWrapper logger = LoggerWrapper.get(ProductService.class);

    private static ProductDAO dao;

    public ProductService() {
        dao = new ProductDAO();
    }

    public void persist(Product entity) {
        dao.openCurrentSessionWithTransaction();
        dao.persist(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    public void update(Product entity) {
        dao.openCurrentSessionWithTransaction();
        dao.update(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    public Product findById(Integer id) {
        dao.openCurrentSession();
        Product product = dao.findById(id);
        dao.closeCurrentSession();
        return product;
    }

    /**
     * Удаляет товар
     *
     * @param id товара
     */
    public void delete(Integer id) {
        logger.debug("Deleting existing product");
        dao.openCurrentSessionWithTransaction();
        Product product = dao.findById(id);
        dao.delete(product);
        dao.closeCurrentSessionWithTransaction();
    }

    public List<Product> findAll() {
        dao.openCurrentSession();
        List<Product> products = dao.findAll();
        dao.closeCurrentSession();
        return products;
    }

    public void deleteAll() {
        dao.openCurrentSessionWithTransaction();
        dao.deleteAll();
        dao.closeCurrentSessionWithTransaction();
    }

    public ProductDAO productDao() {
        return dao;
    }


    /**
     * Получает все товары
     *
     * @return список товаров
     */
    public List<Product> getAll() {
        logger.debug("Retrieving all products");

        return findAll();
    }

    /**
     * Получает список категорий
     *
     * //TODO: вынести в сервис категорий
     * @deprecated
     * @return список категорий
     */
    @Deprecated
    public List<Catalog> getCatalogs() {
        dao.openCurrentSession();
        List<Catalog> catalogs = dao.getCatalogs();
        dao.closeCurrentSession();
        return catalogs;
    }

    /**
     * Получает один товар
     */
    public Product get(Integer id) {
        return findById(id);
    }

    /**
     * Добавлеяет новый товар
     */
    public void add(Product product) {
        logger.debug("Добавление нового товара");

        persist(product);
    }


    /**
     * Редактирует существующий товар
     */
    public void edit(Product product) {
        logger.debug("Редактирование существующего товара");

        // Получаем товар по id
        Product existingProduct = get(product.getId());

        // Устанавливаем обновлённые поля
        existingProduct.setName(product.getName());
        existingProduct.setCatalog(product.getCatalog());
        existingProduct.setPrice(product.getPrice());

        // Сохраняем изменения
        update(existingProduct);
    }
}