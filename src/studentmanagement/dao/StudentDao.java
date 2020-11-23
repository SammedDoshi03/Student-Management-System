package studentmanagement.dao;
import java.sql.*;
import java.util.*;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;

import studentmanagement.bean.Student;

public class StudentDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	
	private static final String INSERT_STUDENTS_SQL = "INSERT INTO student_data" + "  (name, email_Id, mobile_No,blood_Group) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_STUDENT_BY_ID = "select roll_No,name,email_Id,mobile_No,blood_Group from student_data where roll_No =?";
	private static final String SELECT_ALL_STUDENTS = "select * from student_data";
	private static final String DELETE_STUDENTS_SQL = "delete from student_data where roll_No = ?;";
	private static final String UPDATE_STUDENTS_SQL = "update student_data set name = ?,email_Id= ?, mobile_No =? , blood_Group = ? where roll_No = ?;";
	
	private static final String INSERT_VAI_PROC_SQL =  "Call Inception(?, ?, ?, ?, ?);";
	private static final String FUC_SQL =  "{?=Call Interstaller()}";
	
	public StudentDao() {	
	}
	
	protected Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
	
	public int showCount() throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			CallableStatement callableStatement = connection.prepareCall(FUC_SQL)) {
			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.execute();
			System.out.print(callableStatement.getInt(1));
			return  callableStatement.getInt(1);
		} catch (SQLException e) {
			printSQLException(e);
			return 0;
		}
	}
	
	public void insertStudentviaProc(Student student) throws SQLException {
		System.out.println(INSERT_VAI_PROC_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				CallableStatement callableStatement = connection.prepareCall(INSERT_VAI_PROC_SQL)) {
			callableStatement.setInt(1, showCount()+1);
			callableStatement.setString(2, student.getName());
			callableStatement.setString(3, student.getEmail_Id());
			callableStatement.setLong(4, student.getMobile_No());
			callableStatement.setString(5, student.getBlood_Group());
			System.out.println(callableStatement);
			callableStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	

	public void insertStudent(Student student) throws SQLException {
		System.out.println(INSERT_STUDENTS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)) {
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getEmail_Id());
			preparedStatement.setLong(3, student.getMobile_No());
			preparedStatement.setString(4, student.getBlood_Group());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	

	public Student selectStudent(int roll_No) {
		Student student = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
			preparedStatement.setInt(1, roll_No);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("Name");
				String email_Id = rs.getString("Email_Id");
				Long mobile_No = rs.getLong("Mobile_No");
				String blood_Group = rs.getString("Blood_Group");
				student = new Student(roll_No, name, email_Id, mobile_No, blood_Group);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return student;
	}

	public List<Student> selectAllstudent() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Student> student = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int roll_No = rs.getInt("Roll_No");
				String name = rs.getString("Name");
				String email_Id = rs.getString("Email_Id");
				Long mobile_No = rs.getLong("Mobile_No");
				String blood_Group = rs.getString("Blood_Group");
				student.add(new Student(roll_No, name, email_Id, mobile_No, blood_Group));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return student;
	}

	public boolean deleteStudent(int roll_No) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS_SQL);) {
			statement.setInt(1, roll_No);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateStudent(Student student) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENTS_SQL);) {
			System.out.println("Updated Student:"+statement);
			statement.setString(1, student.getName());
			statement.setString(2, student.getEmail_Id());
			statement.setLong(3, student.getMobile_No());
			statement.setString(4, student.getBlood_Group());
			statement.setInt(5, student.getRoll_No());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	
}
