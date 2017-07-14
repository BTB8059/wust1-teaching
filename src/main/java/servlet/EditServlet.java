package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.JsonResult;
import dao.UserDAO;

/**
 * Servlet implementation class EditServlet
 */
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("json/html");
		
		String username1=(String) request.getSession().getAttribute("username");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String phone=request.getParameter("phone");
		
		JsonResult jr=new JsonResult();
		UserDAO userdao=new UserDAO();	
		
		int id=userdao.findUserId(username1, password);
		
		if(!username.equals(username1))
		{
			if(userdao.isUsernameExists(username)){
				jr.setStatus(-2);
				jr.setMessage("username exist");
			}
			else{
				boolean flag=userdao.editUser(id, username, password, phone);
				if(flag){
					jr.setStatus(0);
					jr.setMessage("success1");
					request.getSession().setAttribute("username", username);
				}
				else{
					jr.setStatus(-1);
					jr.setMessage("error1");
				}
			}
		}
		else{
			boolean flag=userdao.editUser(id, username, password, phone);
			if(flag){
				jr.setStatus(0);
				jr.setMessage("success2");
			}
			else{
				jr.setStatus(-1);
				jr.setMessage("error2");
			}
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
