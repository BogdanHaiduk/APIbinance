package com.test.project.service;

import com.test.project.entity.ExhangeInfo;
import com.test.project.repo.ExhangeInfoRepo;
import com.test.project.repo.FilterRepo;
import com.test.project.repo.SymbolsRepo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Getter
@Service
public class ServiceFirst {

    private final ExhangeInfoRepo exhangeInfoRepo;
    private final SymbolsRepo symbolsRepo;
    private final FilterRepo filterRepo;

    @Autowired
    public ServiceFirst(ExhangeInfoRepo exhangeInfoRepo, SymbolsRepo symbolsRepo, FilterRepo filterRepo) {
        this.exhangeInfoRepo = exhangeInfoRepo;
        this.symbolsRepo = symbolsRepo;
        this.filterRepo = filterRepo;
    }

    public ExhangeInfo lastElement(){
        List<ExhangeInfo> list = getExhangeInfoRepo().findAll();
        return list.get(list.size()-1);
    }

    public boolean checkNewInfo(ExhangeInfo exhangeInfo){
        log.info("New response is starting check information");
        if (!exhangeInfoRepo.findAll().isEmpty()) {
            ExhangeInfo exhangeInfoLast = lastElement();
            if (exhangeInfo.hashCode() == exhangeInfoLast.hashCode()) {
                log.info("the same hashCode");
                if (!exhangeInfo.equals(exhangeInfoLast)) return true;
                else {
                    log.error("the same object");
                    return false;
                }
            } else {
                return true;
            }
        }else return true;
    }

    public void saveNewInfo(ExhangeInfo exhangeInfo){
        if (checkNewInfo(exhangeInfo)) {
            exhangeInfo.getAssets()
                    .forEach(asset -> asset.setExhangeInfo(exhangeInfo));
            exhangeInfo.setAssets(exhangeInfo.getAssets());

            exhangeInfo.getRateLimits()
                    .forEach(rateLimit -> rateLimit.setExhangeInfo(exhangeInfo));
            exhangeInfo.setRateLimits(exhangeInfo.getRateLimits());

            exhangeInfo.getSymbols().
                    forEach(symbol -> {
                        symbol.setExhangeInfo(exhangeInfo);
                        symbol.getFilters().forEach(filter -> filter.setSymbols(symbol));
                    });

            exhangeInfoRepo.save(exhangeInfo);
            log.info("Saved new information with Binance");
        }
    }

}
