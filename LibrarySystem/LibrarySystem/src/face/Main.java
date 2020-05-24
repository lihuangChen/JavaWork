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
		System.out.println("��ӭ����ͼ��ݣ�");
		while (true) {
			System.out.println("1������Աģ��    2����ͨ�û�ģ��");
			int x = sc.nextInt();
			if (x == 1) {
				System.out.println("1���û�����   2���鼮����   3���˳�");
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
				System.out.println("1��������Ϣ��ѯ  2���鼮��ѯ  3���鼮����  4���鼮�黹  5���˳�");
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

			System.out.println("1������Ա��¼   2������Ա���   3����ͨ�û��б��ѯ   4���û��˻������뿪��  5���˳�");
			int x2 = sc.nextInt();
			switch (x2) {
			case 1:
				try {
					u = userdao.login();
					if (u.getIdentity() == 0) {
						System.out.println("��ǰΪ����Ա��");
					} else if (u.getIdentity() == 1) {
						System.out.println("��ǰΪ��ͨ�û�״̬��");
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
						System.out.println("��ǰδ��¼��");
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
						System.out.println("��ǰδ��¼��");
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
			System.out.println("1���鼮���  2���鼮ɾ��  3���鼮�޸�  4���鼮�б��ѯ  5���˳�");
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
			
			System.out.println("1���û�ע��  2���û���¼  3��������Ϣ��ѯ  4��������Ϣ�޸�  5�������޸�  6���˳�");
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
