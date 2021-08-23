package com.test.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "exhange_info")
@Getter
@Setter
public class ExhangeInfo {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "timezone")
    private String timezone;
    @Column(name = "server_time")
    private Long serverTime;
    @Column(name = "futures_type")
    private String futuresType;
    @OneToMany (mappedBy = "exhangeInfo" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Set<RateLimit> rateLimits;
    @OneToMany (mappedBy = "exhangeInfo" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Set<Asset> assets;
    @OneToMany (mappedBy = "exhangeInfo" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Set<Symbol> symbols;

    public ExhangeInfo() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExhangeInfo that = (ExhangeInfo) o;
        return Objects.equals(timezone, that.timezone) &&
                Objects.equals(futuresType, that.futuresType) &&
                Objects.equals(rateLimits, that.rateLimits) &&
                Objects.equals(assets, that.assets) &&
                Objects.equals(symbols, that.symbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timezone, futuresType, rateLimits, assets, symbols);
    }

    @Override
    public String toString() {
        return "ExhangeInfo{" +
                "id='" + id + '\'' +
                ", timezone='" + timezone + '\'' +
                ", serverTime=" + serverTime +
                ", futuresType='" + futuresType + '\'' +
                ", rateLimits=" + rateLimits +
                ", assets=" + assets +
                ", symbols=" + symbols +
                '}';
    }
}