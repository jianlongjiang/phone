package com.phone.cn.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.utils.JsonMapper;
 
 


@SuppressWarnings("deprecation")
@Controller
public class PhoneGapAction  {
    private SimpleDateFormat sf = new SimpleDateFormat("yyMMddHHmmss");
     
	@ResponseBody
    @RequestMapping("phoneGap/upFile")
    public Object updatePhoneGrp(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	response.setContentType("text/html;charset=UTF-8");
    	File file1=null;
        DiskFileUpload disFileUpload = new DiskFileUpload();
        List<String> upImags = new ArrayList<String>();
        try{
            List<FileItem> list = disFileUpload.parseRequest(request);
            for(FileItem fileItem:list){
                if(fileItem.isFormField()){
                     
                }else{
//                    if("fieldName".equals(fileItem.getFieldName())){
                    	String fileName = sf.format(new Date())+fileItem.getName();
                    	upImags.add(fileName);
                        File remoteFile = new File(new String(fileName.getBytes(),"UTF-8"));
                        System.out.println("开始遍历.....");
 
                        System.out.println(fileItem.getContentType()+"----"+remoteFile.getName()+fileItem.getName());
 
                        file1 = new File(request.getSession().getServletContext().getRealPath("/html/admin/store"),remoteFile.getName());
                        file1.getParentFile().mkdirs();
                        file1.createNewFile();
                         
                        InputStream ins = fileItem.getInputStream();
                         
                        OutputStream ous = new FileOutputStream(file1);
                        try{
                            byte[] buffer = new byte[1024];
                            int len=0;
                            while((len=ins.read(buffer))>-1){
                                ous.write(buffer, 0, len);
                            }
 
                        }finally{
                            ous.close();
                            ins.close();
                        }
                    }
//                }
            }
        }catch(FileUploadException e){
             e.printStackTrace();
        }
//        String backImages =  StringUtils.join(upImags, ",");
//        Map<String, Object> map = new HashMap<String, Object>();
//        JsonMapper.
        return    JsonMapper.beanToList(upImags);
    }
    
     
}