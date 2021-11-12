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
 * Servlet implementation class InsertNewWiki
 */
@WebServlet("/InsertNewWiki")
public class InsertNewWiki extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNewWiki() {
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
		response.setContentType("text/json; charset=utf-8");
		response.addHeader("access-control-allow-origin", "*");
		String title=request.getParameter("title");
		String date=request.getParameter("date");
		String character=request.getParameter("character");
		String content=request.getParameter("content");
		if(title!=null&&date!=null&&character!=null&&content!=null){
			try {
				Statement statement=con.createStatement();
				statement.execute("INSERT INTO `hgswiki`.`Timeline` (`title`, `date`, `svgpath`, `character`, `content`) VALUES ('"+title+"','"+date+"','"+""+"','"+character+"','"+content+"');");
				statement.close();
				response.getWriter().write("success");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write("failed");
			}
		}
		else {
			response.getWriter().write("nullparams");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
