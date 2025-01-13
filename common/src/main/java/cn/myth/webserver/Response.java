package cn.myth.webserver;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Response {

    private OutputStream outputStream;

    private String requestURI;

    public Response(OutputStream outputStream, String requestURI) {
        this.outputStream = outputStream;
        this.requestURI = requestURI;
    }

    /**
     * 做出响应
     * 思路
     * 1.找到当前文件的IO信息
     * 2.存在则把当前文件的二进制数据写出去
     * 3.不存在，则返回404
     * 如果想让客户端能做出反应，应当按照http协议来
     */
    public void response() {
        StringBuffer stringBuffer = new StringBuffer();
        if (requestURI != null) {
            File file = new File(requestURI);
            //file是否一定存在
            if(file.exists() && !file.isDirectory()) {
                //直接获取文件的信息，写出到输出流
                stringBuffer.append("HTTP/1.1 200 OK \r\n");
                stringBuffer.append("Content-Type:text/html \r\n");
                stringBuffer.append("\r\n");
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                    int length = 0;
                    byte[] bytes = new byte[1024];
                    //输出响应行、响应头、空行
                    outputStream.write(stringBuffer.toString().getBytes("utf-8"));
                    while ((length = fileInputStream.read(bytes)) != -1) {
                        //输出响应体
                        outputStream.write(bytes, 0, length);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        stringBuffer.append("HTTP/1.1 404 Not Found \r\n");
        stringBuffer.append("Content-Type:text/html \r\n");
        stringBuffer.append("\r\n");
        stringBuffer.append("<h1 style='color:red'>File Not Found</h1>");
        try {
            outputStream.write(stringBuffer.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //直接返回404
    }
}
