package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Tutor;

public class TutorDAO {
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
			pStat =conn.prepareStatement("select * from tutors where username=?");
			pStat.setString(1, username);
			rs=pStat.executeQuery();
			if( rs.next() ) 
				return true;
			else 
				return false;
		}
		catch (Exception e) 
		{ 
			e.printStackTrace();
			return false; 
		}
		finally
		{ 
			close();
		}
	} //end isUsernameExists

	
	public boolean findTutor(String username){
		conn=getConnectionn();
		try {
			pStat =conn.prepareStatement("select * from tutors where username=?");
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
	} //end findTutor
	
	
	
	public boolean addTutor(Tutor tutor) {
		conn=getConnectionn();
		try {
			pStat=conn.prepareStatement("insert into tutors values(null,?,?,?,?,?,?,?,?)");
		    pStat.setString(1, tutor.getUsername());
		    pStat.setString(2, tutor.getSex());
		    pStat.setString(3, tutor.getSchool());
		    pStat.setString(4, tutor.getMajor());
		    pStat.setString(5, tutor.getEducation());
		    pStat.setString(6, tutor.getTeacharea());
		    pStat.setString(7, tutor.getSubject());
		    pStat.setString(8, tutor.getComment());
		    int cnt=pStat.executeUpdate();
		    if(cnt>0) 
		    	return true;
		    else 
		    	return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false; 
		}
		finally{
		    close();
		}
    } //end add
	
	public boolean editTutor(String id,String username,String sex,String school,String major,String education,String teacharea,String subject,String comment) 
	{
		conn=getConnectionn();
		try{
			pStat=conn.prepareStatement("update tutors set username=?,sex=?,school=?,major=?,education=?,teacharea=?,subject=?,comment=? where id=?");
		    pStat.setString(1, username);
		    pStat.setString(2, sex);
		    pStat.setString(3, school);
		    pStat.setString(4, major);
		    pStat.setString(5, education);
		    pStat.setString(6, teacharea);
		    pStat.setString(7, subject);
		    pStat.setString(8, comment);
		    pStat.setString(9, id);
		    int cnt=pStat.executeUpdate();
		    if(cnt>0) 
		    	return true;
		    else 
		    	return false;
		}
		catch(Exception e){
			return false;
		}
		finally{
		    close();
		}
	}
	
	public int findTutorId(String username){
		conn=getConnectionn();
		try {
			pStat =conn.prepareStatement("select id from tutors where username=?");
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
	} //end findTutorId
	
	public ArrayList<Tutor> getTutorList()
	{
		ArrayList<Tutor> list = new ArrayList<Tutor>();
		conn=getConnectionn(); 
          
        try {  
        	String sql="select * from tutors";
            pStmt = conn.prepareStatement(sql);
            ResultSet rs=pStmt.executeQuery(); 

            while (rs.next()) 
            {   
                int id = rs.getInt("id");  
                String username = rs.getString("username");  
                String sex = rs.getString("sex");  
                String school = rs.getString("school");
                String major = rs.getString("major");
                String education = rs.getString("education");
                String teacharea = rs.getString("teacharea");
                String subject = rs.getString("subject");
                String comment = rs.getString("comment");
                
                Tutor tutor = new Tutor();  
                tutor.setId(id);
                tutor.setUsername(username); 
                tutor.setSex(sex);
                tutor.setSchool(school);
                tutor.setMajor(major);
                tutor.setEducation(education);                
                tutor.setTeacharea(teacharea);
                tutor.setSubject(subject);                
                tutor.setComment(comment);                               
                  
                list.add(tutor);
            }         
        } 
        catch (Exception e) {  
        	e.printStackTrace();
            //System.out.println(e.getMessage());  
        } 
        finally{
		    close();
		}  
        return list;
	}
	
	public boolean delTutor(String sql)
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
