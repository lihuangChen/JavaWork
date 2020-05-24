package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import face.DBUtil;

public class BookAdmin {
	static DBUtil db = DBUtil.newInstance();
	static Connection con = db.getCon();
	static Scanner sc = new Scanner(System.in);

	public void bookAdd() throws SQLException {
		System.out.println("请输入书名");
		String bName = sc.next();
		String sql = "SElECT book_id,book_num FROM t_book where book_name=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bName);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			System.out.println("此书已经存在，请输入添加的数量");
			int num = sc.nextInt();
			int book_num = rs.getInt("book_num");
			int id = rs.getInt("book_id");
			String sql1 = "update t_book set book_num = " + (num+book_num) + " where book_id =?";
			PreparedStatement pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, id);
			int rs1 = pstmt1.executeUpdate();
			if (rs1>0) {
				System.out.println("添加成功");
			} else {
				System.out.println("添加失败");
			}
		} else {
			String sql2 = "SElECT book_id FROM t_book ORDER BY book_id DESC LIMIT 1";
			int id = 0;
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			ResultSet rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				id = rs2.getInt("book_id") + 1;
			}
			System.out.println("请输入作者，数量，类型");
			String atuthor=sc.next();
			int num=sc.nextInt();
			int type=sc.nextInt();
			int state=0;
			if(num==0) {
				state=1;
			}
			
			String sql3 = "INSERT INTO t_book VALUES('" + id + "','"+bName+"','"+atuthor+"',"+num+","+type+","+state+")";
			PreparedStatement pstmt3 = con.prepareStatement(sql3);
			int rs3 = pstmt3.executeUpdate();
			if (rs3 == 1) {
				System.out.println("添加成功");
			} else {
				System.out.println("添加失败");
			}
		}

	}
	public void bookDelete() throws SQLException {
		System.out.println("请输入要删除的id");
		int id=sc.nextInt();
		String sql="select book_state from t_book where book_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs=pstmt.executeQuery();
		int state=0;
		while(rs.next()) {
			state=rs.getInt("book_state");
		}
		if(state==0) {
			String sql1="delete from t_book where book_id=?";
			PreparedStatement pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, id);
			int rs1 = pstmt1.executeUpdate();
			if(rs1>0) {
			System.out.println("删除成功");
			}else {
			System.out.println("删除失败");
			}
		}else {
			System.out.println("该书被借阅，无法被删除");
		}
		
	}
	public void bookModify() throws SQLException {
		System.out.println("1、修改数量  2、修改是否为可借阅");
		int x=sc.nextInt();
		if(x==1) {
			System.out.println("请输入要修改的id");
			int id=sc.nextInt();
			System.out.println("请输入要修改的数量");
			int num=sc.nextInt();
			String sql1="update t_book set  book_num="+num+" where book_id=?";
			PreparedStatement ps1=con.prepareStatement(sql1);
			ps1.setInt(1, id);
			int rs1=ps1.executeUpdate();
			if(rs1>0) {
				System.out.println("修改成功");
			}else {
				System.out.println("修改失败");
			}
		}else if(x==2) {
			System.out.println("请输入要修改的id");
			int id=sc.nextInt();
			System.out.println("1、可借阅  2、不可借阅");
			int state=sc.nextInt();
			if(state==1) {
				String sql2="update t_book set  book_state="+0+" where book_id=?";
				PreparedStatement ps2=con.prepareStatement(sql2);
				ps2.setInt(1, id);
				int rs2=ps2.executeUpdate();
				if(rs2>0) {
					System.out.println("修改成功");
				}else {
					System.out.println("修改失败");
				}
			}else if(state==2) {
				String sql3="update t_book set  book_state="+0+" where book_id=?";
				PreparedStatement ps3=con.prepareStatement(sql3);
				ps3.setInt(1, id);
				int rs3=ps3.executeUpdate();
				if(rs3>0) {
					System.out.println("修改成功");
				}else {
					System.out.println("修改失败");
				}
			}
		
		}
	}
	public void bookSelect() throws SQLException {
		System.out.println("1、书籍名、2、书籍作者  3、书籍类型");
		int x = sc.nextInt();
		String sql1 = null;
		if (x == 1) {
			System.out.println("请输入要查找的关键字");
			sql1 = "select *from t_book where book_name like '%" + sc.next() + "%'";
		} else if (x == 2) {
			System.out.println("请输入要查找的关键字");
			sql1 = "select *from t_book where book_author like '%" + sc.next() + "%'";
		}else if(x==3) {
			System.out.println("请输入要查找的关键字");
			sql1="select * from book_category b LEFT JOIN t_book t ON t.book_category=b.category_id WHERE b.category_name LIKE '%"+sc.next()+"%'";
		}
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ResultSet rs1 = ps1.executeQuery();
		while (rs1.next()) {
			System.out.print("书名：" + rs1.getString("book_name") + "  作者：" + rs1.getString("book_author") + "  库存:"
					+ rs1.getInt("book_num"));
			int type = rs1.getInt("book_category");
			String sql2 = "SELECT category_name FROM book_category b,t_book t WHERE t.book_category=b.category_id AND t.book_category=?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setInt(1, rs1.getInt("book_category"));
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				System.out.print(" 类型:" + rs2.getString("category_name"));
			}
			int state = rs1.getInt("book_state");
			if (state == 0) {
				System.out.println("  状态:可借阅");
			} else if (state == 1) {
				System.out.println("  状态：不可借阅");
			}
		}

	}
	
}
