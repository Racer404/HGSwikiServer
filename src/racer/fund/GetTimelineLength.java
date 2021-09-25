package racer.fund;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Statement;


public class GetTimelineLength extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTimelineLength() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
		String driver = getInitParameter("driver");
		String url = getInitParameter("url");
		String name = getInitParameter("user");
		String pass = getInitParameter("password");
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,name,pass);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String outString="";
		try {
			Statement statement=con.createStatement();
			ResultSet resultSet=statement.executeQuery("SELECT COUNT(*) FROM Timeline");
			resultSet.next();
			outString=resultSet.getString(1);
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/json; charset=utf-8");
		response.addHeader("access-control-allow-origin", "*");
		response.getWriter().write(outString);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
