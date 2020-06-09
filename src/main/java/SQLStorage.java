import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class SQLStorage {
    private static final Logger LOG = LoggerFactory.getLogger(SQLStorage.class.getName());

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/carstore";
        String username = "postgres";
        String password = "pasword";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement("SELECT * FROM car as c where c.id in (?, ?, ?)");
            st.setInt(1, 1);
            st.setInt(2, 3);
            st.setInt(3, 5);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                System.out.println(String.format("%s %s",
                        rs.getString("name"), rs.getInt("id")));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }
}
