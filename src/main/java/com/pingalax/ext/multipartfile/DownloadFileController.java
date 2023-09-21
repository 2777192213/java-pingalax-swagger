package com.pingalax.ext.multipartfile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author zhouxiaotao
 * @Description: 下载文件
 * @date 2023-09-20 11:23
 */
@Api(value = "文件相关", tags = "文件相关")
@RestController
@RequestMapping("/pingalax/ext/multipartFile")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DownloadFileController {
    /**
     * 文件下载
     *
     * @param filepath 文件路径
     * @param response 返回结果
     */
    @ApiOperation("下载文件")
    @PostMapping("/downloadFile")
    public void downloadFile(String filepath, HttpServletResponse response) {
        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(filepath));
            //输出流，通过输出流将文件写回浏览器，在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();
            //定义一个字节数组
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            //throw new RuntimeException()n(e);
            e.printStackTrace();
        }
    }
}
