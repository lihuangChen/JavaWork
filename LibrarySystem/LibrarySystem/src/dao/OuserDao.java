package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import face.DBUtil;
import model.Ouser;
import model.User;

public class OuserDao {
	static DBUtil db=DBUtil.newInstance();
	static Connection con=db.getCon();
	static Scanner sc= new Scanner(System.in);
	public void addUser() throws SQLException {
		String name;
		while (true) {
			System.out.println("请输入添加的姓名");
			name = sc.next();
			
			String sql1="select * from user where user_name=?";
			PreparedStatement pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("该用户名已存在请重新输入");
			} else {
				break;
			}
		}
		int id=0;
		String sql2="SElECT user_id FROM user ORDER BY user_id DESC LIMIT 1";
		PreparedStatement pstmt2 = con.prepareStatement(sql2);
		ResultSet rs2 = pstmt2.executeQuery();
		while(rs2.next()) {
			id=rs2.getInt("user_id")+1;
		}
		System.out.println("请输入其他信息");
		String sql3="INSERT INTO `user` VALUES("+id+",'"+name+"','"+sc.next()+"',"+sc.nextInt()+",'"+sc.next()+"','"+sc.next()+"','"+sc.next()+"+',"+sc.nextInt()+",1)";
		PreparedStatement pstmt3=con.prepareStatement(sql3);
		int rs3=pstmt3.executeUpdate();
		if(rs3==1) {
			System.out.println("添加成功");
		}else {
			System.out.println("添加失败");
		}
		
	}
	public Ouser login() throws SQLException {
		Ouser resultUser=new Ouser();
		System.out.println("请输入用户名和密码");
		String name=sc.next();
		String paw=sc.next();
		String sql1="select * from user where user_name=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql1);
		pstmt.setString(1,name);
		pstmt.setString(2,paw);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			resultUser.setUser_id(rs.getInt("user_id"));
			resultUser.setUser_name(rs.getString("user_name"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setAge(rs.getInt("age"));
			resultUser.setGender(rs.getString("gender"));
			resultUser.setPhone_no(rs.getString("phone_no"));
			resultUser.setAddress(rs.getString("address"));
			resultUser.setUser_state(rs.getInt("user_state"));
			resultUser.setIdentity(rs.getInt("identity"));
			System.out.println("登录成功！");
		}else {
			System.out.println("账号密码错误！");
		}
		return resultUser;
	}

	public void select(Ouser ou) throws SQLException {
		if (ou != null) {
			String sql1 = "select * from user where user_id=?";
			PreparedStatement pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, ou.getUser_id());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("user_id") + "  " + rs.getString("user_name") + "  " + rs.getInt("age")
						+ "  " + rs.getString("gender") + "  " + rs.getString("phone_no") + "  "
						+ rs.getString("address"));

			}
		}else {
			System.out.println("当前未登录，请先登录");
		}

	}
	public void modityState(Ouser ou) throws SQLException {
		if(ou!=null) {
			System.out.println("1、姓名  2、年龄  3、 性别  4、电话  5、地址  ");
		String s=null;
		int x=sc.nextInt();
		switch (x) {
		case 1:
			s="user_name";
			break;
		case 2:
			s="age";
			break;
		case 3:
			s="gender";
			break;
		case 4:
			s="phone_no";
			break;
		case 5:
			s="address";
			break;
		default:
			break;
		}
		System.out.println("请输入要修改的信息");
		String sql1="update user set "+s+" = '"+sc.next()+"' where user_id=?";
		PreparedStatement pstmt1 = con.prepareStatement(sql1);
		pstmt1.setInt(1,ou.getUser_id());
		int rs1 = pstmt1.executeUpdate();
		if(rs1==1) {
			System.out.println("修改成功");
		}else {
			System.out.println("修改失败");
		}
		}else {
			System.out.println("当前未登录");
		}
		
		
	}
	public void modityPwd(Ouser ou) throws SQLException {
		if(ou!=null) {
			System.out.println("请输入要修改的密码");
		String sql1="update user set password = '"+sc.next()+"' where user_id=?";
		PreparedStatement pstmt1 = con.prepareStatement(sql1);
		pstmt1.setInt(1,ou.getUser_id());
		int rs1 = pstmt1.executeUpdate();
		if(rs1==1) {
			System.out.println("修改成功");
		}else {
			System.out.println("修改失败");
		}
		}else {
			System.out.println("当前未登录");
		}
		
		
	}
}
