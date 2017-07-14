package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.JsonResult;
import DAO.UserDAO;

import com.google.gson.Gson;

/**
 * Servlet implementation class LogServlet
 */
public class TutorLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TutorLogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		UserDAO userdao=new UserDAO();
		boolean flag=userdao.findUser(username, password);
		
        JsonResult jr=new JsonResult();
        
        if( flag ) 
		{
        	jr.setStatus(0);
			jr.setMessage("success");
			request.getSession().setAttribute("username", username);
		}
		else{
			jr.setStatus(-1);
			jr.setMessage("error");
		}
        
		Gson gb=new Gson();
	    String info=gb.toJson(jr);
	    response.getWriter().append(info);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
