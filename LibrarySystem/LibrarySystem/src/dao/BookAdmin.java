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
		System.out.println("����������");
		String bName = sc.next();
		String sql = "SElECT book_id,book_num FROM t_book where book_name=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bName);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			System.out.println("�����Ѿ����ڣ���������ӵ�����");
			int num = sc.nextInt();
			int book_num = rs.getInt("book_num");
			int id = rs.getInt("book_id");
			String sql1 = "update t_book set book_num = " + (num+book_num) + " where book_id =?";
			PreparedStatement pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, id);
			int rs1 = pstmt1.executeUpdate();
			if (rs1>0) {
				System.out.println("��ӳɹ�");
			} else {
				System.out.println("���ʧ��");
			}
		} else {
			String sql2 = "SElECT book_id FROM t_book ORDER BY book_id DESC LIMIT 1";
			int id = 0;
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			ResultSet rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				id = rs2.getInt("book_id") + 1;
			}
			System.out.println("���������ߣ�����������");
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
				System.out.println("��ӳɹ�");
			} else {
				System.out.println("���ʧ��");
			}
		}

	}
	public void bookDelete() throws SQLException {
		System.out.println("������Ҫɾ����id");
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
			System.out.println("ɾ���ɹ�");
			}else {
			System.out.println("ɾ��ʧ��");
			}
		}else {
			System.out.println("���鱻���ģ��޷���ɾ��");
		}
		
	}
	public void bookModify() throws SQLException {
		System.out.println("1���޸�����  2���޸��Ƿ�Ϊ�ɽ���");
		int x=sc.nextInt();
		if(x==1) {
			System.out.println("������Ҫ�޸ĵ�id");
			int id=sc.nextInt();
			System.out.println("������Ҫ�޸ĵ�����");
			int num=sc.nextInt();
			String sql1="update t_book set  book_num="+num+" where book_id=?";
			PreparedStatement ps1=con.prepareStatement(sql1);
			ps1.setInt(1, id);
			int rs1=ps1.executeUpdate();
			if(rs1>0) {
				System.out.println("�޸ĳɹ�");
			}else {
				System.out.println("�޸�ʧ��");
			}
		}else if(x==2) {
			System.out.println("������Ҫ�޸ĵ�id");
			int id=sc.nextInt();
			System.out.println("1���ɽ���  2�����ɽ���");
			int state=sc.nextInt();
			if(state==1) {
				String sql2="update t_book set  book_state="+0+" where book_id=?";
				PreparedStatement ps2=con.prepareStatement(sql2);
				ps2.setInt(1, id);
				int rs2=ps2.executeUpdate();
				if(rs2>0) {
					System.out.println("�޸ĳɹ�");
				}else {
					System.out.println("�޸�ʧ��");
				}
			}else if(state==2) {
				String sql3="update t_book set  book_state="+0+" where book_id=?";
				PreparedStatement ps3=con.prepareStatement(sql3);
				ps3.setInt(1, id);
				int rs3=ps3.executeUpdate();
				if(rs3>0) {
					System.out.println("�޸ĳɹ�");
				}else {
					System.out.println("�޸�ʧ��");
				}
			}
		
		}
	}
	public void bookSelect() throws SQLException {
		System.out.println("1���鼮����2���鼮����  3���鼮����");
		int x = sc.nextInt();
		String sql1 = null;
		if (x == 1) {
			System.out.println("������Ҫ���ҵĹؼ���");
			sql1 = "select *from t_book where book_name like '%" + sc.next() + "%'";
		} else if (x == 2) {
			System.out.println("������Ҫ���ҵĹؼ���");
			sql1 = "select *from t_book where book_author like '%" + sc.next() + "%'";
		}else if(x==3) {
			System.out.println("������Ҫ���ҵĹؼ���");
			sql1="select * from book_category b LEFT JOIN t_book t ON t.book_category=b.category_id WHERE b.category_name LIKE '%"+sc.next()+"%'";
		}
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ResultSet rs1 = ps1.executeQuery();
		while (rs1.next()) {
			System.out.print("������" + rs1.getString("book_name") + "  ���ߣ�" + rs1.getString("book_author") + "  ���:"
					+ rs1.getInt("book_num"));
			int type = rs1.getInt("book_category");
			String sql2 = "SELECT category_name FROM book_category b,t_book t WHERE t.book_category=b.category_id AND t.book_category=?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setInt(1, rs1.getInt("book_category"));
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				System.out.print(" ����:" + rs2.getString("category_name"));
			}
			int state = rs1.getInt("book_state");
			if (state == 0) {
				System.out.println("  ״̬:�ɽ���");
			} else if (state == 1) {
				System.out.println("  ״̬�����ɽ���");
			}
		}

	}
	
}
