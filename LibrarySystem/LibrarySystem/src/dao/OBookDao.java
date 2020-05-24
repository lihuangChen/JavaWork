package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import face.DBUtil;
import model.Ouser;

public class OBookDao {
	static DBUtil db = DBUtil.newInstance();
	static Connection con = db.getCon();
	static Scanner sc = new Scanner(System.in);
	public void bookSelect() throws SQLException{
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
	public void borrow(Ouser ou) throws SQLException, ParseException {
		if(ou==null) {
			System.out.println("当前未登录");
		}else {
		System.out.println("请输入要借阅的书籍号");
		int x=sc.nextInt();
		String sql1="select book_state from t_book where  book_id=? ";
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setInt(1, x);
		ResultSet rs1 = ps1.executeQuery();
		int state=-1;
		while(rs1.next()) {
			state=rs1.getInt("book_state");
		}
		if(state==0) {
			String sql2="update t_book set book_num = book_num-1 where book_id=?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setInt(1, x);
			int rs2 = ps2.executeUpdate();
			String sql3="UPDATE t_book SET book_state =1 WHERE book_id =? AND book_num<=0";
			ps2=con.prepareStatement(sql2);
			ps2.setInt(1, x);
			int rs3=ps2.executeUpdate();
			String sql4="SElECT borrowing_info_id FROM t_borrowing_info ORDER BY borrowing_info_id DESC LIMIT 1";
			int id=0;
			ps2=con.prepareStatement(sql4);
			ResultSet rs4=ps2.executeQuery();
			while (rs4.next()) {
				id=rs4.getInt("borrowing_info_id");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = format.format(new Date());
			Date date = format.parse(time);
			java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			String sql5="INSERT into `t_borrowing_info` VALUES("+(id+1)+","+ou.getUser_id()+","+x+",0,'"+timestamp+"',0)";
			ps2=con.prepareStatement(sql5);
			rs3=ps2.executeUpdate();
			if(rs3>0) {
				System.out.println("借阅成功");
			}else {
				System.out.println("借阅失败");
			}
		}else {
			System.out.println("该书不可借阅");
		}
		}
		
	}

	public void back(Ouser ou) throws SQLException {
		if (ou == null) {
			System.out.println("当前未登录");
		} else {
			System.out.println("1、个人借阅历史  2、书籍归还");
			int x = sc.nextInt();
			if (x == 1) {
				String sql1 = "select * FROM t_borrowing_info WHERE user_id=?  ORDER BY borrowing_time";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setInt(1, ou.getUser_id());
				ResultSet rs1 = ps1.executeQuery();

				while (rs1.next()) {
					Date d = rs1.getDate("borrowing_time");
					String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(d);
					System.out.println("借阅编号:" + rs1.getInt("borrowing_info_id") + "  书籍编号:" + rs1.getInt("book_id")
							+ "  借阅时间:" + dateStr);
				}

			}
			if(x==2) {
				System.out.println("请输入归还的借阅编号");
				int y=sc.nextInt();
				String sql1="select * from t_borrowing_info where borrowing_info_id=?";
				PreparedStatement ps1=con.prepareStatement(sql1);
				ps1.setInt(1, y);
				ResultSet rs1=ps1.executeQuery();
				if(rs1.next()) {
					Date d = rs1.getDate("borrowing_time");
					String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(d);
					String sql2="update t_borrowing_info SET giveback_time='"+dateStr+"' WHERE borrowing_info_id=?";
					PreparedStatement ps2=con.prepareStatement(sql2);
					ps2.setInt(1, y);
					int rs2=ps2.executeUpdate();
					if(rs2!=0) {
						System.out.println("修改成功");
					}
				}else {
					System.out.println("没有次借阅记录");
				}
			}
		}

	}
	
}
