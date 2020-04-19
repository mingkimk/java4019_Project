package Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Manager.Setting;

public class SMain {

	public static void main(String[] args) throws Exception {
		/*
		 * ServerSocket으로  연결 대기 하다가 Clientrk 연결을 시도하면 Socket을 생성하여
		 * 해당 소켓으로 데이터를 주고 받습니다.
		 * 1.ServerSocket은 클라이언트 대기가있을때까지 대기
		 * 2.클라이언트가 연결 요청이 있다면 Socket을 생성하여 연결시킴
		 * 3.연결된 Soecket으로 데이터를 Stream형태로 주고받음
		 */
		ServerSocket serverS = null;
		ServerSocket serverS1 = null;
		Socket withClient = null;
		Socket orderClient=null; //주문메세지 들어왔을때용 소켓
		serverS = new ServerSocket();
		
		serverS.bind(new InetSocketAddress("1.247.118.30", 7777));
		serverS1.bind(new InetSocketAddress("1.247.118.30", 7778));
		ArrayList<Socket> cList = new ArrayList<>();
		ServerCenter sc =new ServerCenter();
		while (true) {
			System.out.println("서버 대기중");
			withClient = serverS.accept();
			orderClient=serverS1.accept();
			cList.add(withClient);
			System.out.println(cList);
			System.out.println(withClient.getInetAddress() + "님이 접속함.");
			ServerChat s = new ServerChat(withClient,orderClient, sc);
			s.start();

		}
	}

}
