package com.pingalax.ext.multipartfile.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author zhouxiaotao
 * @Description: 上传文件结果
 * @date 2023-09-20 11:10
 */
@Data
@ApiModel("上传文件结果")
public class UploadFileResult {
    private String path;
}
