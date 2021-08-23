package com.test.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "rate_limit")
@Getter
@Setter
public class RateLimit {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "rate_limit_type")
    private String rateLimitType;
    @Column(name = "interval")
    private String interval;
    @Column(name = "interval_num")
    private int intervalNum;
    @Column(name = "limit_int")
    private int limit;
    @ManyToOne(optional=true, cascade= CascadeType.ALL)
    @JoinColumn(name="id_exhange_info2", nullable = true)
    private ExhangeInfo exhangeInfo;

    public RateLimit() { this.id = UUID.randomUUID().toString(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateLimit rateLimit = (RateLimit) o;
        return intervalNum == rateLimit.intervalNum &&
                limit == rateLimit.limit &&
                Objects.equals(rateLimitType, rateLimit.rateLimitType) &&
                Objects.equals(interval, rateLimit.interval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rateLimitType, interval, intervalNum, limit);
    }

    @Override
    public String toString() {
        return "RateLimit{" +
                "id='" + id + '\'' +
                ", rateLimitType='" + rateLimitType + '\'' +
                ", interval='" + interval + '\'' +
                ", intervalNum=" + intervalNum +
                ", limit=" + limit + '}';
    }
}
