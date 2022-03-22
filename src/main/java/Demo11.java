
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Uregs
 */
@WebServlet("/Demo11")
public class Demo11 extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter(); //PrintWriter: prints text data to a character stream. 
		response.setContentType("text/html");//getWriter :Returns a PrintWriter object that can send character text to the client.
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String role=request.getParameter("role");
        String pwd=request.getParameter("pwd");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","");
			String qr="insert into client1 values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(qr);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, role);
			ps.setString(4, pwd);
			int i=ps.executeUpdate();
			if(i>0)
			{				out.println("<script>window.alert('successfully registered');</script>");

				HttpSession sesion=request.getSession();
				sesion.setAttribute("id", email);
				response.sendRedirect("index.jsp");
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("AdminF.html");
				rd.include(request, response);
				out.println("<script>window.alert('not  registered');</script>");
			}
			con.close();
		}catch(Exception e)
		{
			RequestDispatcher rd=request.getRequestDispatcher("loginClient.html");
			rd.include(request, response);
			out.println("<script>window.alert('email already register');</script>");
		}
	}

}
