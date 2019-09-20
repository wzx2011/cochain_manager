package com.manage.util;

import com.manage.framework.AppConstants;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	 /**s
     * 压缩文件
     * @param srcFilePath 压缩源路径
     * @param destFilePath 压缩目的路径
     */
    public static void compress(String srcFilePath, String destFilePath) {
        //
        File src = new File(srcFilePath);
 
        if (!src.exists()) {
            throw new RuntimeException(srcFilePath + "不存在");
        }
        File zipFile = new File(destFilePath);
 
        try {
 
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            String baseDir = "";
            compressbyType(src, zos, baseDir);
            zos.close();
 
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
 
        }
    }
    /**
     * 按照原路径的类型就行压缩。文件路径直接把文件压缩，
     * @param src
     * @param zos
     * @param baseDir
     */
     private static void compressbyType(File src, ZipOutputStream zos,String baseDir) {
            if (!src.exists()){
                return;
            }
            System.out.println("压缩路径" + baseDir + src.getName());
            //判断文件是否是文件，如果是文件调用compressFile方法,如果是路径，则调用compressDir方法；
            if (src.isFile()) {
                //src是文件，调用此方法
                compressFile(src, zos, baseDir);
                 
            } else if (src.isDirectory()) {
                //src是文件夹，调用此方法
                compressDir(src, zos, baseDir);
 
            }
 
        }
      
        /**
         * 压缩文件
        */
        private static void compressFile(File file, ZipOutputStream zos,String baseDir) {
            if (!file.exists()){
                return;
            }
            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                ZipEntry entry = new ZipEntry(baseDir + file.getName());
                zos.putNextEntry(entry);
                int count;
                byte[] buf = new byte[1024];
                while ((count = bis.read(buf)) != -1) {
                    zos.write(buf, 0, count);
                }
                bis.close();
 
            } catch (Exception e) {
              // TODO: handle exception
 
            }
        }
         
        /**
         * 压缩文件夹
         */
        private static void compressDir(File dir, ZipOutputStream zos,String baseDir) {
            if (!dir.exists()){
                return;
            }
            File[] files = dir.listFiles();
            if(files.length == 0){
                try {
                    zos.putNextEntry(new ZipEntry(baseDir + dir.getName()+File.separator));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            for (File file : files) {
                compressbyType(file, zos, baseDir + dir.getName() + File.separator);
            }
    }
    
        /**
         * 通过指定路径和文件名来获取文件对象，当文件不存在时自动创建
         * @param path
         * @param fileName
         * @return
         * @throws IOException
         */
        private static File getFile(String path, String fileName) throws IOException {
            // 创建文件对象
            File file;
            if (path != null && !path.equals("")){
                file = new File(path, fileName);
            } else{
                file = new File(fileName);
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            // 返回文件
            return file;
        }

        /**
         * 获得指定文件的输出流
         * @param file
         * @return
         * @throws FileNotFoundException
         */
        private static FileOutputStream getFileStream(File file) throws FileNotFoundException {
            return new FileOutputStream(file);
        }

        /**
         * 将多个文件压缩
         * @param fileList 待压缩的文件列表
         * @param zipFileName 压缩文件名
         * @return 返回压缩好的文件
         * @throws IOException
         */
        public static File getZipFile(List<File> fileList, String zipFileName) throws IOException {
            File zipFile = getFile(AppConstants.getValue("targetPath"), zipFileName);
            // 文件输出流
            FileOutputStream outputStream = getFileStream(zipFile);
            // 压缩流
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

            int size = fileList.size();
            // 压缩列表中的文件
            for (int i = 0;i < size;i++) {
                File file = fileList.get(i);
                zipFile(file, zipOutputStream);
            }
            // 关闭压缩流、文件流
            zipOutputStream.close();
            outputStream.close();
            return zipFile;
        }

        /**
         * 将文件数据写入文件压缩流
         * @param file 带压缩文件
         * @param zipOutputStream 压缩文件流
         * @throws IOException
         */
        private static void zipFile(File file, ZipOutputStream zipOutputStream) throws IOException {
            if (file.exists()) {
                if (file.isFile()) {
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    ZipEntry entry = new ZipEntry(file.getName());
                    zipOutputStream.putNextEntry(entry);

                    final int MAX_BYTE = 10 * 1024 * 1024; // 最大流为10MB
                    long streamTotal = 0; // 接收流的容量
                    int streamNum = 0; // 需要分开的流数目
                    int leaveByte = 0; // 文件剩下的字符数
                    byte[] buffer; // byte数据接受文件的数据

                    streamTotal = bis.available(); // 获取流的最大字符数
                    streamNum = (int) Math.floor(streamTotal / MAX_BYTE);
                    leaveByte = (int) (streamTotal % MAX_BYTE);

                    if (streamNum > 0) {
                        for (int i = 0;i < streamNum;i++) {
                            buffer = new byte[MAX_BYTE];
                            bis.read(buffer, 0, MAX_BYTE);
                            zipOutputStream.write(buffer, 0, MAX_BYTE);
                        }
                    }

                    // 写入剩下的流数据
                    buffer = new byte[leaveByte];
                    bis.read(buffer, 0, leaveByte); // 读入流
                    zipOutputStream.write(buffer, 0, leaveByte); // 写入流
                    zipOutputStream.closeEntry(); // 关闭当前的zip entry

                    // 关闭输入流
                    bis.close();
                    fis.close();
                }
            }
        }
}
