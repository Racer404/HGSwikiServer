package racer.fund;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class InsertIndexMember
 */
@WebServlet("/InsertIndexMember")
public class InsertIndexMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertIndexMember() {
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
		
		//Warning: when request parameter received by under TOMCAT 7.0, need to do UTF-8 encode 注意：在TOMCAT7.0以下版本需要对传入参数进行UTF8编码
		String inputString = request.getParameter("name");
		StringBuilder outString = new StringBuilder();
		if(inputString!=null) {
			try {
				Statement statement = con.createStatement();
				statement.execute("INSERT INTO `hgswiki`.`IndexMembers` (`members`) VALUES ('"+inputString+"');");
				statement.close();
				outString.append("success");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				outString.append("failed");
			}
		}
		else {
			outString.append("null param");
		}
		response.setContentType("text/json; charset=utf-8");
		response.addHeader("access-control-allow-origin", "*");
		response.getWriter().write(outString.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
