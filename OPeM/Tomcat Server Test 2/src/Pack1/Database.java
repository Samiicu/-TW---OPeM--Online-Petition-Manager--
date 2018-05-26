package Pack1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class Database {
   
    private static Connection connection = null;
	
	
	
	private Database()
	{
	}
	
	static void isConnection()
	{
		if(connection==null)
			createConnection();
	}
	
	static void createConnection()
    {
    	try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			connection=DriverManager.getConnection(  "jdbc:oracle:thin:WEB/WEB@localhost:1521:xe");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	    	
    }
	
	public static long add(Petition p) 
	{
		isConnection();
		
		try {
			CallableStatement c=connection.prepareCall("{call add_petitie('"+p.category+"','"+p.title+"','"+p.description +"',"+p.target+",'"+p.tags2+"','"+p.name+"','"+p.email+"','"+p.expDate+"',"+"?"+")}");
			
			
			c.registerOutParameter(1, Types.INTEGER);
			c.executeUpdate();
			
			connection.commit();
			return c.getLong(1);
			//Database.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (Long) null;
		
		//petitions.add(p);
	}
	
	public static ArrayList<Petition> get()
	{
		ArrayList<Petition> petitions=new ArrayList<Petition>();
		isConnection();
		Statement stmt;
		try {
			stmt = connection.createStatement();
		
	        ResultSet rs=stmt.executeQuery("select * from petitii order by (expires_at-sysdate) asc");
	        while(rs.next())
	        {
	        	//System.out.println(rs.getInt(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5)+" "+rs.getInt(6)+" "+rs.getString(7)+" "+rs.getString(8)+" "+rs.getString(9)+" "+rs.getString(11));
	        	petitions.add(new Petition(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getLong(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(11)));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return petitions;
		//for(int i=0;i<petitions.size();i++)
			//System.out.println(petitions.get(i).description);
		//return null;
	}
	
	public static Petition getPetitionById(long id)
	{
		Petition p=null;
		isConnection();
		Statement stmt;
		try {
			stmt = connection.createStatement();
		
	        ResultSet rs=stmt.executeQuery("select * from petitii where id="+id);
	        if(rs.next())
	        {
	        	//System.out.println("Debug");
	        	//System.out.println(rs.getInt(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5)+" "+rs.getInt(6)+" "+rs.getString(7)+" "+rs.getString(8)+" "+rs.getString(9)+" "+rs.getString(11));
	        	p=new Petition(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getLong(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(11));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
}
