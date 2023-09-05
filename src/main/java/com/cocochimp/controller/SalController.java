package com.cocochimp.controller;

import com.cocochimp.entity.FileInfo;
import com.cocochimp.entity.ResultVo;
import com.cocochimp.entity.SalInfo;
import com.cocochimp.mapper.FileMapper;
import com.cocochimp.service.FileService;
import com.cocochimp.service.SalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/Sal")
@Api(tags = "3、薪酬管理接口")
public class SalController {

    ResultVo<Object> resultVo = new ResultVo<>();

    @Autowired
    SalService salService;

    @ApiOperation(value = "1-1-1、薪酬登记")
    @PostMapping("/add/insertInfo/{uid}/{o3id}/{fname}/{basic}")
    public ResultVo<Object> insertInfo(@PathVariable("uid") String uid,@PathVariable("o3id") String o3id,@PathVariable("fname") String fname,@PathVariable("basic") double basic) {
        try{
            SalInfo salInfo = new SalInfo();
            salInfo.setUId(uid);
            salInfo.setO3Id(o3id);
            salInfo.setFName(fname);
            salInfo.setBasic(basic);

            salInfo.setTransport(basic);
            salInfo.setLunch(basic*0.1);
            salInfo.setCommunication(basic*0.1);
            salInfo.setPension(basic*0.08);
            salInfo.setUnemployment(basic*0.005);
            salInfo.setMedical(basic*0.02+3);
            salInfo.setHomemoney(basic*0.08);
            salInfo.setReview("");
            salInfo.setAllmoney(salInfo.getBasic()+salInfo.getCommunication()+salInfo.getHomemoney()+ salInfo.getLunch()+ salInfo.getMedical()+salInfo.getPension()+salInfo.getTransport()+salInfo.getUnemployment());

            salService.insertInfo(salInfo);

            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "1-1-2、薪酬登记【查询“薪酬标准名称”（下拉框展示）】")
    @PostMapping("/add/queryXinZiBiaoZhun")
    public ResultVo<Object> queryXinZiBiaoZhun() {
        try{

            List<Map<String, String>> maps = salService.queryXinZiBiaoZhun();

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);

        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "1-2、展示薪资申请的信息")
    @PostMapping("/add/showSalApplication/{page}/{limit}")
    public ResultVo<Object> showSalApplication(@PathVariable("page") int page, @PathVariable("limit") int limit) {
        try{
            List<Map<String, String>> maps = salService.showSalApplication(page,limit);

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);

        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "1-3、2-2、3-2、点击展示详细信息")
    @PostMapping("/showSalInfoBySid/{sid}")
    public ResultVo<Object> showSalInfoBySid(@PathVariable("sid") String sid) {
        try{
            List<Map<String, String>> maps = salService.showSalInfoBySid(sid);

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "1-4、修改内容(先删后加)")
    @PostMapping("/add/updateInfoBySid/{sid}/{uid}/{o3id}/{fname}/{basic}")
    public ResultVo<Object> updateInfoBySid(@PathVariable("sid") String sid,@PathVariable("uid") String uid,@PathVariable("o3id") String o3id,@PathVariable("fname") String fname,@PathVariable("basic") double basic) {
        try{
            SalInfo salInfo = new SalInfo();

            salInfo.setSId(sid);
            salInfo.setUId(uid);
            salInfo.setO3Id(o3id);
            salInfo.setFName(fname);
            salInfo.setBasic(basic);

            salInfo.setTransport(basic);
            salInfo.setLunch(basic*0.1);
            salInfo.setCommunication(basic*0.1);
            salInfo.setPension(basic*0.08);
            salInfo.setUnemployment(basic*0.005);
            salInfo.setMedical(basic*0.02+3);
            salInfo.setHomemoney(basic*0.08);
            salInfo.setReview("");
            salInfo.setAllmoney(salInfo.getBasic()+salInfo.getCommunication()+salInfo.getHomemoney()+ salInfo.getLunch()+ salInfo.getMedical()+salInfo.getPension()+salInfo.getTransport()+salInfo.getUnemployment());

            salService.updateInfoBySid(salInfo);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "1-5、删除内容")
    @PostMapping("/add/deleteInfoBySid/{sid}")
    public ResultVo<Object> deleteInfoBySid(@PathVariable("sid") String sid) {
        try{
            salService.deleteInfoBySid(sid);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "2-1、薪酬复核:展示所有信息")
    @PostMapping("/check/showSalRechecked/{page}/{limit}")
    public ResultVo<Object> showSalRechecked(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            List<Map<String, String>> maps = salService.showSalRechecked(page, limit);

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "2-3、复核通过")
    @PostMapping("/check/updateCheckInfoBySid/{sid}")
    public ResultVo<Object> updateCheckInfoBySid(@PathVariable("sid") String sid) {
        try{
            salService.updateCheckInfoBySid(sid);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "2-4、复核失败(驳回)")
    @PostMapping("/check/updateCheckFalseInfoBySid/{sid}")
    public ResultVo<Object> updateCheckFalseInfoBySid(@PathVariable("sid") String sid) {
        try{
            salService.updateCheckFalseInfoBySid(sid);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "3-1、薪酬查询:查询“档案查询”的所有信息")
    @PostMapping("/query/showSalSelect/{page}/{limit}")
    public ResultVo<Object> showSalSelect(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            List<Map<String, String>> maps = salService.showSalSelect(page, limit);

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "3-3-1、通过”薪资标准名称“查询【模糊查询】")
    @PostMapping("/query/showSalSelectByXinZiBiaoZhun/{page}/{limit}/{info}")
    public ResultVo<Object> showSalSelectByXinZiBiaoZhun(@PathVariable("page") int page,@PathVariable("limit") int limit,@PathVariable("info") String info) {
        try{
            List<Map<String, String>> maps = salService.showSalSelectByXinZiBiaoZhun(page, limit,info);

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "3-3-2、通过”登记人“查询【模糊查询】")
    @PostMapping("/query/showSalSelectByDengJiRen/{page}/{limit}/{info}")
    public ResultVo<Object> showSalSelectByDengJiRen(@PathVariable("page") int page,@PathVariable("limit") int limit,@PathVariable("info") String info) {
        try{
            List<Map<String, String>> maps = salService.showSalSelectByDengJiRen(page, limit,info);

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "3-3-3、通过”薪资总额“查询【范围查询】")
    @PostMapping("/query/showSalSelectByAllMoney/{page}/{limit}/{lowMoney}/{highMoney}")
    public ResultVo<Object> showSalSelectByAllMoney(@PathVariable("page") int page,@PathVariable("limit") int limit,@PathVariable("lowMoney") int lowMoney,@PathVariable("highMoney") int highMoney) {
        try{
            List<Map<String, String>> maps = salService.showSalSelectByAllMoney(page, limit,lowMoney,highMoney);

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "4-1、薪酬发放:薪酬发放登记页面")
    @PostMapping("/give/giveSalInfo/{page}/{limit}")
    public ResultVo<Object> giveSalInfo(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            List<Map<String, String>> maps = salService.giveSalInfo(page, limit);

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "4-2-1、薪酬发放登记细则:展示所有数据")
    @PostMapping("/give/giveSalInfoByO3Id/{page}/{limit}/{o3id}")
    public ResultVo<Object> giveSalInfoByO3Id(@PathVariable("page") int page,@PathVariable("limit") int limit,@PathVariable("o3id") String o3id) {
        try{
            List<Map<String, String>> maps = salService.giveSalInfoByO3Id(page, limit,o3id);

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "4-2-2、薪酬发放登记细则:提交所有数据")
    @PostMapping("/give/updateSalGiveInfoByO3Id/{o3id}")
    public ResultVo<Object> updateSalGiveInfoByO3Id(@PathVariable("o3id") String o3id) {
        try{
            salService.updateSalGiveInfoByO3Id(o3id);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "4-2-3、薪酬发放登记细则:发放成功的所有数据")
    @PostMapping("/give/giveSalAllInfo/{page}/{limit}")
    public ResultVo<Object> giveSalAllInfo(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            List<Map<String, String>> maps = salService.giveSalAllInfo(page, limit);

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


}
