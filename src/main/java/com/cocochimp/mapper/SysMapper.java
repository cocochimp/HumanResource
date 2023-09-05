package com.cocochimp.mapper;

import com.cocochimp.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SysMapper {

    //0-1、用户注册
    @Insert("insert into sys_user " +
            "values(#{uId},#{username},#{password},#{rId},'0')")
    void insertUser(@Param("uId") String uId,@Param("username") String username,@Param("password") String password,@Param("rId") String rId);

    //0-1、选择用户身份
    @Select("select r_id,role_name " +
            "from sys_role " +
            "where is_deleted='0'")
    List<Map<String,String>> queryAllRole();

    //0-2、用户登录
    @Select("select * " +
            "from sys_user " +
            "where username=#{username} and `password`=#{password} and is_deleted='0'")
    List<Map<String,Object>> login(SysUser sysUser);

    //0-2、用户登录的权限路径
    @Select("select p.p_name title,p.path path " +
            "from sys_user u,sys_role_permission rp,sys_permission p " +
            "where u.username=#{username} and u.`password`=#{password} and u.is_deleted='0' and u.r_id=rp.r_id and rp.p_id=p.p_id and p.higher_pid=#{higherId}")
    List<Map<String,Object>> loginPermissionId(@Param("username") String username,@Param("password") String password,@Param("higherId") String higherId);

    @Select("select p.p_name title,p.path path " +
            "from sys_permission p " +
            "where p.p_id=#{pid}")
    Map<String,Object> loginPermissionInfo(@Param("pid") String pid);

    @Select("select distinct p.higher_pid pid " +
            "from sys_user u,sys_role_permission rp,sys_permission p " +
            "where u.username=#{username} and u.`password`=#{password} and u.is_deleted='0' and u.r_id=rp.r_id and rp.p_id=p.p_id")
    List<Map<String,Object>> loginPermissionChildById(@Param("username") String username,@Param("password") String password);

    //1-1、查询所有用户
    @Select("select u.u_id,u.username,u.`password`,r.role_name " +
            "from sys_user u,sys_role r " +
            "where u.r_id=r.r_id and u.is_deleted='0'" +
            "limit #{page},#{limit}")
    List<Map<String,String>> showAllUser(@Param("page") int page, @Param("limit") int limit);

    //1-1、通过姓名查询用户
    @Select("select u.u_id,u.username,u.`password`,r.role_name\n" +
            "from sys_user u,sys_role r\n" +
            "where u.r_id=r.r_id and u.is_deleted='0' and u.username like concat('%',#{username},'%')\n" +
            "limit #{page},#{limit}")
    List<Map<String,String>> queryUserByName(@Param("username") String username,@Param("page") int page, @Param("limit") int limit);

    //1-1、通过角色身份查询用户(下拉框)
    @Select("select u.u_id,u.username,u.`password`,r.role_name\n" +
            "from sys_user u,sys_role r\n" +
            "where u.r_id=r.r_id and u.is_deleted='0' and r.role_name like concat('%',#{role_name},'%')\n" +
            "limit #{page},#{limit}")
    List<Map<String,String>> queryUserByRole(@Param("role_name") String role_name,@Param("page") int page, @Param("limit") int limit);

    //1-2、删除指定用户(通过u_id)
    @Update("update sys_user " +
            "set is_deleted='1' " +
            "where u_id=#{uid}")
    void deleteUserByUid(@Param("uid") String uid);

    //1-3、修改指定用户的角色
    @Update("update sys_user " +
            "set r_id=#{rId} " +
            "where u_id=#{uId}")
    void updateUserRoleByUid(SysUser sysUser);

    //3-1、查询所有机构
    @Select("select o3.o3_id id,o1.o1_name,o2.o2_name,o3.o3_name\n" +
            "from sys_organ1 o1,sys_organ2 o2,sys_organ3 o3\n" +
            "where o1.o1_id=o2.o1_id and o2.o2_id=o3.o2_id\n" +
            "limit #{page},#{limit}")
    List<Map<String,String>> queryAllOrganization(@Param("page") int page, @Param("limit") int limit);

    //3-1、通过机构名查询机构
    @Select("select o3.o3_id id,o1.o1_name,o2.o2_name,o3.o3_name\n" +
            "from sys_organ1 o1,sys_organ2 o2,sys_organ3 o3\n" +
            "where o1.o1_id=o2.o1_id and o2.o2_id=o3.o2_id \n" +
            "and (o1.o1_name like concat('%',#{organName},'%') or o2.o2_name like concat('%',#{organName},'%') or o3.o3_name like concat('%',#{organName},'%'))\n" +
            "limit #{page},#{limit}")
    List<Map<String,String>> queryOrganizationByName(@Param("organName") String organName,@Param("page") int page, @Param("limit") int limit);

    //3-2、增加三级机构
    @Select("select * " +
            "from sys_organ1")
    List<Map<String,String>> queryOrgan1();

    @Select("select * " +
            "from sys_organ2 " +
            "where o1_id=#{o1id}")
    List<Map<String,String>> queryOrgan2(@Param("o1id") String o1id);

    @Select("select * " +
            "from sys_organ3 " +
            "where o2_id=#{o2id}")
    List<Map<String,String>> queryOrgan3(@Param("o2id") String o2id);

    @Insert("insert into sys_organ1 " +
            "values(#{o1Id},#{o1Name});")
    void insertOrgan1(SysOrgan1 sysOrgan1);

    @Insert("insert into sys_organ2 " +
            "values(#{o2Id},#{o1Id},#{o2Name});")
    void insertOrgan2(SysOrgan2 sysOrgan2);

    @Insert("insert into sys_organ3 " +
            "values(#{o3Id},#{o2Id},#{o3Name})")
    void insertOrgan3(SysOrgan3 sysOrgan3);

    //3-3、删除三级机构
    @Delete("delete from sys_organ3 " +
            "where o3_id=#{o3id}")
    void deleteOrgan3(@Param("o3id") String o3id);

    //4-1、展示所有职位
    @Select("select * \n" +
            "from file_profession\n" +
            "limit #{page},#{limit}")
    List<Map<String,String>> queryAllProfession(@Param("page") int page, @Param("limit") int limit);

    //4-1、通过职称名查询职称信息
    @Select("select * \n" +
            "from file_profession\n" +
            "where profession like concat('%',#{profession},'%')\n" +
            "limit #{page},#{limit}")
    List<Map<String,String>> queryProfessionByProfession(@Param("profession") String profession,@Param("page") int page, @Param("limit") int limit);

    //4-2、新增职位
    @Insert("insert into file_profession " +
            "values(#{pId},#{profession})")
    void insertProfession(FileProfession fileProfession);

    //4-3、修改职称姓名
    @Update("update file_profession " +
            "set profession=#{profession} " +
            "where p_id=#{pId}")
    void updateProfessionByPid(@Param("profession") String profession,@Param("pId") String pId);

    //4-4、删除职位
    @Update("delete from file_profession " +
            "where p_id=#{pId}")
    void deleteProfessionByPid(@Param("profession") String profession,@Param("pId") String pId);

}
