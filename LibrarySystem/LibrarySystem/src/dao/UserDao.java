package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import face.DBUtil;
import model.User;

public class UserDao {
	static DBUtil db=DBUtil.newInstance();
	static Connection con=db.getCon();
	static Scanner sc= new Scanner(System.in);
	public User login() throws SQLException {
		User resultUser=new User();
		System.out.println("�������û���������");
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
			System.out.println("��¼�ɹ���");
		}else {
			System.out.println("�˺��������");
		}
		return resultUser;
	}
	public void addUser() throws SQLException {
		String name;
		while (true) {
			System.out.println("��������ӵ�����");
			name = sc.next();
			
			String sql1="select * from user where user_name=?";
			PreparedStatement pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("���û����Ѵ�������������");
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
		System.out.println("������������Ϣ");
		String sql3="INSERT INTO `user` VALUES("+id+",'"+name+"','"+sc.next()+"',"+sc.nextInt()+",'"+sc.next()+"','"+sc.next()+"','"+sc.next()+"+',"+sc.nextInt()+",0)";
		PreparedStatement pstmt3=con.prepareStatement(sql3);
		int rs3=pstmt3.executeUpdate();
		if(rs3==1) {
			System.out.println("��ӳɹ�");
		}else {
			System.out.println("���ʧ��");
		}
		
	}
	
	public void select() throws SQLException {
		String sql1="select * from user where identity=?";
		PreparedStatement pstmt = con.prepareStatement(sql1);
		pstmt.setInt(1, 1);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt("user_id")+"  "+rs.getString("user_name")+"  "+rs.getInt("age")+"  "+rs.getString("gender")+"  "+rs.getString("phone_no")+"  "+rs.getString("address"));
			
		}
	}
	public void modityState() throws SQLException {
		System.out.println("������Ҫ�޸ĵ��û���");
		String name =sc.next();
		String sql1="select * from user where user_name=?";
		PreparedStatement pstmt = con.prepareStatement(sql1);
		pstmt.setString(1,name);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			if(rs.getInt("identity")==0) {
				System.out.println("���û�Ϊ����Ա��Ȩ�޲���");
				break;
			}else {
				System.out.println("������1�����ã�����0��������");
				int x=sc.nextInt();
				String sql2="update user set user_state = "+x+" where user_name=?";
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1,name);
				int rs2 = pstmt2.executeUpdate();
				if(rs2==1) {
					System.out.println("�޸ĳɹ�");
				}else {
					System.out.println("�޸�ʧ��");
				}
			}
			
		}
	}
}
