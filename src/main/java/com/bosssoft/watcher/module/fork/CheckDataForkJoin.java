package com.bosssoft.watcher.module.fork;

import com.bosssoft.watcher.dao.RuleAdministrationDAO;
import com.bosssoft.watcher.entity.PO.ErrorDataPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

@Component
public class CheckDataForkJoin extends RecursiveTask<List<ErrorDataPO>> {

    private static final Logger logger = LoggerFactory.getLogger(CheckDataForkJoin.class);

    @Resource
    private RuleAdministrationDAO ruleAdministrationDAO;

    private final List<String> list;

    private final int cpu = Runtime.getRuntime().availableProcessors();

    public CheckDataForkJoin(List<String> list) {
        this.list = list;
    }

    @Override
    protected List<ErrorDataPO> compute() {
        List<ErrorDataPO> errorDataPOList = new ArrayList<>();
        if (list.size() > cpu) {
            CheckDataForkJoin checkOne = new CheckDataForkJoin(list.subList(0, list.size() / 2));
            CheckDataForkJoin checkTwo = new CheckDataForkJoin(list.subList(list.size() / 2, list.size()));

            invokeAll(checkOne,checkTwo);

            try{
                errorDataPOList.addAll(checkOne.get());
                errorDataPOList.addAll(checkTwo.get());
            } catch (ExecutionException | InterruptedException e) {
                logger.error(e.getMessage());
            }
        }else {
            errorDataPOList = ruleAdministrationDAO.retrieveErrorData(this.list);
        }
        return errorDataPOList;
    }
}
