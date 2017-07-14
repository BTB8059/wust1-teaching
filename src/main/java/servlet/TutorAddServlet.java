package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.JsonResult;
import beans.Tutor;
import dao.TutorDAO;

/**
 * Servlet implementation class TutorAddServlet
 */
public class TutorAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TutorAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	    response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
	    String username=request.getParameter("username");
		String sex=request.getParameter("sex");
		String school=request.getParameter("school");
		String major=request.getParameter("major");
		String education=request.getParameter("education");
		String teacharea=request.getParameter("teacharea");
		String subject=request.getParameter("subject");
		String comment=request.getParameter("comment");
		
		System.out.println(username);
		
		Tutor tutor=new Tutor();
		tutor.setUsername(username);		
		tutor.setSex(sex);
		tutor.setSchool(school);
		tutor.setMajor(major);
		tutor.setEducation(education);
		tutor.setTeacharea(teacharea);
		tutor.setSubject(subject);
		tutor.setComment(comment);
		
		TutorDAO tutordao=new TutorDAO();
		
        JsonResult jr=new JsonResult();
		
		if(tutordao.isUsernameExists(username)){
			jr.setStatus(0);
			jr.setMessage("用户名已存在");
		}
		else{
			boolean flag=tutordao.addTutor(tutor);
			System.out.println("11111");
			if(flag){
				jr.setStatus(1);
				jr.setMessage("添加成功");
			}
			else{
				jr.setStatus(-1);
				jr.setMessage("添加失败");
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
