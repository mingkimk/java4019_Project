package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import DB.MemberDTO;

public class ManagementDAO {

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	ManagementDTO d = null;
	private static ManagementDAO DAOobj;
	private ManagementDTO goods=null;

	public ManagementDAO() {
	}

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("���� ����");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("���� ����");
		}
	}

	public static ManagementDAO getInstance() {
		if (DAOobj == null) {
			 DAOobj = new ManagementDAO();
		}
		return DAOobj;
	}

	public boolean Insert(Object obj) {
		boolean result = false;
		String[] check = (String[]) oj;
		goods = new ManagementDTO();
		for (int i = 0; i < check.length; i++) {
			goods.setCode(check[0]);
			goods.setCname(check[1]);
			goods.setCnt(check[2]);
			goods.setPrice(check[3]);
		
		}
		if (connect()) {
			try {
				String sql = "insert into goods values(?,?,?,?)";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, goods.getCode());
				psmt.setString(2, goods.getCname());
				psmt.setString(3, goods.getCnt());
				psmt.setString(4, goods.getPrice());
			
				int r = psmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				psmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("DB���� ����");
			System.exit(0);
		}

		return result;
	}

	public boolean editinfo(ManagementDTO m) {
		boolean result = false;
		if (connect()) {
			try {
				String sql = "update goods set code=?,cname=?,cnt=?,price=? where code=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, m.getCode());
				psmt.setString(2, m.getCname());
				psmt.setInt(3, m.getCnt());
				psmt.setInt(4, m.getPrice());
				int r = psmt.executeUpdate();
				System.out.println(d.getCode() + "," + d.getCname() + "," + d.getCnt() + "," + d.getPrice());
				if (r > 0) {
					result = true;
				}
				psmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB �������");
			System.exit(0);
		}
		return result;
	}

	public void delinfo(ManagementDTO d) {
		if (connect()) {
			try {
				String sql = "delete goods where code=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, d.getCode());
				psmt.executeUpdate();
				System.out.println(d.getCode() + "," + d.getCname() + "," + d.getCnt() + "," + d.getPrice());

			} catch (SQLException e) {
				System.out.println("��������");
			}
		}

	}

	public ArrayList<String[]> getList() {
		ArrayList<String[]> list = new ArrayList<String[]>();
		String sql = "SELECT * FROM goods";
		if (connect()) {
			try {
				stmt = conn.createStatement();
				if (stmt != null) {
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						ManagementDTO dto = new ManagementDTO();
						dto.setCode(rs.getInt("code"));
						dto.setCname(rs.getString("cname"));
						dto.setCnt(rs.getInt("cnt"));
						dto.setPrice(rs.getInt("price"));
						list.add(dto.getArray());
						return list;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("DB �������");
			System.exit(0);
		}
		return list;
	}

	
	private boolean connect() {
		boolean result = false;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle", "system", "11111111");
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("???? ????:" + e.getMessage());
		}
		return result;
	}
}
