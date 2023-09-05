package com.cocochimp.service;

import com.cocochimp.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysService {
    //0-1、用户注册
    void insertUser(SysUser sysUser);

    //0-1、选择用户身份
    List<Map<String,String>> queryAllRole();

    //0-2、用户登录信息
    List<Map<String,Object>> login(SysUser sysUser);

    //0-2、用户登录的权限路径
    List<Map<String,Object>> loginPermission(SysUser sysUser);

    //1-1、查询所有用户
    List<Map<String,String>> showAllUser(int page,int limit);

    //1-1、通过姓名查询用户
    List<Map<String,String>> queryUserByName(String username,int page,int limit);

    //1-1、通过角色身份查询用户(下拉框)
    List<Map<String,String>> queryUserByRole(String role_name,int page,int limit);

    //1-2、删除指定用户(通过u_id)
    void deleteUserByUid(String uid);

    //1-3、修改指定用户的角色
    void updateUserRoleByUid(SysUser sysUser);

    //3-1、查询所有机构
    List<Map<String,String>> queryAllOrganization(int page,int limit);

    //3-1、通过机构名查询机构
    List<Map<String,String>> queryOrganizationByName(String organName,int page,int limit);

    //3-2、增加三级机构
    List<Map<String,String>> queryOrgan1();
    List<Map<String,String>> queryOrgan2(String o1id);
    List<Map<String,String>> queryOrgan3(String o2id);
    void insertOrgan1(SysOrgan1 sysOrgan1);
    void insertOrgan2(SysOrgan2 sysOrgan2);
    void insertOrgan3(SysOrgan3 sysOrgan3);

    //3-3、删除三级机构
    void deleteOrgan3(String o3id);

    //4-1、展示所有职位
    List<Map<String,String>> queryAllProfession(int page,int limit);

    //4-1、通过职称名查询职称信息
    List<Map<String,String>> queryProfessionByProfession(String profession,int page,int limit);

    //4-2、新增职位
    void insertProfession(FileProfession fileProfession);

    //4-3、修改职称姓名
    void updateProfessionByPid(String profession,String pId);

    //4-4、删除职位
    void deleteProfessionByPid(String profession,String pId);

}
