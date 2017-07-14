package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.JsonResult;

/**
 * Servlet implementation class UsernameServlet
 */
public class UsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsernameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		
		JsonResult jr=new JsonResult();
		
		String username=(String) request.getSession().getAttribute("username");
		if(username!=null){
			jr.setStatus(0);
			jr.setMessage(username);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
