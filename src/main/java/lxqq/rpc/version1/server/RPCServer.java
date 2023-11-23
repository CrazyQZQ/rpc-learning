package lxqq.rpc.version1.server;

import lxqq.rpc.common.entity.User;
import lxqq.rpc.common.service.UserService;
import lxqq.rpc.common.service.impl.UserServiceImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");
            while (true) {
                Socket s = ss.accept();
                System.out.println("客户端:" + s.getInetAddress().getHostAddress() + "已连接到服务器");

                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //读取客户端发送来的消息
                String mess = br.readLine();
                System.out.println("客户端：" + mess);
                User user = userService.getByName(mess);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                bw.write(user + "\n");
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
