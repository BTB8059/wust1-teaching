package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.JsonResult;
import beans.JsonUser;
import dao.UserDAO;

/**
 * Servlet implementation class InforServlet
 */
public class InforServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InforServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		
        JsonUser ju=new JsonUser();	
        UserDAO userdao=new UserDAO();
		String username=(String) request.getSession().getAttribute("username");
		String password=(String) request.getSession().getAttribute("password");
		
		int id=userdao.findUserId(username, password);
		String phone=userdao.findUserPhone(username, password);
		if(id!=-1 && !phone.equals(null)){
			ju.setStatus(0);
			ju.setMessage("success");
			ju.setId(id);
			ju.setUsername(username);
			ju.setPassword(password);
			ju.setPhone(phone);
		}
		else{
			ju.setStatus(-1);
			ju.setMessage("error");
		}	
		Gson gb=new Gson();
	    String info=gb.toJson(ju);
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
