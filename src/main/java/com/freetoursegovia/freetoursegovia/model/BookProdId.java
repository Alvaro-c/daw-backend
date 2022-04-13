package com.freetoursegovia.freetoursegovia.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookProdId implements Serializable {
    private static final long serialVersionUID = 2514686912244193802L;
    @Column(name = "id_booking", nullable = false)
    private Integer idBooking;

    @Column(name = "id_product", nullable = false)
    private Integer idProduct;

    public Integer getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Integer idBooking) {
        this.idBooking = idBooking;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookProdId entity = (BookProdId) o;
        return Objects.equals(this.idBooking, entity.idBooking) &&
                Objects.equals(this.idProduct, entity.idProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBooking, idProduct);
    }

}