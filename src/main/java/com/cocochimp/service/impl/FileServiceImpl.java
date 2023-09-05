package com.cocochimp.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.cocochimp.entity.FileInfo;
import com.cocochimp.mapper.FileMapper;
import com.cocochimp.service.FileService;
import com.cocochimp.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;

    @Override
    public void insertInfo(FileInfo fileInfo) {
        //生成订单时间orderData
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        fileInfo.setCreateTime(dateTime.format(formatter));

        //图片上传功能
//        String s = uploadFileAvatar(file);
//        fileInfo.setImage(s);

        //生成id
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        fileInfo.setFId(String.valueOf(id));

        fileMapper.insertInfo(fileInfo);
    }

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //获取上传的文件的原名
        int i = file.getOriginalFilename().lastIndexOf(".");
        String suffix = file.getOriginalFilename().substring(i);

        String uuid = UUID.randomUUID().toString();
        String filename = "secondhandshop" + "/" + uuid + suffix;

        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI5tHpAsCZp1EnFKNXwUk2";
        String accessKeySecret = "YhgCRUroxkTMPlZDl4GmkPu2qT3Grr";
        String bucketName = "cocochimp-markdown-img";
        String objectName = filename;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
        try {
            //file.getInputStream() 当前文件的输入流
            ossClient.putObject(bucketName, objectName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        String url = "https://" + bucketName + "." + endpoint + "/" + filename;

        return url;
    }

    @Override
    public List<Map<String, String>> queryProfession() {
        return fileMapper.queryProfession();
    }

    @Override
    public List<Map<String, String>> queryNation() {
        return fileMapper.queryNation();
    }

    @Override
    public List<Map<String, String>> queryAcademic() {
        return fileMapper.queryAcademic();
    }

    @Override
    public List<Map<String, String>> queryReligion() {
        return fileMapper.queryReligion();
    }

    @Override
    public List<Map<String, String>> showFileApplication(int page, int limit) {

        List<Map<String, String>> maps1 = fileMapper.showFileApplication(page, limit);
        List<Map<String, String>> maps = fileMapper.showStatus(page, limit);

        Iterator<Map<String, String>> iterator = maps.iterator();
        int index=0;
        while (iterator.hasNext()) {
            Map<String, String> result = iterator.next();

            if(result.get("is_reject").equals("0")){
                maps1.get(index).put("is_reject","已申请");
            }else{
                maps1.get(index).put("is_reject","被驳回");
            }
            index++;
        }
        return maps1;
    }


    @Override
    public List<Map<String, String>> showFileInfoByFid(String fid) {
        return fileMapper.showFileInfoByFid(fid);
    }

    @Override
    public void updateInfoByFid(String fid,String uid,FileInfo fileInfo,MultipartFile file) {
        fileMapper.deleteInfoByFid(fid);
        //生成订单时间orderData
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        fileInfo.setCreateTime(dateTime.format(formatter));

        //图片上传功能
        String s = uploadFileAvatar(file);
        fileInfo.setImage(s);

        //生成id
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        fileInfo.setFId(String.valueOf(id));
        fileInfo.setUId(uid);

        fileMapper.insertInfo(fileInfo);
    }

    @Override
    public void deleteInfoByFid(String fid) {
        fileMapper.updateInfoByFid(fid);
    }

    @Override
    public List<Map<String, String>> showFileRechecked(int page, int limit) {
        return fileMapper.showFileRechecked(page,limit);
    }

    @Override
    public void updateCheckInfoByFid(String fid) {
        fileMapper.updateCheckInfoByFid(fid);
    }

    @Override
    public void updateCheckFalseInfoByFid(String fid) {
        fileMapper.updateCheckFalseInfoByFid(fid);
    }

    @Override
    public List<Map<String, String>> showFileSelect(int page, int limit) {
        return fileMapper.showFileSelect(page,limit);
    }

    @Override
    public List<Map<String, String>> showFileSelectByOrgan1(int page, int limit, String info) {
        return fileMapper.showFileSelectByOrgan1(page, limit, info);
    }

    @Override
    public List<Map<String, String>> showFileSelectByOrgan2(int page, int limit, String info) {
        return fileMapper.showFileSelectByOrgan2(page, limit, info);
    }

    @Override
    public List<Map<String, String>> showFileSelectByOrgan3(int page, int limit, String info) {
        return fileMapper.showFileSelectByOrgan3(page, limit, info);
    }

    @Override
    public List<Map<String, String>> showFileSelectByProfession(int page, int limit, String info) {
        return fileMapper.showFileSelectByProfession(page, limit, info);
    }

}
