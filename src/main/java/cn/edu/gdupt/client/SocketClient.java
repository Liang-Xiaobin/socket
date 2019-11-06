package cn.edu.gdupt.client;

import java.io.*;
import java.net.Socket;

/**
 * socket通信客户端
 *
 * @author Liang Xiaobin<1490556728@qq.com>
 * @version 2019/11/5
 * @since JDK1.8
 */
public class SocketClient {
    public static void main(String[] args) {
        try {
            //创建Socket对象
            Socket socket = new Socket("106.54.211.175", 8888);
            //根据输入输出流和服务端连接
            //获取一个输出流
            OutputStream outputStream = socket.getOutputStream();
            //将输出流包装
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.print("服务端你好,我是balala");
            printWriter.flush();
            //关闭输出流
            socket.shutdownOutput();
            //获取一个输入流，接收服务端信息
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info = "";
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                info += temp;
                System.out.println("客户端接收服务端发送信息:" + info);
            }
            //关闭相对应的资源
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
