package lxqq.rpc.version1.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class RPCClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1",8888);
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                //构建IO
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                //向服务器端发送一条消息
                bw.write(sc.nextLine() + "\n");
                bw.flush();
                //读取服务器返回的消息
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String mess = br.readLine();
                System.out.println("服务器："+mess);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
