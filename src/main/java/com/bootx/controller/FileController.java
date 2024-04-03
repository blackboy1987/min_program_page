package com.bootx.controller;

import com.bootx.common.Result;
import com.bootx.util.DateUtils;
import com.bootx.util.UploadUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController("fileController")
@RequestMapping("/api/file")
public class FileController extends BaseController {

    private final String[] extensions = new String[]{
            "png","jpg","gif"
    };


    @GetMapping("/config")
    public Result config() {
        return Result.success();
    }

    @PostMapping("/upload")
    public Result upload(HttpServletRequest request, MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if(!checkFileType(file.getOriginalFilename())){
            return Result.error("仅允许上传png和jpg图片");
        }

        File tempFile = new File(FileUtils.getTempDirectory(), UUID.randomUUID() + "."+extension);
        file.transferTo(tempFile);
        String path = DateUtils.formatDateToString(new Date(),"yyyy/MM/dd/") + UUID.randomUUID().toString().replace("-","")+"."+extension;

        UploadUtils.upload(tempFile,path);
        String url = UploadUtils.getUrl(path);
        return Result.success(url);
    }

    private boolean checkFileType(String name) {
        String extension = FilenameUtils.getExtension(name);
        return ArrayUtils.contains(extensions,extension);
    }
}
