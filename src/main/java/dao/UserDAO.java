package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.User;

public class UserDAO {
	public static final String DRIVER="org.gjt.mm.mysql.Driver";
	public static final String DBURL="jdbc:mysql://localhost:3306/jiajiaowang?useUnicode=true&characterEncoding=utf-8";
	public static final String DBUSER="root";
	public static final String DBPASS="btb";
    
	private Connection conn=null;
	private PreparedStatement pStat=null;
	private ResultSet rs=null;
	private PreparedStatement pStmt=null;
    
	
	public Connection getConnectionn(){
		try{
			Class.forName(DRIVER).newInstance();
			return DriverManager.getConnection(DBURL,DBUSER,DBPASS);
		}
		catch(Exception e){
			return null;
		}
	}
	
	
	public void close(){
		try{
			if( rs!=null ) rs.close();
			if( pStat!=null ) pStat.close();
			if( conn!=null ) conn.close();
			if(pStmt!=null)  pStmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//end close
	
	
	public boolean isUsernameExists(String username) {
		conn=getConnectionn();
		try {
			pStat =conn.prepareStatement("select * from users where username=?");
			pStat.setString(1, username);
			rs=pStat.executeQuery();
			if( rs.next() ) 
				return true;
			else 
				return false;
		}
		catch (Exception e) 
		{ 
			return false; 
		}
		finally
		{ 
			close();
		}
	} //end isUsernameExists

	
	public boolean findUser(String username, String password){
		conn=getConnectionn();
		try {
			pStat =conn.prepareStatement("select * from users where username=? and password=?");
		    pStat.setString(1, username);
		    pStat.setString(2, password);
		    rs=pStat.executeQuery();
		    if( rs.next() ) 
		    	return true;
		    else 
			    return false;
		}
		catch (Exception e) 
		{ 
			return false; 
	    }
		finally
		{
		    close();
		}
	} //end findUser
	
	
	public boolean addUser(User user) {
		conn=getConnectionn();
		try {
			pStat=conn.prepareStatement("insert into users values(null,?,?,?)");
		    pStat.setString(1, user.getUsername());
		    pStat.setString(2, user.getPassword());
		    pStat.setString(3, user.getPhone());
		    int cnt=pStat.executeUpdate();
		    if(cnt>0) 
		    	return true;
		    else 
		    	return false;
		}
		catch (Exception e) { 
			return false; 
		}
		finally{
		    close();
		}
    } //end add
	
	public boolean editUser(int id,String username,String password,String phone) 
	{
		conn=getConnectionn();
		try{
			pStat=conn.prepareStatement("update users set username=?,password=?,phone=? where id=?");
		    pStat.setString(1, username);
		    pStat.setString(2, password);
		    pStat.setString(3, phone);
		    pStat.setInt(4, id);
		    int cnt=pStat.executeUpdate();
		    if(cnt>0) 
		    	return true;
		    else 
		    	return false;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
		    close();
		}
	}
	
	public int findUserId(String username){
		conn=getConnectionn();
		try {
			pStat =conn.prepareStatement("select id from users where username=?");
		    pStat.setString(1, username);
		    rs=pStat.executeQuery();
		    if( rs.next() ) 
		    	return rs.getInt("id");
		    else 
			    return -1;
		}
		catch (Exception e) 
		{ 
			return -1; 
	    }
		finally
		{
		    close();
		}
	} //end findUserId
	
	public String findUserPhone(String username){
		conn=getConnectionn();
		try {
			pStat =conn.prepareStatement("select phone from users where username=?");
		    pStat.setString(1, username);
		    rs=pStat.executeQuery();
		    if( rs.next() ) 
		    	return rs.getString("phone");
		    else 
			    return null;
		}
		catch (Exception e) 
		{ 
			e.printStackTrace();
			return null; 
	    }
		finally
		{
		    close();
		}
	} //end findUserId
	
	public String findUserPassword(String username){
		conn=getConnectionn();
		try {
			pStat =conn.prepareStatement("select password from users where username=?");
		    pStat.setString(1, username);
		    rs=pStat.executeQuery();
		    if( rs.next() ) 
		    	return rs.getString("password");
		    else 
			    return null;
		}
		catch (Exception e) 
		{ 
			e.printStackTrace();
			return null; 
	    }
		finally
		{
		    close();
		}
	} //end findUserId
	
	public ArrayList<User> getUserList()
	{
		ArrayList<User> list = new ArrayList<User>();
		conn=getConnectionn(); 
          
        try {  
        	String sql="select * from users";
            pStmt = conn.prepareStatement(sql);
            ResultSet rs=pStmt.executeQuery(); 

            while (rs.next()) 
            {   
                int id = rs.getInt("id");  
                String username = rs.getString("username");  
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                
                User use = new User();  
                use.setId(id);  
                use.setUsername(username);  
                use.setPassword(password);
                use.setPassword(phone);
                  
                list.add(use);
            }         
        } 
        catch (Exception e) {  
            System.out.println(e.getMessage());  
        } 
        finally{
		    close();
		}  
        return list;
	}
	
	public boolean delUser(String sql)
	{  
		conn=getConnectionn();		
        try {  
        	pStmt = conn.prepareStatement(sql);
        	int rows = pStmt.executeUpdate();  
            if(rows >= 1)  
                return true;   
            else   
               return false;              
        }
        catch (Exception e) {  
			return false;
        } 
	}

}

