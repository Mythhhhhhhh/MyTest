package cn.myth.webserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) {
        //服务端
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8083);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //阻塞的
        while (true){
            try {
                //请求报文也会在client中
                //如果没有请求到来，程序会一直阻塞在这个地方
                Socket client = serverSocket.accept();
                //新建一个新的线程去处理当前客户端的请求
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //具体的业务逻辑
                        try {
                            //请求报文就在输入流中
                            InputStream inputStream = client.getInputStream();
                            //做出响应，响应到输出流
                            OutputStream outputStream = client.getOutputStream();
                            //请求的资源已经拿到了
                            String requestURI = new Request(inputStream).getRequestURI();
                            System.out.println(requestURI);
                            new Response(outputStream, requestURI).response();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
