package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.JsonTutor;
import beans.Tutor;
import dao.TutorDAO;

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
        JsonTutor json=new JsonTutor();
        int count=0;
        for(Tutor tu : list)
        { 
        	count++;
        }
        int draw;
		if (request.getParameter("draw") == null)
			draw = 1;
		else{
			draw = Integer.parseInt(request.getParameter("draw"));
		}
        json.setDraw(draw);
        json.setRecordsTotal(count);
        json.setRecordsFiltered(10);
        json.setData(list);
       
        Gson gb=new Gson();
	    String info=gb.toJson(json);
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
