package com.cocochimp.service.impl;

import com.cocochimp.entity.*;
import com.cocochimp.mapper.SysMapper;
import com.cocochimp.service.SysService;
import com.cocochimp.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysServiceImpl implements SysService {

    @Autowired
    SysMapper sysMapper;

    @Override
    public void insertUser(SysUser sysUser) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        String uid=id+"";
        sysMapper.insertUser(uid,sysUser.getUsername(), sysUser.getPassword(), sysUser.getrId());
    }

    @Override
    public List<Map<String, String>> queryAllRole() {
        return sysMapper.queryAllRole();
    }

    @Override
    public List<Map<String, Object>> login(SysUser sysUser) {
        return sysMapper.login(sysUser);
    }

    @Override
    public List<Map<String, Object>> loginPermission(SysUser sysUser) {
        //大集合
        List<Map<String, Object>> mapList = new ArrayList<>();

        //得到权限id
        List<Map<String, Object>> maps = sysMapper.loginPermissionChildById(sysUser.getUsername(),sysUser.getPassword());

        //迭代遍历
        Iterator<Map<String, Object>> iterator = maps.iterator();
        int index=0;
        while (iterator.hasNext()) {
            Map<String, Object> result = iterator.next();

            Map<String,Object> onePath = new HashMap<>();
            List<Map<String, Object>> child = sysMapper.loginPermissionId(sysUser.getUsername(),sysUser.getPassword(),(String) result.get("pid"));
            Map<String, Object> permissionInfo = sysMapper.loginPermissionInfo((String) result.get("pid"));

            onePath.put("path",permissionInfo.get("path"));
            onePath.put("title",permissionInfo.get("title"));
            onePath.put("children",child);

            mapList.add(index++,onePath);
        }
        return mapList;
    }


    @Override
    public List<Map<String, String>> showAllUser(int page,int limit) {
        return sysMapper.showAllUser(page,limit);
    }

    @Override
    public List<Map<String, String>> queryUserByName(String username,int page,int limit) {
        return sysMapper.queryUserByName(username,page,limit);
    }

    @Override
    public List<Map<String, String>> queryUserByRole(String role_name,int page,int limit) {
        return sysMapper.queryUserByRole(role_name,page,limit);
    }

    @Override
    public void deleteUserByUid(String uid) {
        sysMapper.deleteUserByUid(uid);
    }

    @Override
    public void updateUserRoleByUid(SysUser sysUser) {
        sysMapper.updateUserRoleByUid(sysUser);
    }

    @Override
    public List<Map<String, String>> queryAllOrganization(int page,int limit) {
        return sysMapper.queryAllOrganization(page,limit);
    }

    @Override
    public List<Map<String, String>> queryOrganizationByName(String organName,int page,int limit) {
        return sysMapper.queryOrganizationByName(organName,page,limit);
    }

    @Override
    public List<Map<String, String>> queryOrgan1() {
        return sysMapper.queryOrgan1();
    }

    @Override
    public List<Map<String, String>> queryOrgan2(String o1id) {
        return sysMapper.queryOrgan2(o1id);
    }

    @Override
    public List<Map<String, String>> queryOrgan3(String o2id) {
        return sysMapper.queryOrgan3(o2id);
    }

    @Override
    public void insertOrgan1(SysOrgan1 sysOrgan1) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        sysOrgan1.setO1Id(String.valueOf(id));
        sysMapper.insertOrgan1(sysOrgan1);
    }

    @Override
    public void insertOrgan2(SysOrgan2 sysOrgan2) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        sysOrgan2.setO2Id(String.valueOf(id));
        sysMapper.insertOrgan2(sysOrgan2);
    }

    @Override
    public void insertOrgan3(SysOrgan3 sysOrgan3) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        sysOrgan3.setO3Id(String.valueOf(id));
        sysMapper.insertOrgan3(sysOrgan3);
    }

    @Override
    public void deleteOrgan3(String o3id) {
        sysMapper.deleteOrgan3(o3id);
    }

    @Override
    public List<Map<String, String>> queryAllProfession(int page,int limit) {
        return sysMapper.queryAllProfession(page,limit);
    }

    @Override
    public List<Map<String, String>> queryProfessionByProfession(String profession,int page,int limit) {
        return sysMapper.queryProfessionByProfession(profession,page,limit);
    }

    @Override
    public void insertProfession(FileProfession fileProfession) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        fileProfession.setPId(String.valueOf(id));
        sysMapper.insertProfession(fileProfession);
    }

    @Override
    public void updateProfessionByPid(String profession,String pId) {
        sysMapper.updateProfessionByPid(profession, pId);
    }

    @Override
    public void deleteProfessionByPid(String profession,String pId) {
        sysMapper.deleteProfessionByPid(profession,pId);
    }
}
