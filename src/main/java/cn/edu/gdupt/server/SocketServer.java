package cn.edu.gdupt.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket通信服务端
 *
 * @author Liang Xiaobin<1490556728@qq.com>
 * @version 2019/11/5
 * @since JDK1.8
 */
public class SocketServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("服务端已经启动,等待客户端连接");
            while (true) {
                Socket socket = serverSocket.accept();
                //根据输入输出流和客户端连接
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String temp = null;
                String info = "";
                while ((temp = bufferedReader.readLine()) != null) {
                    info += temp;
                    System.out.println("已接收到客户端连接");
                    System.out.println("服务端接收到客户端信息:" + info + "当前客户端ip为:");
                }
                //获取一个输出流
                OutputStream outputStream = socket.getOutputStream();
                //将输出流包装
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.print("你好，服务端已经接收到你的信息");
                printWriter.flush();
                //关闭输出流
                socket.shutdownOutput();
                //关闭相对应资源
                printWriter.close();
                outputStream.close();
                bufferedReader.close();
                inputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
