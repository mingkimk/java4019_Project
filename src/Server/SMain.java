package Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Manager.Setting;

public class SMain {

	public static void main(String[] args) throws Exception {
		/*
		 * ServerSocket����  ���� ��� �ϴٰ� Clientrk ������ �õ��ϸ� Socket�� �����Ͽ�
		 * �ش� �������� �����͸� �ְ� �޽��ϴ�.
		 * 1.ServerSocket�� Ŭ���̾�Ʈ ��Ⱑ���������� ���
		 * 2.Ŭ���̾�Ʈ�� ���� ��û�� �ִٸ� Socket�� �����Ͽ� �����Ŵ
		 * 3.����� Soecket���� �����͸� Stream���·� �ְ����
		 */
		ServerSocket serverS = null;
		ServerSocket serverS1 = null;
		Socket withClient = null;
		Socket orderClient=null; //�ֹ��޼��� ���������� ����
		serverS = new ServerSocket();
		
		serverS.bind(new InetSocketAddress("1.247.118.30", 7777));
		serverS1.bind(new InetSocketAddress("1.247.118.30", 7778));
		ArrayList<Socket> cList = new ArrayList<>();
		ServerCenter sc =new ServerCenter();
		while (true) {
			System.out.println("���� �����");
			withClient = serverS.accept();
			orderClient=serverS1.accept();
			cList.add(withClient);
			System.out.println(cList);
			System.out.println(withClient.getInetAddress() + "���� ������.");
			ServerChat s = new ServerChat(withClient,orderClient, sc);
			s.start();

		}
	}

}
