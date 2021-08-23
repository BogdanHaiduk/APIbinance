package com.test.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "symbol")
@Getter
@Setter
public class Symbol {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "pair")
    private String pair;
    @Column(name = "contract_type")
    private String contractType;
    @Column(name = "delivery_date")
    private Long deliveryDate;
    @Column(name = "onboard_date")
    private Long onboardDate;
    @Column(name = "status")
    private String status;
    @Column(name = "maint_margin_percent")
    private BigDecimal maintMarginPercent;
    @Column(name = "required_margin_percent")
    private BigDecimal requiredMarginPercent;
    @Column(name = "base_asset")
    private String baseAsset;
    @Column(name = "quote_asset")
    private String quoteAsset;
    @Column(name = "margin_asset")
    private String marginAsset;
    @Column(name = "price_precision")
    private int pricePrecision;
    @Column(name = "quantity_precision")
    private int quantityPrecision;
    @Column(name = "base_asset_precision")
    private int baseAssetPrecision;
    @Column(name = "quote_precision")
    private int quotePrecision;
    @Column(name = "underlying_type")
    private String underlyingType;
    @Column(name = "settle_plan")
    private int settlePlan;
    @Column(name = "trigger_protect")
    private BigDecimal triggerProtect;
    @Column(name = "liquidation_fee")
    private BigDecimal liquidationFee;
    @Column(name = "market_take_bound")
    private BigDecimal marketTakeBound;
    @ManyToOne (optional=true, cascade=CascadeType.ALL)
    @JoinColumn (name="id_exhange_info3", nullable = true)
    private ExhangeInfo exhangeInfo;
    @OneToMany (mappedBy = "symbols" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Set<Filter> filters;

    public Symbol() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol1 = (Symbol) o;
        return pricePrecision == symbol1.pricePrecision &&
                quantityPrecision == symbol1.quantityPrecision &&
                baseAssetPrecision == symbol1.baseAssetPrecision &&
                quotePrecision == symbol1.quotePrecision &&
                settlePlan == symbol1.settlePlan &&
                Objects.equals(symbol, symbol1.symbol) &&
                Objects.equals(pair, symbol1.pair) &&
                Objects.equals(contractType, symbol1.contractType) &&
                Objects.equals(deliveryDate, symbol1.deliveryDate) &&
                Objects.equals(onboardDate, symbol1.onboardDate) &&
                Objects.equals(status, symbol1.status) &&
                Objects.equals(maintMarginPercent, symbol1.maintMarginPercent) &&
                Objects.equals(requiredMarginPercent, symbol1.requiredMarginPercent) &&
                Objects.equals(baseAsset, symbol1.baseAsset) &&
                Objects.equals(quoteAsset, symbol1.quoteAsset) &&
                Objects.equals(marginAsset, symbol1.marginAsset) &&
                Objects.equals(underlyingType, symbol1.underlyingType) &&
                Objects.equals(triggerProtect, symbol1.triggerProtect) &&
                Objects.equals(liquidationFee, symbol1.liquidationFee) &&
                Objects.equals(marketTakeBound, symbol1.marketTakeBound) &&
                Objects.equals(filters, symbol1.filters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, pair, contractType, deliveryDate, onboardDate, status, maintMarginPercent, requiredMarginPercent, baseAsset, quoteAsset, marginAsset, pricePrecision, quantityPrecision, baseAssetPrecision, quotePrecision, underlyingType, settlePlan, triggerProtect, liquidationFee, marketTakeBound, filters);
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "id='" + id + '\'' +
                ", symbol='" + symbol + '\'' +
                ", pair='" + pair + '\'' +
                ", contractType='" + contractType + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", onboardDate=" + onboardDate +
                ", status='" + status + '\'' +
                ", maintMarginPercent=" + maintMarginPercent +
                ", requiredMarginPercent=" + requiredMarginPercent +
                ", baseAsset='" + baseAsset + '\'' +
                ", quoteAsset='" + quoteAsset + '\'' +
                ", marginAsset='" + marginAsset + '\'' +
                ", pricePrecision=" + pricePrecision +
                ", quantityPrecision=" + quantityPrecision +
                ", baseAssetPrecision=" + baseAssetPrecision +
                ", quotePrecision=" + quotePrecision +
                ", underlyingType='" + underlyingType + '\'' +
                ", settlePlan=" + settlePlan +
                ", triggerProtect=" + triggerProtect +
                ", liquidationFee=" + liquidationFee +
                ", marketTakeBound=" + marketTakeBound +
                ", filters=" + filters +
                '}';
    }
}
