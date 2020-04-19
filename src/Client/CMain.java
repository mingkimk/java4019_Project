package Client;

import java.net.Socket;


public class CMain {

	public static void main(String[] args) throws Exception {
		Socket withServer = null;
		Socket withServer1 = null;
	//	Socket orderServer=null;
		withServer = new Socket("1.247.118.30", 7777);
		withServer = new Socket("1.247.118.30", 7778);
		System.out.println("아이피랑 포트넘버 생성");
		new ClientChat(withServer,withServer1);
	}

}
