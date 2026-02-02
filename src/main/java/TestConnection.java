import database.DataBaseConnection;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection connection = DataBaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Connection test passed! ");
            DataBaseConnection.closeConnection(connection);
        } else {
            System.out.println("Connection test failed! ");
        }

    }
}