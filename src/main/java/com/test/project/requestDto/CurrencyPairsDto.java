package com.test.project.requestDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyPairsDto implements RequestMetaDataDto {
    private BigDecimal minBaseAmount;
    private BigDecimal maxBaseAmount;
    private BigDecimal minCounterAmount;
    private int basePrecision;
    private int counterPrecision;

}
