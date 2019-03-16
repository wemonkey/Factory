package com.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.po.Users;

public class UsersDAO {
	public static final String driver = "com.mysql.jdbc.Driver";
	//�������ݿ��ַ
	public static final String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false"; //���ݿ���
	public static final String DB_username = "root";
	public static final String DB_password = "000000"; //�ĳ��Լ���root����
	public String usernum = "";//����
	public String password = "";//����
	public String username ="";//����
	public String sex = "";//�Ա�
	public String diplomas = "" ;//ѧ��
	public String position =  "";//ְλ
	public String salary = "";//��н
	public String birthday = "";//��������
	public String workday = "";//��ְ����
	public Date birthday_Date = null;//��������
	public Date workday_Date = null;//��ְ����

	Connection conn = null;
	PreparedStatement psmt=null;
	Statement statement = null;
	ResultSet rs=null;

	//���췽�����������ݿ�
	public UsersDAO() {	
		try {
			Class.forName(driver);//�������ݿ�������ע�ᵽ����������
			conn = DriverManager.getConnection(url, DB_username, DB_password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (conn != null) {
			System.out.println("���ݿ����ӳɹ�������");
		} else {
			System.out.println("���ݿ�����ʧ�ܣ�����");
		}
	}

	//��ȡ���ݿ������е�Ա������Ϣ��
	public List ReadStaffList(){
		List<Users> list =new ArrayList<Users>();//�����洢student��Ϣ���б�	
		try {
			String sql="select * from staff";
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			/*
			 *  +  +  +  +  +  +  +  + 
			 */
			while(rs.next())
			{
				String usernum=rs.getString("����");//����
				String password=rs.getString("����");//����
				String username=rs.getString("����");//����
				String sex=rs.getString("�Ա�");//�Ա�
				String diplomas=rs.getString("ѧ��");//ѧ��
				String position=rs.getString("ְλ");//ְλ
				String salary=rs.getString("��н");//��н
				Date birthday=rs.getDate("��������");//��������
				Date workday=rs.getDate("��ְ����");//��ְ����
				//����Users����u,�洢����ѧ����Ϣ
				Users u=new Users(usernum,password,username,sex,diplomas,position,salary,birthday,workday);
				list.add(u);//���Users����u��list
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			try {
				if(rs!=null)
				{
					rs.close();
				}
				if(psmt!=null)
				{
					psmt.close();
				}
				if(conn!=null)
				{
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	//AddStaff:
	//�������ݿ�staff��
	//�������ݿ����Ӷ���
	public void CreateStaff() throws Exception{
		//�������ݿ⽨��,���ڱ����¼���û���������
		statement = conn.createStatement();
		String sql=  "CREATE TABLE IF NOT EXISTS staff("
				+"����  varchar(20) NOT NULL,"
				+"����  varchar(30) NOT NULL,"
				+"����  varchar(30) DEFAULT NULL,"
				+"�Ա�  varchar(30) DEFAULT NULL,"
				+"ѧ��  varchar(30) DEFAULT NULL,"
				+"ְλ  varchar(30) DEFAULT NULL,"
				+"��н  varchar(30) DEFAULT NULL,"
				+"��������  date DEFAULT NULL,"
				+"��ְ����  date DEFAULT NULL,"
				+"PRIMARY KEY (����)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8;";		    		   
		statement.executeUpdate(sql);
		statement.close();

	}
	//DeleteStaff:
	//ɾ��ָ�����ŵ�Ա����
	public boolean DeleteStaff(Users user) {
		String Del_usernum = user.getUsernum();
		String sql = "delete from staff where ���� =?";//��id�����е���������
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, Del_usernum);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}


	//doAdd��
	//���Ա����
	public boolean AddStaff(Users user) throws Exception{
		usernum = user.getUsernum();//����
		password = user.getPassword();//����
		username = user.getUsername();//����
		sex = user.getSex();//�Ա�
		diplomas = user.getDiplomas() ;//ѧ��
		position =  user.getPosition();//ְλ
		salary = user.getSalary();//��н
		birthday_Date = user.getBirthday();//��������
		workday_Date = user.getWorkday();//��ְ����
		String sql = "INSERT INTO staff(����,����,����,�Ա�,ѧ��,ְλ,��н,��������,��ְ����) VALUES(?,?,?,?,?,?,?,?,?)";//��������
		String sql_sure = "SELECT * FROM  staff WHERE ����='" + usernum + "'"; //��ѯ���,����id�Ƿ����
		statement = conn.createStatement();
		rs = statement.executeQuery(sql_sure);
		if(rs.next()){
			if (rs.getString("����").equals(usernum)) {
				//���ڴ˹���,�������ҳ�沢��ʾ
				return false;		
			}
		}else{
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, usernum);
			pstmt.setString(2, password);
			pstmt.setString(3, username);     
			pstmt.setString(4, sex);
			pstmt.setString(5, diplomas);
			pstmt.setString(6, position);
			pstmt.setString(7, salary);
			pstmt.setDate(8, birthday_Date);
			pstmt.setDate(9, workday_Date);

			int Ps_flag = pstmt.executeUpdate();
			if (Ps_flag ==1 ) {
				//����ɹ�,������ҳ��
				System.out.println("Ա����Ϣ��ӳɹ�!");
				pstmt.close();
			} else {
				System.out.println("Ա����Ϣ���ʧ��!");
			}
		}
		return true;	
	}

	//dologin:
	//�ж��û���¼�Ƿ�ɹ���
	public boolean IsLogined(Users user) throws Exception{

		int result = -1;
		String sql = "SELECT count(*) FROM  staff WHERE ����=? and ����=?"; //��ѯ���
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, user.getUsernum());
		psmt.setString(2, user.getPassword());
		rs = psmt.executeQuery();
		if (rs.next()) {
			result=rs.getInt(1);
		}
		if (result>0) {
			//��½�ɹ�,��ת����ҳ��,session�е�loginUser�����û�����
			return true;
		} else {
			//���벻��ƥ��,���ص�¼���沢��ʾ
			return false;
		}
	}

	//doModify:
	//�޸��û���Ϣ��
	public boolean Modify(Users user) throws SQLException {
		usernum = user.getUsernum();//����
		password = user.getPassword();//����
		username = user.getUsername();//����
		sex = user.getSex();//�Ա�
		diplomas = user.getDiplomas() ;//ѧ��
		position =  user.getPosition();//ְλ
		salary = user.getSalary();//��н
		birthday = user.getBirthday().toString();//��������
		workday = user.getWorkday().toString();//��ְ����

		String sql_update = "UPDATE staff SET ����= '"+password+"',����= '"+username
				+"',�Ա�= '"+sex+"',ѧ��= '"+diplomas+"',ְλ= '"+position+"',��н= '"+salary
				+"',��������= '"+birthday+"',��ְ���� = '"+workday+"' where ���� ='" + usernum + "'";
		statement = conn.createStatement();
		statement.executeUpdate(sql_update);
		return true;
	}

	//Main_Page,SearchStaff��ModifyStaff:
	//���ݹ�����ʾһ��Ա������Ϣ��
	public Users SearchStaff(Users user) throws SQLException {
//		usernum = user.getUsernum();//����
//		password = user.getPassword();//����
//		username = user.getUsername();//����
//		sex = user.getSex();//�Ա�
//		diplomas = user.getDiplomas() ;//ѧ��
//		position =  user.getPosition();//ְλ
//		salary = user.getSalary();//��н
//		birthday_Date = user.getBirthday();//��������
//		workday_Date = user.getWorkday();//��ְ����
		Users user2 = null;

		String sql = "select * from staff where ���� = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, user.getUsernum());
		rs=psmt.executeQuery();
		if(rs.next()){
			//��ȡ���ݿ��иù�������Ӧ����Ϣ,����ֵ:
			usernum=rs.getString("����");
			password=rs.getString("����");
			username=rs.getString("����");
			sex=rs.getString("�Ա�");
			diplomas=rs.getString("ѧ��");
			position=rs.getString("ְλ");
			salary=rs.getString("��н");
			birthday_Date=rs.getDate("��������");
			workday_Date=rs.getDate("��ְ����");
			user2 = new Users(usernum,password,username,sex,diplomas,position,salary,birthday_Date,workday_Date);
		}
		psmt.close();
		conn.close();


		return user2;
	}

	//Register:
	//�����ݿ������Ա����Ϣ������ֻ�й��ź����루��ʼע�ᣩ
	public boolean register(Users user) throws SQLException {
		String T_username="";//��ѯ���ݿ����Ƿ��Ѿ��и��û�Ҫע��Ĺ���
		String sql = "INSERT INTO staff(����,����,����,�Ա�,ѧ��,ְλ,��н,��������,��ְ����) VALUES(?,?,'','��','','','','1990/01/01','2008/01/01')";//�������,�����½����û���������
		String sql_sure = "SELECT * FROM  staff WHERE ����='" + user.getUsernum() + "'"; //��ѯ���,�жϸù����Ƿ�ע��
		statement = conn.createStatement();
		rs = statement.executeQuery(sql_sure);
		if (rs.next()) {
			T_username = rs.getString("����");
			if (T_username.equals(user.getUsernum())) {
				//���ڸù���,����ҳ�沢��ʾ
				return false;
			}
		} else {
			//����ע��ɹ�,¼���û���������,ҳ����ת����¼���沢��ʾ
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsernum());
			pstmt.setString(2, user.getPassword());
			int Ps_flag = pstmt.executeUpdate();
			if (Ps_flag ==1 ) {
				pstmt.close();
				return true;
			} else {
				//�����쳣����ʱ,���ڷ���ע��ҳ��
				return false;
			}
		}
		return true;
	}
}
