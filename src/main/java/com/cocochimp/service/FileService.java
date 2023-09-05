package com.cocochimp.service;

import com.cocochimp.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileService{

    //1-1、插入档案数据
    void insertInfo(FileInfo fileInfo);
    //1-1、上传头像图片
    String uploadFileAvatar(MultipartFile file);
    //1-1、查询特定信息
    List<Map<String,String>> queryProfession();
    List<Map<String,String>> queryNation();
    List<Map<String,String>> queryAcademic();
    List<Map<String,String>> queryReligion();

    //1-2、展示档案申请所有的信息
    //1-2、并通过is_reject判断数据状态
    List<Map<String,String>> showFileApplication(int page,int limit);

    //点击展示信息(1-3、2-2、3-2)
    List<Map<String,String>> showFileInfoByFid(String fid);

    //1-3、修改内容(先删后加)
    void updateInfoByFid(String fid,String uid,FileInfo fileInfo,MultipartFile file);

    //1-4、删除内容
    void deleteInfoByFid(String fid);

    //2-1、档案复核:展示所有信息
    List<Map<String,String>> showFileRechecked(int page,int limit);

    //2-2、点击复核展示信息
    //见上面——>点击展示信息(1-3、2-2、3-2)【List<Map<String,String>> showFileInfoByFid(String fid);】

    //2-3、复核通过
    void updateCheckInfoByFid(String fid);

    //2-4、复核失败(驳回)
    void updateCheckFalseInfoByFid(String fid);

    //3-1、查询“档案查询”的所有信息
    List<Map<String,String>> showFileSelect(int page, int limit);

    //3-2、点击复核展示信息
    //见上面——>点击展示信息(1-3、2-2、3-2)【List<Map<String,String>> showFileInfoByFid(String fid);】

    //3-3、通过一级机构查询
    List<Map<String,String>> showFileSelectByOrgan1(int page,int limit,String info);

    //3-3、通过二级机构查询
    List<Map<String,String>> showFileSelectByOrgan2(int page,int limit,String info);

    //3-3、通过三级机构查询
    List<Map<String,String>> showFileSelectByOrgan3(int page,int limit,String info);

    //3-3、通过职位查询
    List<Map<String,String>> showFileSelectByProfession(int page,int limit,String info);


}
