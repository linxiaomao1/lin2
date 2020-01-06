package build;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBUtil implements sqlDao{
	final private String url="jdbc:mysql://localhost:3306/jspdemo?serverTimezone=UTC&useSSL=false";
	final private String root ="root";
	final private String pwd = "123456";
	final private String driver = "com.mysql.cj.jdbc.Driver";
	Connection conn;
	
	public static void main(String[] args) {
		new DBUtil();
	}
	
	public DBUtil() 
	{
		try {
			//������
			Class.forName(driver);
			//�������ݿ�
			conn = DriverManager.getConnection(url,root,pwd);
			
			//Ԥ����sql���
				String Sql="select * from logintable";
				Map<String,String> values = new HashMap<>();
				values.put("username","356");
			//����
			String SQL="insert into logintable values(?,?),(?,?)";
			int resultint =this.insertexecte(SQL, new String[] {"310","50","222","333"});
			System.out.println(resultint);
			//ִ�в�ѯ
				ResultSet rs = this.selecttexecute(Sql,values);
			while(rs.next())
			{
				System.out.println("username:"+rs.getString("username"));
				System.out.println("password:"+rs.getString("password"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public ResultSet selecttexecute(String sql, Map<String, String> values) {
		PreparedStatement stmt;
		try {
			//Sql = 'select * from logintable where username=?'
			StringBuffer stb = new StringBuffer(sql+" where ");
			if(values !=null )
			{
				stb.append(" where ");
				boolean flag = true; 
					for(Map.Entry<String, String>entry : values.entrySet())
				{
						if(flag) {
							flag =false;
							stb.append(entry.getKey()+"="+entry.getValue()+"' ");
						}
					stb.append("and"+entry.getKey()+"="+entry.getValue()+"' ");
				}
			}
			stmt = conn.prepareStatement(sql.toString());
			return stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertexecte(String sql, String[] arg) {
		PreparedStatement stmt;
		try {
			//Sql = 'select * from logintable where username=?'
			stmt = conn.prepareStatement(sql);
			for(int i = 0;i<arg.length;i++) {
				stmt.setString(i+1, arg[i]);
			}
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	

}
