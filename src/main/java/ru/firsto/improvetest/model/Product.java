package ru.firsto.improvetest.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * User: razor
 * Date: 25.12.15
 */

@Entity
@Table(name= "prod", schema = "improvetest")
public class Product extends NamedEntity {
    protected BigDecimal price;
    protected Catalog catalog;

    public Product() {
    }

    public Product(Integer id, String name, Catalog catalog, BigDecimal price) {
        super(id, name);
        this.catalog = catalog;
        this.price = price;
    }

    public Product(Integer id, String name, Integer category, BigDecimal price) {
        this(id, name, new Catalog(category, ""), price);
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Override
    public Integer getId() {
        return super.getId();
    }

    @Basic
    @Column(name = "name")
    @Override
    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "cat_id")
    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name=" + name +
                ", catalog=" + catalog +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(price, product.price) &&
                Objects.equals(catalog, product.catalog) &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price);
    }
}
