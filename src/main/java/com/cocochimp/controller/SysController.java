package com.cocochimp.controller;

import com.cocochimp.entity.*;
import com.cocochimp.service.SysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/Sys")
@Api(tags = "1、系统管理接口")
public class SysController{

    ResultVo<Object> resultVo = new ResultVo<>();

    @Autowired
    SysService sysService;

    @ApiOperation(value = "0-1-1、用户注册")
    @PostMapping("/register/{name}/{password}/{rid}")
    public ResultVo<Object> insertUser(@PathVariable("name") String name,
                                       @PathVariable("password") String password,
                                       @PathVariable("rid") String rid) {
        try{
            SysUser sysUser = new SysUser();
            sysUser.setUsername(name);
            sysUser.setPassword(password);
            sysUser.setrId(rid);

            sysService.insertUser(sysUser);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "0-1-2、选择用户身份")
    @PostMapping("/register/queryAllRole")
    public ResultVo<Object> queryAllRole() {
        try{
            resultVo.setData(sysService.queryAllRole());
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "0-2-1、用户登录")
    @PostMapping("/login")
    public ResultVo<Object> login(SysUser sysUser) {
        List<Map<String, Object>> login = sysService.login(sysUser);
        try{
            if(!login.isEmpty()){
                resultVo.setData(login);
                resultVo.setMess("true");
                resultVo.setOk(true);
            }else{
                resultVo.setData("");
                resultVo.setMess("没有该用户");
                resultVo.setOk(false);
            }
            System.out.println(sysUser.getUsername()+","+sysUser.getPassword());
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "0-2-2、用户登录的权限路径")
    @PostMapping("/login/loginPermission")
    public ResultVo<Object> loginPermission(SysUser sysUser) {
        try{
            resultVo.setData(sysService.loginPermission(sysUser));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "1-1-1、查看所有用户")
    @PostMapping("/user/showAllUser/{page}/{limit}")
    public ResultVo<Object> showAllUser(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(sysService.showAllUser(page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "1-1-2、通过姓名查询用户")
    @PostMapping("/user/queryUserByName/{username}/{page}/{limit}")
    public ResultVo<Object> queryUserByName(@PathVariable("username") String username,@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(sysService.queryUserByName(username,page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "1-1-3、通过角色身份查询用户(下拉框)")
    @PostMapping("/user/queryUserByRole/{role_name}/{page}/{limit}")
    public ResultVo<Object> queryUserByRole(@PathVariable("role_name") String role_name,@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(sysService.queryUserByRole(role_name,page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "1-2、删除指定用户(通过u_id)")
    @PostMapping("/user/deleteUserByUid/{uid}")
    public ResultVo<Object> deleteUserByUid(@PathVariable("uid") String uid) {
        try{
            sysService.deleteUserByUid(uid);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "1-3、修改指定用户的角色")
    @PostMapping("/user/updateUserRoleByUid")
    public ResultVo<Object> updateUserRoleByUid(SysUser sysUser) {
        try{
            sysService.updateUserRoleByUid(sysUser);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "3-1-1、查询所有机构")
    @PostMapping("/organ/queryAllOrganization/{page}/{limit}")
    public ResultVo<Object> queryAllOrganization(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(sysService.queryAllOrganization(page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "3-1-2、通过机构名查询机构")
    @PostMapping("/organ/queryOrganizationByName/{organName}/{page}/{limit}")
    public ResultVo<Object> queryOrganizationByName(@PathVariable("organName") String organName,@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(sysService.queryOrganizationByName(organName,page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "3-2-1、增加机构(一级下拉框)")
    @PostMapping("/organ/queryOrgan1")
    public ResultVo<Object> queryOrgan1() {
        try{
            resultVo.setData(sysService.queryOrgan1());
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "3-2-2、增加机构(二级下拉框)")
    @PostMapping("/organ/queryOrgan2/{o1id}")
    public ResultVo<Object> queryOrgan2(@PathVariable("o1id") String o1id) {
        try{
            resultVo.setData(sysService.queryOrgan2(o1id));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "3-2-2、增加机构(二级下拉框)")
    @PostMapping("/organ/queryOrgan3/{o2id}")
    public ResultVo<Object> queryOrgan3(@PathVariable("o2id") String o2id) {
        try{
            resultVo.setData(sysService.queryOrgan3(o2id));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "3-2-3、增加机构(一级)")
    @PostMapping("/organ/insertOrgan1")
    public ResultVo<Object> insertOrgan1(SysOrgan1 sysOrgan1) {
        try{
            sysService.insertOrgan1(sysOrgan1);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "3-2-4、增加机构(二级)")
    @PostMapping("/organ/insertOrgan2")
    public ResultVo<Object> insertOrgan2(SysOrgan2 sysOrgan2) {
        try{
            sysService.insertOrgan2(sysOrgan2);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "3-2-5、增加机构(三级)")
    @PostMapping("/organ/insertOrgan3")
    public ResultVo<Object> insertOrgan3(SysOrgan3 sysOrgan3) {
        try{
            sysService.insertOrgan3(sysOrgan3);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "3-3、删除三级机构")
    @PostMapping("/organ/deleteOrgan3/{o3id}")
    public ResultVo<Object> deleteOrgan3(@PathVariable("o3id") String o3id) {
        try{
            sysService.deleteOrgan3(o3id);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "4-1-1、展示所有职位")
    @PostMapping("/position/queryAllProfession/{page}/{limit}")
    public ResultVo<Object> queryAllProfession(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(sysService.queryAllProfession(page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "4-1-2、通过职称名查询职称信息")
    @PostMapping("/position/queryProfessionByProfession/{profession}/{page}/{limit}")
    public ResultVo<Object> queryProfessionByProfession(@PathVariable("profession") String profession,@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(sysService.queryProfessionByProfession(profession,page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "4-2、新增职位")
    @PostMapping("/position/insertProfession")
    public ResultVo<Object> insertProfession(FileProfession fileProfession) {
        try{
            sysService.insertProfession(fileProfession);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "4-3、修改职称姓名")
    @PostMapping("/position/updateProfessionByPid")
    public ResultVo<Object> updateProfessionByPid(String profession,String pId) {
        try{
            sysService.updateProfessionByPid(profession,pId);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @ApiOperation(value = "4-4、删除职位")
    @PostMapping("/position/deleteProfessionByPid")
    public ResultVo<Object> deleteProfessionByPid(String profession,String pId) {
        try{
            sysService.deleteProfessionByPid(profession,pId);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

}
