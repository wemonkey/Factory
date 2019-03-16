package com.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.po.Users;

public class UsersDAO {
	public static final String driver = "com.mysql.jdbc.Driver";
	//定义数据库地址
	public static final String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false"; //数据库名
	public static final String DB_username = "root";
	public static final String DB_password = "000000"; //改成自己的root密码
	public String usernum = "";//工号
	public String password = "";//密码
	public String username ="";//姓名
	public String sex = "";//性别
	public String diplomas = "" ;//学历
	public String position =  "";//职位
	public String salary = "";//月薪
	public String birthday = "";//出生年月
	public String workday = "";//入职日期
	public Date birthday_Date = null;//出生年月
	public Date workday_Date = null;//入职日期

	Connection conn = null;
	PreparedStatement psmt=null;
	Statement statement = null;
	ResultSet rs=null;

	//构造方法：连接数据库
	public UsersDAO() {	
		try {
			Class.forName(driver);//记载数据库驱动，注册到驱动管理器
			conn = DriverManager.getConnection(url, DB_username, DB_password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (conn != null) {
			System.out.println("数据库连接成功！！！");
		} else {
			System.out.println("数据库连接失败！！！");
		}
	}

	//读取数据库中所有的员工的信息：
	public List ReadStaffList(){
		List<Users> list =new ArrayList<Users>();//建立存储student信息的列表	
		try {
			String sql="select * from staff";
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			/*
			 *  +  +  +  +  +  +  +  + 
			 */
			while(rs.next())
			{
				String usernum=rs.getString("工号");//工号
				String password=rs.getString("密码");//密码
				String username=rs.getString("姓名");//姓名
				String sex=rs.getString("性别");//性别
				String diplomas=rs.getString("学历");//学历
				String position=rs.getString("职位");//职位
				String salary=rs.getString("月薪");//月薪
				Date birthday=rs.getDate("出生年月");//出生年月
				Date workday=rs.getDate("入职日期");//入职日期
				//创建Users对象u,存储单个学生信息
				Users u=new Users(usernum,password,username,sex,diplomas,position,salary,birthday,workday);
				list.add(u);//添加Users对象u到list
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
	//创建数据库staff表：
	//定义数据库连接对象
	public void CreateStaff() throws Exception{
		//连接数据库建表,用于保存登录的用户名与密码
		statement = conn.createStatement();
		String sql=  "CREATE TABLE IF NOT EXISTS staff("
				+"工号  varchar(20) NOT NULL,"
				+"密码  varchar(30) NOT NULL,"
				+"姓名  varchar(30) DEFAULT NULL,"
				+"性别  varchar(30) DEFAULT NULL,"
				+"学历  varchar(30) DEFAULT NULL,"
				+"职位  varchar(30) DEFAULT NULL,"
				+"月薪  varchar(30) DEFAULT NULL,"
				+"出生年月  date DEFAULT NULL,"
				+"入职日期  date DEFAULT NULL,"
				+"PRIMARY KEY (工号)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8;";		    		   
		statement.executeUpdate(sql);
		statement.close();

	}
	//DeleteStaff:
	//删除指定工号的员工：
	public boolean DeleteStaff(Users user) {
		String Del_usernum = user.getUsernum();
		String sql = "delete from staff where 工号 =?";//该id所在行的所有数据
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


	//doAdd：
	//添加员工：
	public boolean AddStaff(Users user) throws Exception{
		usernum = user.getUsernum();//工号
		password = user.getPassword();//密码
		username = user.getUsername();//姓名
		sex = user.getSex();//性别
		diplomas = user.getDiplomas() ;//学历
		position =  user.getPosition();//职位
		salary = user.getSalary();//月薪
		birthday_Date = user.getBirthday();//出生年月
		workday_Date = user.getWorkday();//入职日期
		String sql = "INSERT INTO staff(工号,密码,姓名,性别,学历,职位,月薪,出生年月,入职日期) VALUES(?,?,?,?,?,?,?,?,?)";//插入数据
		String sql_sure = "SELECT * FROM  staff WHERE 工号='" + usernum + "'"; //查询语句,检查该id是否存在
		statement = conn.createStatement();
		rs = statement.executeQuery(sql_sure);
		if(rs.next()){
			if (rs.getString("工号").equals(usernum)) {
				//存在此工号,返回添加页面并提示
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
				//插入成功,返回主页面
				System.out.println("员工信息添加成功!");
				pstmt.close();
			} else {
				System.out.println("员工信息添加失败!");
			}
		}
		return true;	
	}

	//dologin:
	//判断用户登录是否成功：
	public boolean IsLogined(Users user) throws Exception{

		int result = -1;
		String sql = "SELECT count(*) FROM  staff WHERE 工号=? and 密码=?"; //查询语句
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, user.getUsernum());
		psmt.setString(2, user.getPassword());
		rs = psmt.executeQuery();
		if (rs.next()) {
			result=rs.getInt(1);
		}
		if (result>0) {
			//登陆成功,跳转到主页面,session中的loginUser保存用户工号
			return true;
		} else {
			//密码不能匹配,返回登录界面并提示
			return false;
		}
	}

	//doModify:
	//修改用户信息：
	public boolean Modify(Users user) throws SQLException {
		usernum = user.getUsernum();//工号
		password = user.getPassword();//密码
		username = user.getUsername();//姓名
		sex = user.getSex();//性别
		diplomas = user.getDiplomas() ;//学历
		position =  user.getPosition();//职位
		salary = user.getSalary();//月薪
		birthday = user.getBirthday().toString();//出生年月
		workday = user.getWorkday().toString();//入职日期

		String sql_update = "UPDATE staff SET 密码= '"+password+"',姓名= '"+username
				+"',性别= '"+sex+"',学历= '"+diplomas+"',职位= '"+position+"',月薪= '"+salary
				+"',出生年月= '"+birthday+"',入职日期 = '"+workday+"' where 工号 ='" + usernum + "'";
		statement = conn.createStatement();
		statement.executeUpdate(sql_update);
		return true;
	}

	//Main_Page,SearchStaff和ModifyStaff:
	//根据工号显示一个员工的信息：
	public Users SearchStaff(Users user) throws SQLException {
//		usernum = user.getUsernum();//工号
//		password = user.getPassword();//密码
//		username = user.getUsername();//姓名
//		sex = user.getSex();//性别
//		diplomas = user.getDiplomas() ;//学历
//		position =  user.getPosition();//职位
//		salary = user.getSalary();//月薪
//		birthday_Date = user.getBirthday();//出生年月
//		workday_Date = user.getWorkday();//入职日期
		Users user2 = null;

		String sql = "select * from staff where 工号 = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, user.getUsernum());
		rs=psmt.executeQuery();
		if(rs.next()){
			//读取数据库中该工号所对应的信息,并赋值:
			usernum=rs.getString("工号");
			password=rs.getString("密码");
			username=rs.getString("姓名");
			sex=rs.getString("性别");
			diplomas=rs.getString("学历");
			position=rs.getString("职位");
			salary=rs.getString("月薪");
			birthday_Date=rs.getDate("出生年月");
			workday_Date=rs.getDate("入职日期");
			user2 = new Users(usernum,password,username,sex,diplomas,position,salary,birthday_Date,workday_Date);
		}
		psmt.close();
		conn.close();


		return user2;
	}

	//Register:
	//往数据库中添加员工信息：但是只有工号和密码（初始注册）
	public boolean register(Users user) throws SQLException {
		String T_username="";//查询数据库中是否已经有该用户要注册的工号
		String sql = "INSERT INTO staff(工号,密码,姓名,性别,学历,职位,月薪,出生年月,入职日期) VALUES(?,?,'','男','','','','1990/01/01','2008/01/01')";//插入语句,插入新建的用户名与密码
		String sql_sure = "SELECT * FROM  staff WHERE 工号='" + user.getUsernum() + "'"; //查询语句,判断该工号是否被注册
		statement = conn.createStatement();
		rs = statement.executeQuery(sql_sure);
		if (rs.next()) {
			T_username = rs.getString("工号");
			if (T_username.equals(user.getUsernum())) {
				//存在该工号,返回页面并提示
				return false;
			}
		} else {
			//或者注册成功,录入用户名与密码,页面跳转到登录界面并提示
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsernum());
			pstmt.setString(2, user.getPassword());
			int Ps_flag = pstmt.executeUpdate();
			if (Ps_flag ==1 ) {
				pstmt.close();
				return true;
			} else {
				//存在异常输入时,用于返回注册页面
				return false;
			}
		}
		return true;
	}
}
