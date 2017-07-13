package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.TutorDAO;
import beans.JsonResult;
import beans.Tutor;

/**
 * Servlet implementation class QueryServlet
 */
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		
		TutorDAO dao=new TutorDAO();
        ArrayList<Tutor> list =dao.getTutorList();
        
        for(Tutor tu : list)
        {
        	System.out.println(tu.getId());
        	System.out.println(tu.getUsername());
        	System.out.println(tu.getSex());
        	System.out.println(tu.getSchool());
        	System.out.println(tu.getMajor());
        	System.out.println(tu.getEducation());
        	System.out.println(tu.getTeacharea());
        	System.out.println(tu.getSubject());
        	System.out.println(tu.getComment());        	
        }
       
        Gson gb=new Gson();
	    String info=gb.toJson(list);
	    response.getWriter().append(info);		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
