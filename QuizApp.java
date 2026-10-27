
import java.sql.*;

class QuizApp {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/college",
                "root", "your_password");

            // Insert quiz result
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO QuizResults VALUES (?, ?, ?, ?)");
            ps.setInt(1, 101);
            ps.setString(2, "Java Quiz");
            ps.setInt(3, 10);
            ps.setInt(4, 9);
            ps.executeUpdate();

            Statement st = con.createStatement();

            // Highest score
            ResultSet rs = st.executeQuery(
                "SELECT MAX(score) FROM QuizResults");
            if (rs.next())
                System.out.println("Highest: " + rs.getInt(1));

            // Students scoring above 80%
            rs = st.executeQuery(
                "SELECT * FROM QuizResults WHERE score*100.0/total_questions > 80");

            while (rs.next())
                System.out.println(rs.getInt(1) + " " +
                    rs.getString(2) + " " + rs.getInt(4));

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}