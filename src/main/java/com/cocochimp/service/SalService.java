package com.cocochimp.service;

import com.cocochimp.entity.SalInfo;
import com.cocochimp.mapper.SalMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public interface SalService {

    //1-1、薪酬登记
    void insertInfo(SalInfo salInfo);

    //1-1、薪酬登记【查询"薪酬标准名称"（下拉框展示）】
    List<Map<String,String>> queryXinZiBiaoZhun();

    //1-2、展示薪资申请的信息
    //1-2、通过is_reject判断数据状态
    List<Map<String,String>> showSalApplication(int page,int limit);

    //点击展示信息(1-3、2-2、3-2)
    List<Map<String,String>> showSalInfoBySid(String sid);

    //1-3、修改内容(先删后加)
    void updateInfoBySid(SalInfo salInfo);

    //1-4、删除内容
    void deleteInfoBySid(String sid);

    //2-1、薪酬复核:展示所有信息
    List<Map<String,String>> showSalRechecked(int page,int limit);

    //2-2、点击复核展示信息
    //见上面——>点击展示信息(1-3、2-2、3-2)【List<Map<String,String>> showSalInfoBySid(@Param("sid") String Sid);】

    //2-3、复核通过
    void updateCheckInfoBySid(String sid);

    //2-4、复核失败(驳回)
    void updateCheckFalseInfoBySid(String sid);

    //3-1、薪酬查询:查询“档案查询”的所有信息
    List<Map<String,String>> showSalSelect(int page,int limit);

    //3-3、通过"薪资标准名称"查询
    List<Map<String,String>> showSalSelectByXinZiBiaoZhun(int page,int limit,String info);

    //3-3、通过"登记人"查询
    List<Map<String,String>> showSalSelectByDengJiRen(int page,int limit,String info);

    //3-3、通过"薪资总额"查询
    List<Map<String,String>> showSalSelectByAllMoney(int page,int limit,int lowMoney,int highMoney);

    //4-1、薪酬发放:薪酬发放登记页面
    List<Map<String,String>> giveSalInfo(int page,int limit);

    //4-2、薪酬发放登记细则:展示所有数据
    List<Map<String,String>> giveSalInfoByO3Id(int page,int limit,String o3id);

    //4-2、薪酬发放登记细则:提交所有数据
    void updateSalGiveInfoByO3Id(String o3id);

    //4-2、薪酬发放登记细则:发放成功的所有数据
    List<Map<String,String>> giveSalAllInfo(int page,int limit);


}
