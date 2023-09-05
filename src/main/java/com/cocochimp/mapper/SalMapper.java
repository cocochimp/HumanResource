package com.cocochimp.mapper;

import com.cocochimp.entity.SalInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SalMapper {

    //1-1、薪酬登记
    @Insert("insert into sal_info " +
            "values (#{sId},#{uId},#{o3Id},#{fName},#{allmoney},#{basic},#{transport},#{lunch},#{communication},#{pension},#{unemployment},#{medical},#{homemoney},#{review},#{createTime},'0','0','0','0')")
    void insertInfo(SalInfo salInfo);

    //1-1、薪酬登记【查询"薪酬标准名称"（下拉框展示）】
    @Select("select o3_id,o.o3_name " +
            "from sys_organ3 o")
    List<Map<String,String>> queryXinZiBiaoZhun();

    //1-2、展示薪资申请的信息
    @Select("select s.s_id,s.f_name,o.o3_name,u.username,s.allmoney,s.create_time\n" +
            "from sal_info s,sys_organ3 o,sys_user u\n" +
            "where s.u_id=u.u_id and s.o3_id=o.o3_id and s.is_check='0' and s.is_deleted='0' and s.is_give='0' " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showSalApplication(@Param("page") int page, @Param("limit") int limit);

    //1-2、通过is_reject判断数据状态
    @Select("select s.is_reject\n" +
            "from sal_info s,sys_organ3 o,sys_user u\n" +
            "where s.u_id=u.u_id and s.o3_id=o.o3_id and s.is_check='0' and s.is_deleted='0' and s.is_give='0' " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showStatus(@Param("page") int page, @Param("limit") int limit);

    //点击展示信息(1-3、2-2、3-2)
    @Select("select o.o3_name,u.username,s.*\n" +
            "from sal_info s,sys_organ3 o,sys_user u\n" +
            "where s.u_id=u.u_id and s.o3_id=o.o3_id and s_id=#{sid}\n ")
    List<Map<String,String>> showSalInfoBySid(@Param("sid") String sid);

    //1-3、修改内容(先删后加)
    @Delete("delete from sal_info " +
            "where s_id=#{sid}")
    void deleteInfoBySid(@Param("sid") String sid);

    @Insert("insert into sal_info " +
           "values (#{sId},#{uId},#{o3Id},#{fName},#{allmoney},#{basic},#{transport},#{lunch},#{communication},#{pension},#{unemployment},#{medical},#{homemoney},#{review},#{createTime},'0','0','0','0')")
    void insertInfoBySid(SalInfo salInfo);

    //1-4、删除内容
    @Update("update sal_info " +
            "set is_deleted='1' " +
            "where s_id=#{sid}")
    void updateInfoBySid(@Param("sid") String sid);

    //2-1、薪酬复核:展示所有信息
    @Select("select s.s_id,s.f_name,o.o3_name,u.username,s.allmoney,s.create_time\n" +
            "from sal_info s,sys_organ3 o,sys_user u\n" +
            "where s.u_id=u.u_id and s.o3_id=o.o3_id and s.is_check='0' and s.is_deleted='0' and s.is_reject='0' and s.is_give='0' " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showSalRechecked(@Param("page") int page, @Param("limit") int limit);

    //2-2、点击复核展示信息
    //见上面——>点击展示信息(1-3、2-2、3-2)【List<Map<String,String>> showSalInfoBySid(@Param("sid") String Sid);】

    //2-3、复核通过
    @Update("update sal_info " +
            "set is_check='1' " +
            "where s_id=#{sid}")
    void updateCheckInfoBySid(@Param("sid") String sid);

    //2-4、复核失败(驳回)
    @Update("update sal_info " +
            "set is_reject='1' " +
            "where s_id=#{sid}")
    void updateCheckFalseInfoBySid(@Param("sid") String sid);

    //3-1、薪酬查询:查询“档案查询”的所有信息
    @Select("select s.s_id,s.f_name,o.o3_name,u.username,s.allmoney,s.create_time\n" +
            "from sal_info s,sys_organ3 o,sys_user u\n" +
            "where s.u_id=u.u_id and s.o3_id=o.o3_id and s.is_deleted='0' and s.is_reject='0' and s.is_check='1' and s.is_give='0' " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showSalSelect(@Param("page") int page, @Param("limit") int limit);

    //3-2、点击复核展示信息
    //见上面——>点击展示信息(1-3、2-2、3-2)【List<Map<String,String>> showSalInfoBySid(@Param("sid") String Sid);】

    //3-3、通过"薪资标准名称"查询
    @Select("select s.*\n" +
            "from(\n" +
            "select s.s_id,s.f_name,o.o3_name,u.username,s.allmoney,s.create_time\n" +
            "from sal_info s,sys_organ3 o,sys_user u\n" +
            "where s.u_id=u.u_id and s.o3_id=o.o3_id and s.is_deleted='0' and s.is_reject='0' and s.is_check='1' and s.is_give='0' ) s\n" +
            "where s.o3_name like concat('%',#{info},'%') " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showSalSelectByXinZiBiaoZhun(@Param("page") int page, @Param("limit") int limit,@Param("info") String info);

    //3-3、通过"登记人"查询
    @Select("select s.*\n" +
            "from(\n" +
            "select s.s_id,s.f_name,o.o3_name,u.username,s.allmoney,s.create_time\n" +
            "from sal_info s,sys_organ3 o,sys_user u\n" +
            "where s.u_id=u.u_id and s.o3_id=o.o3_id and s.is_deleted='0' and s.is_reject='0' and s.is_check='1' and s.is_give='0' ) s\n" +
            "where s.username like concat('%',#{info},'%') " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showSalSelectByDengJiRen(@Param("page") int page, @Param("limit") int limit,@Param("info") String info);

    //3-3、通过"薪资总额"查询
    @Select("select s.*\n" +
            "from(\n" +
            "select s.s_id,s.f_name,o.o3_name,u.username,s.allmoney,s.create_time\n" +
            "from sal_info s,sys_organ3 o,sys_user u\n" +
            "where s.u_id=u.u_id and s.o3_id=o.o3_id and s.is_deleted='0' and s.is_reject='0' and s.is_check='1' and s.is_give='0') s\n" +
            "where s.allmoney between #{lowMoney} and #{highMoney} " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showSalSelectByAllMoney(@Param("page") int page, @Param("limit") int limit,@Param("lowMoney") int lowMoney,@Param("highMoney") int highMoney);

    //4-1、薪酬发放:薪酬发放登记页面
    @Select("select s.s_id,s.o3_id,o1.o1_name,o2.o2_name,o3.o3_name,count(*) count,sum(s.allmoney) money\n" +
            "from sal_info s,sys_organ3 o3,sys_organ2 o2,sys_organ1 o1\n" +
            "where o3.o2_id=o2.o2_id and o2.o1_id=o1.o1_id and s.o3_id=o3.o3_id and s.is_check='1' and is_give='0'\n" +
            "group by s.o3_id  " +
            "limit #{page},#{limit}")
    List<Map<String,String>> giveSalInfo(@Param("page") int page, @Param("limit") int limit);

    //4-2、薪酬发放登记细则:展示所有数据
    @Select("select *\n" +
            "from sal_info\n" +
            "where o3_id=#{o3id} " +
            "limit #{page},#{limit}")
    List<Map<String,String>> giveSalInfoByO3Id(@Param("page") int page, @Param("limit") int limit,@Param("o3id") String o3id);

    //4-2、薪酬发放登记细则:提交所有数据
    @Update("update sal_info\n" +
            "set is_give='1'\n" +
            "where o3_id=#{o3id}")
    void updateSalGiveInfoByO3Id(@Param("o3id") String o3id);

    //4-2、薪酬发放登记细则:发放成功的所有数据
    @Select("select s.s_id,s.f_name,o.o3_name,u.username,s.allmoney,s.create_time\n" +
            "from sal_info s,sys_organ3 o,sys_user u\n" +
            "where s.u_id=u.u_id and s.o3_id=o.o3_id and s.is_give='1' " +
            "limit #{page},#{limit}")
    List<Map<String,String>> giveSalAllInfo(@Param("page") int page, @Param("limit") int limit);


}
