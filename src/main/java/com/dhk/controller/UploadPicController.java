package com.dhk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
public class UploadPicController {
    @RequestMapping(value = "uploadPic")
    @ResponseBody
    public String uploadPic(MultipartFile items_pic)throws Exception{
        String originalFileName = items_pic.getOriginalFilename();
        if(items_pic!=null&&originalFileName!=null&&originalFileName.length()>0){
            String pic_path="F:\\uploadPic\\";
            String newFileName = UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));
            File newFile = new File(pic_path+newFileName);
            items_pic.transferTo(newFile);
        }
        return "success pic";
    }
}
