package com.aloysius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentDataInsert
 */
@WebServlet("/StudentDataInsert")
public class StudentDataInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDataInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int sno=Integer.parseInt(request.getParameter("sn"));
		String sname=request.getParameter("sa");
		int spnum=Integer.parseInt(request.getParameter("sp"));
		int sfee=Integer.parseInt(request.getParameter("sf"));
		
		out.println(sno);
		out.println(sname);
		out.println(spnum);
		out.println(sfee);
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String s1="insert into AloysiusStudentData(sno,sname,spnum,sfee) values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(s1);
			ps.setInt(1, sno);
			ps.setString(2, sname);
			ps.setInt(3, spnum);
			ps.setInt(4, sfee);
			ps.executeUpdate();
			ps.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}