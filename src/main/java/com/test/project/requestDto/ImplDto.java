package com.test.project.requestDto;

import com.test.project.entity.ExhangeInfo;
import com.test.project.entity.Filter;

import java.math.BigDecimal;
import java.util.*;

public class ImplDto {

    private ImplDto() {
    }

    public static Map<String,Map<String, RequestMetaDataDto>> impl (ExhangeInfo exhangeInfo){
        Map<String,Map<String, RequestMetaDataDto>> map = new HashMap<>();
        Map<String, RequestMetaDataDto> currencyPairsDtoList = new HashMap<>();
        Map<String, RequestMetaDataDto> currenciesDtoList = new HashMap<>();

        exhangeInfo.getSymbols()
                .forEach(symbols ->{
                    CurrencyPairsDto currencyPairsDto = new CurrencyPairsDto();
                    CurrenciesDto currenciesDto = new CurrenciesDto();
                    Optional <BigDecimal> min =
                            symbols.getFilters().stream().map(Filter::getMinPrice)
                                    .min(Comparator.nullsLast(Comparator.comparingDouble(BigDecimal::doubleValue)));
                    Optional <BigDecimal> max =
                            symbols.getFilters().stream().map(Filter::getMaxPrice)
                                    .max(Comparator.nullsFirst(Comparator.comparingDouble(BigDecimal::doubleValue)));
                    Optional <BigDecimal> minQty =
                            symbols.getFilters().stream().map(Filter::getMinQty)
                                    .min(Comparator.nullsLast(Comparator.comparingDouble(BigDecimal::doubleValue)));

                    currencyPairsDto.setMaxBaseAmount(max.get());
                    currencyPairsDto.setMinBaseAmount(min.get());
                    currencyPairsDto.setMinCounterAmount(minQty.get());
                    currencyPairsDto.setBasePrecision(symbols.getBaseAssetPrecision());
                    currencyPairsDto.setCounterPrecision(symbols.getQuantityPrecision());

                    currenciesDto.setWithdrawalFee(symbols.getLiquidationFee());
                    currenciesDto.setMinWithdrawal(symbols.getMarketTakeBound());

                    currencyPairsDtoList.put(symbols.getSymbol(),currencyPairsDto);
                    currenciesDtoList.put(symbols.getBaseAsset(),currenciesDto);
                });

        map.put("currencyPairs",currencyPairsDtoList);
        map.put("currencies",currenciesDtoList);

        return map;
    }
}
