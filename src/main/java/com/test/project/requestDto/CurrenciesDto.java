package com.test.project.requestDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrenciesDto implements RequestMetaDataDto {
    private BigDecimal withdrawalFee;
    private BigDecimal minWithdrawal;
}
