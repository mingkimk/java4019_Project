package DB;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Manager.ManagementDAO;
import Server.ServerCenter;
import Server.ServerChat;

public class DAOCenter implements DAOInterface {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private String[] check;
//	private MemberDAO memDAO = null;
//	private ManagementDAO mgmtDAO = null;
//	private CartDAO cartDAO = null;
	private static DAOCenter DAOcenter;
	private static MemberDAO dao = MemberDAO.getInstance();
	private static ManagementDAO mdao=ManagementDAO.getInstance();
	private String notice = "";
	private ServerCenter sc = null;
	private String msg = "";
	private ServerChat chat = null;
	private Socket orderClient=null;
	private int bl = 0;

	private DAOCenter() {

	}

	public static DAOCenter getInstance() {
		if (DAOcenter == null) {
			DAOcenter = new DAOCenter();
		}
		return DAOcenter;
	}

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Class load fail :" + e.getMessage());
		}
	}

	private void connect() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle", "system", "11111111");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Class load fail : " + e.getMessage());
		}
	}

	public void whichone(Object objectMember, ServerChat chat,Socket orderClient) {
		this.chat = chat;
		check = (String[]) objectMember;
		for (int i = 0; i < check.length; i++) {
			notice = check[check.length - 1];
			if (notice.equals("login")) { // 로그인 체크
				System.out.println("이건 로그인 : " + check[check.length - 1]);
				Select(check[0], check[1], notice, chat);
				break;
			} else if (notice.equals("join")) { // 회원가입 체크
				System.out.println("이건회원가입 : " + check[check.length - 1]);
				Insert(objectMember, notice, chat);
				break;
			} else if (notice.equals("check")) { // 중복 체크
				System.out.println("이건중복체크지롱  : " + check[check.length - 1]);
				Select(check[0], check[1], notice, chat);
				break;
			}else if (notice.equals("add")) {// 주문
				Insert(objectMember, notice, chat,orderClient);
				break;
			}

		}

	}


	@Override
	public Boolean Insert(Object obj, String notice, ServerChat chat,Socket orderClient) {//
		switch (notice) {

		case "join":
			boolean bl = dao.InsertMember(obj);
			if (bl == true) {
				msg = "member/yes";
			} else {
				msg = "member/no";
			}
			System.out.println("DAO에서 메세지 확인 : " + msg);
			chat.send(msg);
			// sc.goSC(msg);
			break;
		case "add":
			boolean bl = mdao.Insert(obj);
			if (bl == true) {
				msg = "member/yes";
			} else {
				msg = "member/no";
			}
			System.out.println("DAO에서 메세지 확인 : " + msg);
			chat.send(msg);
			// sc.goSC(msg);
			break;
		case "cart":
			break;
		case "order":
			break;
		}
		return null;
	}

	@Override
	public int Select(String id, String pwd, String notice, ServerChat chat) {

		switch (notice) {

		case "login": // 로그인용
			bl = dao.loginchk(id, pwd);
			if (bl == 1) {
				msg = "login/yes/1";
			} else if (bl == 5) {
				msg = "login/yes/5";
			} else if (bl == 10) {
				msg = "login/no";
			}
			System.out.println("DAO에서 메세지 확인 : " + msg);
			chat.send(msg);
			// sc.goSC(msg);
			break;
		case "check":
			bl = dao.idchk(id); // 중복확인용
			if (bl == 1) {
				msg = "check/no";
			} else if (bl == 5) {
				msg = "check/yes";
			}
			System.out.println("DAO에서 메세지 확인 : " + msg);
			chat.send(msg);
			break;
		}
		return 300;
	}

	@Override
	public Boolean Edit(Object DTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Delete(Object DTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
