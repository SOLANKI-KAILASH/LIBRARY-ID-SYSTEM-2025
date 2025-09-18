import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.sql.*;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // max 5MB
public class LibraryIDServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Read form fields
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String enrollment = request.getParameter("enrollment");
        String address = request.getParameter("address");
        String issueDate = request.getParameter("issueDate");
        String expiryDate = request.getParameter("expiryDate");
        String issuer = request.getParameter("issuer");

        try {
            String url = "jdbc:mysql://localhost:3306/library";
            String username = "root";
            String password = "kailash1577";

            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO student_data (name, email, mobile, enrollment, address,issue_date, expiry_date, issuer) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, mobile);
            st.setString(4, enrollment);
            st.setString(5, address);
            st.setString(6, issueDate);
            st.setString(7, expiryDate);
            st.setString(8, issuer);

            int rowsInserted = st.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("No rows inserted!");
            }

            st.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Simple response
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>"+ name + " ID CARD </title>");
        out.println("<style>");
        out.println(".idcard {");
        out.println("  margin-top: 30px;");
        out.println("  display: flex;");
        out.println("  flex-direction: column;");
        out.println("  justify-content: center;");
        out.println("  align-items: center;");
        out.println("  width: 600px;");
        out.println("  padding: 10px 10px 20px 10px;");
        out.println("  border: 2px solid black;");
        out.println("}");
        out.println("#gpa-logo {");
        out.println("  margin-top: 7px;");
        out.println("  height: 70px;");
        out.println("  width: 70px;");
        out.println("}");
        out.println(".header { display: flex; }");
        out.println(".line { display: flex; }");
        out.println(".data { padding-left: 50px; font-size: 16px; }");
        out.println("#library-id { color: red; font-weight: 800; font-size: 18px; }");
        out.println("#print-button { background-color: rgb(5,255,5); color: black; font-size: 16px; }");
        out.println("@media (max-width: 500px) {");
        out.println("  .idcard { width: 350px; padding: 10px; }");
        out.println("  #gpa-logo { height: 70px; width: 70px; }");
        out.println("  .header { display: flex; font-size: 14px; }");
        out.println("  .data { padding-left: 15px; font-size: 14px; }");
        out.println("  #library-id { font-size: 18px; }");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='idcard'>");
        out.println("  <div class='header'>");
        out.println("    <img src='logo2.png' alt='' id='gpa-logo'>");
        out.println("    <h3>GOVERNMENT POLYTECHNIC, AHMEDABAD <br>Ambavadi, Ahmedabad - 380015</h3>");
        out.println("  </div>");
        out.println("  <p id='library-id'>LIBRARY ID CARD</p>");
        out.println("  <div class='data'>");
        out.println("    <b>NAME: " + name + " <br>");
        out.println("    EMAIL: " + email + " <br>");
        out.println("    MOBILE: " + mobile + " <br>");
        out.println("    ENROLLMENT NO: " + enrollment + " <br>");
        out.println("    ADDRESS: " + address + " <br>");
        out.println("    ISSUE DATE: " + issueDate + " <br>");
        out.println("    EXPIRY DATE: " + expiryDate + " <br>");
        out.println("    ISSUER: " + issuer + "</b>");

        out.println("  </div>");
        out.println("</div>");
        out.println("<br><br><br><br><br>");
        out.println("<button onclick='window.print()' id='print-button'>Print ID Card</button>");
        out.println("</body>");
        out.println("</html>");

    }
}
