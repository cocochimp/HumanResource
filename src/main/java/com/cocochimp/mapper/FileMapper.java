package com.cocochimp.mapper;

import com.cocochimp.entity.FileInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service//extends BaseMapper<FileInfo>
public interface FileMapper{

    //1-1、档案登记
    @Insert("insert into file_info " +
            "values (#{fId},#{uId},#{name},#{sex},#{birth},#{age},#{image},#{professionId},#{organizionId},#{nationId},#{academicId},#{religionId},#{email},#{phone},#{qq},#{address},#{personalHistory},#{familyRelationship},#{remarks},#{createTime},'0','0','0')")
    void insertInfo(FileInfo fileInfo);

    //1-1、档案登记【查询"职称"】
    @Select("select * " +
            "from file_profession")
    List<Map<String,String>> queryProfession();

    //1-1、档案登记【查询"民族"】
    @Select("select * " +
            "from file_nation")
    List<Map<String,String>> queryNation();

    //1-1、档案登记【查询"学历"】
    @Select("select * " +
            "from file_academic")
    List<Map<String,String>> queryAcademic();

    //1-1、档案登记【查询"宗教"】
    @Select("select * " +
            "from file_religion")
    List<Map<String,String>> queryReligion();

    //1-2、展示档案申请的信息
    @Select("select f.f_id,f.`name`,f.sex,o1.o1_name,o2.o2_name,o3.o3_name,fp.profession " +
            "from file_info f,sys_organ3 o3,file_profession fp,sys_organ2 o2,sys_organ1 o1 " +
            "where f.organizion_id=o3.o3_id and f.profession_id=fp.p_id and o3.o2_id=o2.o2_id and o2.o1_id=o1.o1_id and f.is_check='0' and is_deleted='0'  " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showFileApplication(@Param("page") int page, @Param("limit") int limit);

    //1-2、通过is_reject判断数据状态
    @Select("select f.is_reject " +
            "from file_info f,sys_organ3 o3,file_profession fp,sys_organ2 o2,sys_organ1 o1 " +
            "where f.organizion_id=o3.o3_id and f.profession_id=fp.p_id and o3.o2_id=o2.o2_id and o2.o1_id=o1.o1_id and f.is_check='0' " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showStatus(@Param("page") int page, @Param("limit") int limit);

    //点击展示信息(1-3、2-2、3-2)
    @Select("select f.*,o1.o1_name,o2.o2_name,o3.o3_name,fp.profession,fn.nation,fa.academic,fr.religion " +
            "from file_info f,sys_organ3 o3,sys_organ2 o2,sys_organ1 o1,file_profession fp,file_nation fn,file_academic fa,file_religion fr " +
            "where o3.o2_id=o2.o2_id and o2.o1_id=o1.o1_id and f.organizion_id=o3.o3_id and f.profession_id=fp.p_id and f.nation_id=n_id and f.academic_id=a_id and f.religion_id=r_id and f.f_id=#{fid} ")
    List<Map<String,String>> showFileInfoByFid(@Param("fid") String fid);

    //1-3、修改内容(先删后加)
    @Delete("delete from file_info " +
            "where f_id=#{fid}")
    void deleteInfoByFid(@Param("fid") String fid);

    @Insert("insert into file_info " +
            "values (#{fId},#{uId},#{name},#{sex},#{birth},#{age},#{image},#{professionId},#{organizionId},#{nationId},#{academicId},#{religionId},#{email},#{phone},#{qq},#{address},#{personalHistory},#{familyRelationship},#{remarks},#{createTime},'0','0','0')")
    void insertInfoByFid(FileInfo fileInfo);

    //1-4、删除内容
    @Update("update file_info " +
            "set is_deleted='1' " +
            "where f_id=#{fid}")
    void updateInfoByFid(@Param("fid") String fid);

    //2-1、档案复核:展示所有信息
    @Select("select f.f_id,f.`name`,f.sex,o1.o1_name,o2.o2_name,o3.o3_name,fp.profession " +
            "from file_info f,sys_organ3 o3,file_profession fp,sys_organ2 o2,sys_organ1 o1 " +
            "where f.organizion_id=o3.o3_id and f.profession_id=fp.p_id and o3.o2_id=o2.o2_id and o2.o1_id=o1.o1_id and f.is_check='0' and f.is_deleted='0' and f.is_reject='0' " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showFileRechecked(@Param("page") int page, @Param("limit") int limit);

    //2-2、点击复核展示信息
    //见上面——>点击展示信息(1-3、2-2、3-2)【List<Map<String,String>> showFileInfoByFid(@Param("fid") String fid);】

    //2-3、复核通过
    @Update("update file_info " +
            "set is_check='1' " +
            "where f_id=#{fid}")
    void updateCheckInfoByFid(@Param("fid") String fid);

    //2-4、复核失败(驳回)
    @Update("update file_info " +
            "set is_reject='1' " +
            "where f_id=#{fid}")
    void updateCheckFalseInfoByFid(@Param("fid") String fid);

    //3-1、查询“档案查询”的所有信息
    @Select("select f.f_id,f.`name`,f.sex,o1.o1_name,o2.o2_name,o3.o3_name,fp.profession " +
            "from file_info f,sys_organ3 o3,file_profession fp,sys_organ2 o2,sys_organ1 o1 " +
            "where f.organizion_id=o3.o3_id and f.profession_id=fp.p_id and o3.o2_id=o2.o2_id and o2.o1_id=o1.o1_id and f.is_check='1' and f.is_deleted='0' and f.is_reject='0' " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showFileSelect(@Param("page") int page, @Param("limit") int limit);

    //3-2、点击复核展示信息
    //见上面——>点击展示信息(1-3、2-2、3-2)【List<Map<String,String>> showFileInfoByFid(@Param("fid") String fid);】

    //3-3、通过一级机构查询
    @Select("select f.* " +
            "from ( " +
            "select f.f_id,f.`name`,f.sex,o1.o1_name,o2.o2_name,o3.o3_name,fp.profession " +
            "from file_info f,sys_organ3 o3,file_profession fp,sys_organ2 o2,sys_organ1 o1 " +
            "where f.organizion_id=o3.o3_id and f.profession_id=fp.p_id and o3.o2_id=o2.o2_id and o2.o1_id=o1.o1_id and f.is_check='1' and f.is_deleted='0' and f.is_reject='0' " +
            ") f " +
            "where f.o1_name like concat('%',#{info},'%') " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showFileSelectByOrgan1(@Param("page") int page, @Param("limit") int limit,@Param("info") String info);

    //3-3、通过二级机构查询
    @Select("select f.* " +
            "from ( " +
            "select f.f_id,f.`name`,f.sex,o1.o1_name,o2.o2_name,o3.o3_name,fp.profession " +
            "from file_info f,sys_organ3 o3,file_profession fp,sys_organ2 o2,sys_organ1 o1 " +
            "where f.organizion_id=o3.o3_id and f.profession_id=fp.p_id and o3.o2_id=o2.o2_id and o2.o1_id=o1.o1_id and f.is_check='1' and f.is_deleted='0' and f.is_reject='0' " +
            ") f " +
            "where f.o2_name like concat('%',#{info},'%') " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showFileSelectByOrgan2(@Param("page") int page, @Param("limit") int limit,@Param("info") String info);

    //3-3、通过三级机构查询
    @Select("select f.* " +
            "from ( " +
            "select f.f_id,f.`name`,f.sex,o1.o1_name,o2.o2_name,o3.o3_name,fp.profession " +
            "from file_info f,sys_organ3 o3,file_profession fp,sys_organ2 o2,sys_organ1 o1 " +
            "where f.organizion_id=o3.o3_id and f.profession_id=fp.p_id and o3.o2_id=o2.o2_id and o2.o1_id=o1.o1_id and f.is_check='1' and f.is_deleted='0' and f.is_reject='0' " +
            ") f " +
            "where f.o3_name like concat('%',#{info},'%') " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showFileSelectByOrgan3(@Param("page") int page, @Param("limit") int limit,@Param("info") String info);

    //3-3、通过职位查询
    @Select("select f.* " +
            "from ( " +
            "select f.f_id,f.`name`,f.sex,o1.o1_name,o2.o2_name,o3.o3_name,fp.profession " +
            "from file_info f,sys_organ3 o3,file_profession fp,sys_organ2 o2,sys_organ1 o1 " +
            "where f.organizion_id=o3.o3_id and f.profession_id=fp.p_id and o3.o2_id=o2.o2_id and o2.o1_id=o1.o1_id and f.is_check='1' and f.is_deleted='0' and f.is_reject='0' " +
            ") f " +
            "where f.profession like concat('%',#{info},'%') " +
            "limit #{page},#{limit}")
    List<Map<String,String>> showFileSelectByProfession(@Param("page") int page, @Param("limit") int limit,@Param("info") String info);



}
