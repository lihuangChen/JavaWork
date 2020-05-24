package face;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	
	
//	String url = "jdbc:mysql://localhost:3306/apple?"+ "useUnicode=true&characterEncoding=utf8";
	String url="jdbc:mysql://localhost:3306/library?characterEncoding=utf-8";
	static String user="root";
	static String password ="123456";
	private Connection con=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private static DBUtil db;
	
	/*
	 * 加载驱动
	 */
	 private DBUtil() {
		 try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	
	
	/*
	 * 连接方法
	 */
	public Connection getCon() {
		try {
			con=DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	/*
	 * 查询方法
	 */
	public ResultSet query(String sql,Object ...obj) {
		// Object  ...obj  obj代表参数数组（0~n的数组）
		con=getCon();
		try {
			ps =con.prepareStatement(sql);
			//添加参数
			int num=0;
			while(num<obj.length) {
				ps.setObject(num+1, obj[num++]);
			}
			//获取结果集
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	/*
	 * 修改方法
	 */
	public boolean modify(String sql1,Object ...obj) {
		con=getCon();
		int num = 0;
		try {
			ps =con.prepareStatement(sql1);
			
			int i=0;
			while(i<obj.length) {
				ps.setObject(i+1, obj[i++]);
			}
			num=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag=false;
//		if(num>0) {
//			flag=true;
//		}
		return num>0? true:false;
		
	}
	
	     /*
		 * 单例模式
		 */
		public static DBUtil newInstance() {
			if(db==null) {
				db=new DBUtil();
			}
			return db;
			
		}
	
	
	
	/*
	 * 关闭连接
	 */
	
	public void close() {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
