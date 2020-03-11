package com.cn.springbootutil.common.util.file;

import com.cn.springbootutil.common.config.ApplicationConfig;
import com.cn.springbootutil.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description
 * @author: YZH 图片，文件上传
 * @create: 2020-02-28 16:51
 **/
public class FileUtil {
    @Autowired
    private ApplicationConfig applicationConfig;
    /**
     * 读取路径下的文件的内容
     * @param path
     * @return
     */
    public static  String getContent(String path)  {
        try {
            // String path="C:/Users/LSC/Desktop/新建文本文档 (2).txt";
            StringBuilder str=new StringBuilder();
            File f = new File(path);
            // 判断文件或目录是否存在
            if (f.exists()) {
                if (f.isFile()) {
                    //该缓冲流有一个readLine()独有方法
                    BufferedReader br = new BufferedReader(new FileReader(path));
                    String s = null;
                    //readLine()每次读取一行
                    while ((s = br.readLine()) != null) {
                        str.append(s);
                    }
                    System.out.println(str.toString());
                    return str.toString();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
     return null;
    }
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String renameToUid(String fileName) {
        return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    public Map<String, Object> updatePersonalImg(MultipartFile file, String avatarData) throws Exception {
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUid(fileName);
        //获取图片后缀
        String prefix = fileName.substring((fileName.lastIndexOf(".") + 1));
        String[] str = avatarData.split(",");
        //获取截取的x坐标
        int x = (int) Math.floor(Double.parseDouble(str[0].split(":")[1]));
        //获取截取的y坐标
        int y = (int) Math.floor(Double.parseDouble(str[1].split(":")[1]));
        //获取截取的高度
        int h = (int) Math.floor(Double.parseDouble(str[2].split(":")[1]));
        //获取截取的宽度
        int w = (int) Math.floor(Double.parseDouble(str[3].split(":")[1]));
        //获取旋转的角度
        int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
        try {
            BufferedImage cutImage = ImageUtils.cutImage(file, x, y, w, h, prefix);
            BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            boolean flag = ImageIO.write(rotateImage, prefix, out);
            //转换后存入数据库
            byte[] b = out.toByteArray();
            FileUtil.uploadFile(b, applicationConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            throw new Exception("图片裁剪错误！！");
        }
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    public static void main(String[] args) {
             FileUtil fileUtil=new FileUtil();
            Map<String, Object> result = new HashMap<>();
             String avatarData="";
            MultipartFile file=null;
            try {
                result =fileUtil.updatePersonalImg(file, avatarData);
            } catch (Exception e) {
                System.out.println("更新图像失败！");
            }
            if (result != null && result.size() > 0) {
                 Result.ok();
            } else {
                Result.error("更新图像失败！");
            }

    }
}
