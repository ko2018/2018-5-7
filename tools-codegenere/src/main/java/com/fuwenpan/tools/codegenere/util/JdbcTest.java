package com.fuwenpan.tools.codegenere.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.shiro.crypto.hash.SimpleHash;

public class JdbcTest {
	// 连接MySql数据库，用户名和密码都是root
	static String url = "jdbc:mysql://192.168.2.88:3306/drugpurchase";
	static String username = "himofa";
	static String password = "himofaFwp2016*FZ1";

	public static void main(String[] args) {
		try {
			// 加载MySql的驱动类
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url, username,
					password);
			Statement stmt = con.createStatement();
			Statement stmt1 = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM base_area");
			while (rs.next()) {
				int id = rs.getInt("id");
				Timestamp addTime = rs.getTimestamp("addTime");
				int deleteStatus = rs.getInt("deleteStatus");
				String areaName = rs.getString("areaName");
				int level = rs.getInt("level");
				int sequence = rs.getInt("sequence");
				int parent_id = rs.getInt("parent_id");
				int common = rs.getInt("common");

				System.out.println(id + "：" + areaName);

				int rows = stmt1
						.executeUpdate("INSERT INTO area(area_id,add_time,delete_status,area_name,level,sequence,parent_id,common) values("
								+ ""
								+ id
								+ ",'"
								+ addTime
								+ "',"
								+ deleteStatus
								+ ",'"
								+ areaName
								+ "',"
								+ level
								+ ","
								+ sequence
								+ ","
								+ parent_id
								+ ","
								+ common + ")");
				
				System.out.println("AAAAAAAAA: "+rows);

				//break;
			}

			rs.close();
			stmt.close();
			stmt1.close();
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
	}
	
	public String validatePassword(String salt, String pswd) {
		SimpleHash hash = new SimpleHash("MD5", pswd, salt,
				2);
		String encodedPassword = hash.toHex();
		return encodedPassword;
	}
}
