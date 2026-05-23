package data;

import domain.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains the methods of SELECT, INSERT, UPDATE and DELETE for the
 * Person table in MYSQL
 */
public class JdbcPerson {

    private final String SQL_INSERT = "INSERT INTO person(name) VALUES(?)";

    private final String SQL_UPDATE = "UPDATE person SET name=? WHERE id_person=?";

    private final String SQL_DELETE = "DELETE FROM person WHERE id_person = ?";

    private final String SQL_SELECT = "SELECT id_person, name FROM person ORDER BY id_person";

    public int insert(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = JavaConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, name);
            System.out.println("Executing query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Affected records:" + rows);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            JavaConnection.close(stmt);
            JavaConnection.close(conn);
        }

        return rows;
    }

    public int update(int idPerson, String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = JavaConnection.getConnection();
            System.out.println("Executing query:" + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, name);
            stmt.setInt(2, idPerson);
            rows = stmt.executeUpdate();
            System.out.println("Updated records:" + rows);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            JavaConnection.close(stmt);
            JavaConnection.close(conn);
        }

        return rows;
    }

    public int delete(int idPerson) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = JavaConnection.getConnection();
            System.out.println("Executing query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idPerson);
            rows = stmt.executeUpdate();
            System.out.println("Deleted records:" + rows);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            JavaConnection.close(stmt);
            JavaConnection.close(conn);
        }

        return rows;
    }

    public List<Person> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Person person = null;
        List<Person> people = new ArrayList<>();

        try {
            conn = JavaConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPerson = rs.getInt("id_person");
                String name = rs.getString("name");

                person = new Person();
                person.setIdPerson(idPerson);
                person.setName(name);

                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            JavaConnection.close(rs);
            JavaConnection.close(stmt);
            JavaConnection.close(conn);
        }

        return people;
    }
}