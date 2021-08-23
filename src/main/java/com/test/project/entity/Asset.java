package com.test.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "asset")
@Getter
@Setter
public class Asset{
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "asset")
    private String asset;
    @Column(name = "margin_available")
    private  boolean marginAvailable;
    @Column(name = "auto_asset_exchange")
    private int autoAssetExchange;
    @ManyToOne(optional=true, cascade= CascadeType.ALL)
    @JoinColumn(name="id_exhange_info1", nullable = true)
    private ExhangeInfo exhangeInfo;

    public Asset() { this.id = UUID.randomUUID().toString(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset1 = (Asset) o;
        return marginAvailable == asset1.marginAvailable &&
                autoAssetExchange == asset1.autoAssetExchange &&
                Objects.equals(asset, asset1.asset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asset, marginAvailable, autoAssetExchange);
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", asset='" + asset + '\'' +
                ", marginAvailable=" + marginAvailable +
                ", autoAssetExchange=" + autoAssetExchange +
                '}';
    }
}
