package cn.zhangjd.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

public class FilesUtil {
    public static String uploadFile(MultipartFile file) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        String fileName = Long.toHexString(System.currentTimeMillis()) + suffix;
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
        //System.out.println("path:"+path.getAbsolutePath());
        File upload = new File(path.getAbsolutePath(), "static/upload/" + simpleDateFormat.format(new Date()));
        if (!upload.exists()) {
            upload.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(new File(upload, fileName));
        out.write(file.getBytes());
        out.flush();
        out.close();
        return "/upload/" + simpleDateFormat.format(new Date()) + "/" + fileName;

    }

    public static boolean DelFile(String path) throws Exception {
        File file=getFile(path);
        file.delete();
        if (file.exists() && file.isFile()) {
            return true;
        }
        return false;
    }
    public static File getFile(String path) throws FileNotFoundException {
        File classPath = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!classPath.exists()) {
            classPath = new File("");
        }
        File file = new File(classPath.getAbsolutePath(), "static/" + path);
        return  file;
    }

    public static List<String> getPath(String text) {
        List<String> paths=new ArrayList<String>();
        List<String> path1=util(text,"<source src=\"");
        List<String> path2=util(text,"<img src=\"");
        List<String> path3=util(text,"<a href=\"");
        paths.addAll(path1);
        paths.addAll(path2);
        paths.addAll(path3);
        return paths;
    }
    private static List<String> util(String text, String str){
        List<String> paths=new ArrayList<String>();
        int form=0,open=0,end=0;
        while (true){
            form=text.indexOf(str,end);
            if (form==-1){break;}
            open=text.indexOf("/upload/",form);
            if (open==-1){break;}
            end=text.indexOf('\"',open);
            if (end==-1){break;}
            paths.add(text.substring(open,end));
        }
        return paths;
    }
}
