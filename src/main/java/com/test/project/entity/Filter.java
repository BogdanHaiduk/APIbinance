package com.test.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "filter_table")
@Getter
@Setter
public class Filter {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "min_price")
    private BigDecimal minPrice;
    @Column(name = "max_price")
    private BigDecimal maxPrice;
    @Column(name = "filter_type")
    private String filterType;
    @Column(name = "tick_size")
    private BigDecimal tickSize;
    @Column(name = "step_size")
    private BigDecimal stepSize;
    @Column(name = "max_qty")
    private BigDecimal maxQty;
    @Column(name = "min_qty")
    private BigDecimal minQty;
    @Column(name = "limit_for_filter")
    private int limit;
    @Column(name = "multiplier_down")
    private BigDecimal multiplierDown;
    @Column(name = "multiplier_up")
    private BigDecimal multiplierUp;
    @Column(name = "multiplier_decimal")
    private BigDecimal multiplierDecimal;
    @ManyToOne(optional=true, cascade=CascadeType.ALL)
    @JoinColumn (name="id_symbols", nullable = true)
    private Symbol symbols;

    public Filter() { this.id = UUID.randomUUID().toString(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return limit == filter.limit &&
                Objects.equals(minPrice, filter.minPrice) &&
                Objects.equals(maxPrice, filter.maxPrice) &&
                Objects.equals(filterType, filter.filterType) &&
                Objects.equals(tickSize, filter.tickSize) &&
                Objects.equals(stepSize, filter.stepSize) &&
                Objects.equals(maxQty, filter.maxQty) &&
                Objects.equals(minQty, filter.minQty) &&
                Objects.equals(multiplierDown, filter.multiplierDown) &&
                Objects.equals(multiplierUp, filter.multiplierUp) &&
                Objects.equals(multiplierDecimal, filter.multiplierDecimal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minPrice, maxPrice, filterType, tickSize, stepSize, maxQty, minQty, limit,
                multiplierDown, multiplierUp, multiplierDecimal);
    }

    @Override
    public String toString() {
        return "Filter{" +
                "id='" + id + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", filterType='" + filterType + '\'' +
                ", tickSize=" + tickSize +
                ", stepSize=" + stepSize +
                ", maxQty=" + maxQty +
                ", minQty=" + minQty +
                ", limit=" + limit +
                ", multiplierDown=" + multiplierDown +
                ", multiplierUp=" + multiplierUp +
                ", multiplierDecimal=" + multiplierDecimal +
                '}';
    }
}
