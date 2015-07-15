package org.myself.mobile.web.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-25
 * Time: 下午9:18
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     * @param fileName:文件名
     */
    public static List<String> readFileByLines(String fileName) {
        List<String> list = new ArrayList<String>();
        if(fileName!=null&&!"".equals(fileName)){
            File file = new File(fileName);
            BufferedReader reader = null;
            try {
                //System.out.println("以行为单位读取文件内容，一次读一整行：");
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
			 /*一次读入一行，直到读入null为文件结束*/
                while ((tempString = reader.readLine()) != null) {
//                    list.add(tempString);
                    System.out.println(tempString);
                }
            } catch (IOException e) {
                System.out.println("读取文本文件异常");
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                        System.out.println("读取文本文件异常");
                    }
                }
            }
        }
        return list;
    }
    /**
     * 把内容写到文件
     * @param filePathName 文件名
     * @param List<String> 文件内容
     */
    public static boolean writerFile(String filePathName,List<String> content){
        boolean flag=false;
        OutputStreamWriter osw=null;
        try {
            if(filePathName!=null&&!"".equals(filePathName)){
                osw = new OutputStreamWriter(new FileOutputStream(filePathName));
            }
        } catch (FileNotFoundException e1) {
            flag=false;
            e1.printStackTrace();
        }
        if(osw!=null){
            BufferedWriter bw=new BufferedWriter(osw);
            try {
                if(content!=null&&!"".equals(content)){
                    for(String str:content){
                         bw.write(str);
                         bw.newLine();
                    }
                    flag= true;
                }
            } catch (IOException e) {
                flag=false;
                e.printStackTrace();
            }finally{
                try {
                    bw.close();
                    osw.close();
                } catch (IOException e) {
                    flag=false;
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

   // 1.创建文件夹
    public void createDir(String path) throws FileNotFoundException{
        File dir=new File(path);
        if(!dir.exists())
            dir.mkdir();
    }
   // 2.创建新文件
    public void createFile(String path,String filename) throws IOException{
        File file=new File(path+"/"+filename);
        if(!file.exists())
            file.createNewFile();
    }
}
