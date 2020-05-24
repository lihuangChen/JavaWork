package face;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import dao.BookAdmin;
import dao.OBookDao;
import dao.OuserDao;
import dao.UserDao;
import model.User;
import model.Ouser;
public class Main {
	static Scanner sc = new Scanner(System.in);
	static Ouser ou=null;
	public static void main(String[] args) {
		System.out.println("欢迎来到图书馆！");
		while (true) {
			System.out.println("1、管理员模块    2、普通用户模块");
			int x = sc.nextInt();
			if (x == 1) {
				System.out.println("1、用户管理   2、书籍管理   3、退出");
				int x1 = sc.nextInt();

				if (x1 == 1) {
					user();

				} else if (x1 == 2) {
					bookAdmin();
				}
				if (x1 != 1 || x1 != 2) {

				}

			}else if(x==2){
				OBookDao ob=new OBookDao();
				System.out.println("1、个人信息查询  2、书籍查询  3、书籍借阅  4、书籍归还  5、退出");
				int x1=sc.nextInt();
				switch (x1) {
				case 1:
					Ouser();
					break;
				case 2:
					try {
						ob.bookSelect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						try {
							ob.borrow(ou);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 4:
					try {
						ob.back(ou);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				default:
					break;
				}
			}

		}
	}

	public static void user() {
		boolean f = true;
		User u = null;
		while (f) {
			UserDao userdao = new UserDao();

			System.out.println("1、管理员登录   2、管理员添加   3、普通用户列表查询   4、用户账户禁用与开启  5、退出");
			int x2 = sc.nextInt();
			switch (x2) {
			case 1:
				try {
					u = userdao.login();
					if (u.getIdentity() == 0) {
						System.out.println("当前为管理员！");
					} else if (u.getIdentity() == 1) {
						System.out.println("当前为普通用户状态！");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					userdao.addUser();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					if (u != null) {
						userdao.select();
					} else {
						System.out.println("当前未登录！");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					if (u != null) {
						userdao.modityState();
					} else {
						System.out.println("当前未登录！");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:
				f = false;
			default:
				break;
			}

		}
	}

	public static void bookAdmin() {
		boolean f = true;
		while(f) {
			BookAdmin book=new BookAdmin();
			System.out.println("1、书籍添加  2、书籍删除  3、书籍修改  4、书籍列表查询  5、退出");
			int x=sc.nextInt();
			switch (x) {
			case 1:
				try {
					book.bookAdd();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					book.bookDelete();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					book.bookModify();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					book.bookSelect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:
				f=false;
			default:
				break;
			}
		}
	}
	public static void Ouser() {
		OuserDao o=new OuserDao(); 
		boolean f=true;
		while(f) {
			
			System.out.println("1、用户注册  2、用户登录  3、个人信息查询  4、个人信息修改  5、密码修改  6、退出");
			int x=sc.nextInt();
			switch (x) {
			case 1:
				try {
					o.addUser();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					ou=o.login();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					o.select(ou);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					o.modityState(ou);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:
				try {
					o.modityPwd(ou);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 6:
				f=false;
			default:
				break;
			}
		}
	}

}
