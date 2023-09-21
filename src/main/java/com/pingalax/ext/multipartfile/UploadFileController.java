package com.pingalax.ext.multipartfile;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.IdUtil;
import com.pingalax.ext.multipartfile.dto.UploadFileResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author zhouxiaotao
 * @Description: 文件上传接口
 * @date 2023-09-20 10:17
 */
@Api(value = "文件相关接口", tags = "文件相关接口")
@RestController
@Slf4j
@RequestMapping("/pingalax/ext/multipartFile")
public class UploadFileController {
    /**
     * 上传文件
     *
     * @param file 文件
     */
    @ApiOperation("上传文件")
    @PostMapping("/uploadFile")
    public UploadFileResult uploadFileResult(@RequestPart("file") MultipartFile file) throws IOException {
        log.info(file.toString());
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //获取上传文件的拓展名
        String fileExt = FileUtil.extName(originalFilename);
        //获取当前类的类路径
        String classPath = ClassUtil.getClassPath();
        //获取指定路径的3级目录
        String rootPath = FileUtil.getParent(classPath, 3);
        //文件路径
        //IdUtil.simpleUUID()生成UUID
        String filePath = "/static/upload/" + DateUtil.format(new Date(), "yyyy/MM/dd/") + IdUtil.simpleUUID() + "/" + originalFilename;
        //创建文件
        File fileObj = FileUtil.file(rootPath + filePath);
        //创建文件目录
        FileUtil.mkdir(fileObj);
        //将临时文件转存的指定位置
        file.transferTo(fileObj);
        //返回保存文件结果
        UploadFileResult uploadFileResult = new UploadFileResult();
        uploadFileResult.setPath(filePath);
        return uploadFileResult;
    }

}
