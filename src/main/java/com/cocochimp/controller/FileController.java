package com.cocochimp.controller;

import com.cocochimp.entity.FileInfo;
import com.cocochimp.entity.ResultVo;
import com.cocochimp.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/File")
@Api(tags = "2、档案管理接口")
public class FileController {

    ResultVo<Object> resultVo = new ResultVo<>();

    @Autowired
    FileService fileService;

    @ApiOperation(value = "1-1-1、档案登记")
    @PostMapping("/add/{name}/{age}/{professionId}/{OrganizationId}/{sex}/{image}")
    public ResultVo<Object> queryGoodsList(@PathVariable("name") String name, @PathVariable("age") String age, @PathVariable("professionId") String professionId, @PathVariable("image") String image, @PathVariable("OrganizationId") String OrganizationId, @PathVariable("sex") String sex) {
        try{
            FileInfo fileInfo=new FileInfo();
            fileInfo.setSex(sex);
            fileInfo.setName(name);
            fileInfo.setAge(age);
            fileInfo.setProfessionId(professionId);
            fileInfo.setImage(image);
            fileInfo.setOrganizionId(OrganizationId);

            fileService.insertInfo(fileInfo);
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

    @ApiOperation(value = "1-1-1、上传图片")
    @PostMapping("/add/picture")
    public ResultVo<Object> upPicture(MultipartFile file) {
        try{
            resultVo.setData(fileService.uploadFileAvatar(file));
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


    @ApiOperation(value = "1-1-2、档案登记【查询所有职称】")
    @PostMapping("/add/queryProfession")
    public ResultVo<Object> queryProfession() {
        try{

            List<Map<String, String>> maps = fileService.queryProfession();

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

    @ApiOperation(value = "1-1-3、档案登记【查询所有民族】")
    @PostMapping("/add/queryNation")
    public ResultVo<Object> queryNation() {
        try{

            List<Map<String, String>> maps = fileService.queryNation();

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

    @ApiOperation(value = "1-1-4、档案登记【查询所有学历】")
    @PostMapping("/add/queryAcademic")
    public ResultVo<Object> queryAcademic() {
        try{

            List<Map<String, String>> maps = fileService.queryAcademic();

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

    @ApiOperation(value = "1-1-5、档案登记【查询所有宗教】")
    @PostMapping("/add/queryReligion")
    public ResultVo<Object> queryReligion() {
        try{
            List<Map<String, String>> maps = fileService.queryReligion();

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

    @ApiOperation(value = "1-2、展示档案申请的信息")
    @PostMapping("/add/showFileApplication/{page}/{limit}")
    public ResultVo<Object> showFileApplication(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            List<Map<String, String>> maps = fileService.showFileApplication(page,limit);

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
    @PostMapping("/showFileInfoByFid/{fid}")
    public ResultVo<Object> showFileInfoByFid(@PathVariable("fid") String fid) {
        try{
            List<Map<String, String>> maps = fileService.showFileInfoByFid(fid);

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

    @ApiOperation(value = "1-5、修改内容(先删后加)")
    @PostMapping("/add/updateInfoByFid/{fid}")
    public ResultVo<Object> updateInfoByFid(@PathVariable("fid") String fid,String uid,FileInfo fileInfo,MultipartFile file) {
        try{
            fileService.updateInfoByFid(fid,uid,fileInfo,file);
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

    @ApiOperation(value = "1-4、删除内容")
    @PostMapping("/add/deleteInfoByFid/{fid}")
    public ResultVo<Object> deleteInfoByFid(@PathVariable("fid") String fid) {
        try{
            fileService.deleteInfoByFid(fid);
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

    @ApiOperation(value = "2-1、档案复核:展示所有信息")
    @PostMapping("/check/showFileRechecked/{page}/{limit}")
    public ResultVo<Object> showFileRechecked(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            List<Map<String, String>> maps = fileService.showFileRechecked(page, limit);

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
    @PostMapping("/check/updateCheckInfoByFid/{fid}")
    public ResultVo<Object> updateCheckInfoByFid(@PathVariable("fid") String fid) {
        try{
            fileService.updateCheckInfoByFid(fid);

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
    @PostMapping("/check/updateCheckFalseInfoByFid/{fid}")
    public ResultVo<Object> updateCheckFalseInfoByFid(@PathVariable("fid") String fid) {
        try{
            fileService.updateCheckFalseInfoByFid(fid);
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

    @ApiOperation(value = "3-1、查询“档案查询”的所有信息")
    @PostMapping("/query/showFileSelect/{page}/{limit}")
    public ResultVo<Object> showFileSelect(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            List<Map<String, String>> maps = fileService.showFileSelect(page, limit);

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

    @ApiOperation(value = "3-3-1、通过一级机构查询【模糊查询】")
    @PostMapping("/query/showFileSelectByOrgan1/{page}/{limit}/{info}")
    public ResultVo<Object> showFileSelectByOrgan1(@PathVariable("page") int page,@PathVariable("limit") int limit,@PathVariable("info") String info) {
        try{
            List<Map<String, String>> maps = fileService.showFileSelectByOrgan1(page, limit,info);

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

    @ApiOperation(value = "3-3-2、通过二级机构查询【模糊查询】")
    @PostMapping("/query/showFileSelectByOrgan2/{page}/{limit}/{info}")
    public ResultVo<Object> showFileSelectByOrgan2(@PathVariable("page") int page,@PathVariable("limit") int limit,@PathVariable("info") String info) {
        try{
            List<Map<String, String>> maps = fileService.showFileSelectByOrgan2(page, limit,info);

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

    @ApiOperation(value = "3-3-3、通过三级机构查询【模糊查询】")
    @PostMapping("/query/showFileSelectByOrgan3/{page}/{limit}/{info}")
    public ResultVo<Object> showFileSelectByOrgan3(@PathVariable("page") int page,@PathVariable("limit") int limit,@PathVariable("info") String info) {
        try{
            List<Map<String, String>> maps = fileService.showFileSelectByOrgan3(page, limit,info);

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

    @ApiOperation(value = "3-3-4、通过职位查询【模糊查询】")
    @PostMapping("/query/showFileSelectByProfession/{page}/{limit}/{info}")
    public ResultVo<Object> showFileSelectByProfession(@PathVariable("page") int page,@PathVariable("limit") int limit,@PathVariable("info") String info) {
        try{
            List<Map<String, String>> maps = fileService.showFileSelectByProfession(page, limit,info);

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
