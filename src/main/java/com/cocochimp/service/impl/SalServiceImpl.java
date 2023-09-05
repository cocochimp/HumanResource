package com.cocochimp.service.impl;

import com.cocochimp.entity.SalInfo;
import com.cocochimp.mapper.SalMapper;
import com.cocochimp.service.SalService;
import com.cocochimp.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class SalServiceImpl implements SalService {

    @Autowired
    SalMapper salMapper;

    @Override
    public void insertInfo(SalInfo salInfo) {
        //生成订单时间orderData
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        salInfo.setCreateTime(dateTime.format(formatter));

        //生成id
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        salInfo.setSId(String.valueOf(id));

        salInfo.setUId(salInfo.getUId());
        salInfo.setFName(salInfo.getFName());
        salMapper.insertInfo(salInfo);
    }

    @Override
    public List<Map<String, String>> queryXinZiBiaoZhun() {
        return salMapper.queryXinZiBiaoZhun();
    }

    @Override
    public List<Map<String, String>> showSalApplication(int page, int limit) {
        List<Map<String, String>> maps1 = salMapper.showSalApplication(page, limit);
        List<Map<String, String>> maps = salMapper.showStatus(page, limit);

        Iterator<Map<String, String>> iterator = maps.iterator();
        int index=0;
        while (iterator.hasNext()) {
            Map<String, String> result = iterator.next();

            if(result.get("is_reject").equals("0")){
                maps1.get(index).put("is_reject","已申请");
            }else{
                maps1.get(index).put("is_reject","被驳回");
            }
            index++;
        }
        return maps1;
    }

    @Override
    public List<Map<String, String>> showSalInfoBySid(String sid) {
        return salMapper.showSalInfoBySid(sid);
    }

    @Override
    public void updateInfoBySid(SalInfo salInfo) {
        salMapper.deleteInfoBySid(salInfo.getSId());

        //生成订单时间orderData
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        salInfo.setCreateTime(dateTime.format(formatter));

        //生成id
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        salInfo.setSId(String.valueOf(id));

        salInfo.setUId(salInfo.getUId());
        salInfo.setFName(salInfo.getFName());
        salMapper.insertInfo(salInfo);
    }

    @Override
    public void deleteInfoBySid(String sid) {
        salMapper.updateInfoBySid(sid);
    }

    @Override
    public List<Map<String, String>> showSalRechecked(int page, int limit) {
        return salMapper.showSalRechecked(page, limit);
    }

    @Override
    public void updateCheckInfoBySid(String sid) {
        salMapper.updateCheckInfoBySid(sid);
    }

    @Override
    public void updateCheckFalseInfoBySid(String sid) {
        salMapper.updateCheckFalseInfoBySid(sid);
    }

    @Override
    public List<Map<String, String>> showSalSelect(int page, int limit) {
        return salMapper.showSalSelect(page, limit);
    }

    @Override
    public List<Map<String, String>> showSalSelectByXinZiBiaoZhun(int page, int limit, String info) {
        return salMapper.showSalSelectByXinZiBiaoZhun(page, limit, info);
    }

    @Override
    public List<Map<String, String>> showSalSelectByDengJiRen(int page, int limit, String info) {
        return salMapper.showSalSelectByDengJiRen(page, limit, info);
    }

    @Override
    public List<Map<String, String>> showSalSelectByAllMoney(int page, int limit, int lowMoney, int highMoney) {
        return salMapper.showSalSelectByAllMoney(page, limit, lowMoney, highMoney);
    }

    @Override
    public List<Map<String, String>> giveSalInfo(int page, int limit) {
        return salMapper.giveSalInfo(page, limit);
    }

    @Override
    public List<Map<String, String>> giveSalInfoByO3Id(int page, int limit, String o3id) {
        return salMapper.giveSalInfoByO3Id(page, limit, o3id);
    }

    @Override
    public void updateSalGiveInfoByO3Id(String o3id) {
        salMapper.updateSalGiveInfoByO3Id(o3id);
    }

    @Override
    public List<Map<String, String>> giveSalAllInfo(int page, int limit) {
        return salMapper.giveSalAllInfo(page, limit);
    }
}
