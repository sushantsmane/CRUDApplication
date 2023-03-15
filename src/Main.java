import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
     static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        insertStudent();
        selectStudent();
        updateStudent();
        deleteStudent();

    }

    private static void selectStudent() {
    }


    static void deleteStudent(){
        int rollNo = sc.nextInt();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mumbai", "root", "");
            Statement st = con.createStatement();
            int count = st.executeUpdate("delete from student where rollNo='"+rollNo+"'");
            if(count>0){
                System.out.println("Student is deleted from DB");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }


    static void updateStudent(){
        Student student = new Student();
        student.setRollNo(sc.nextInt());
        student.setName(sc.next());
        student.setEmail(sc.next());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mumbai", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate("update student set name='"+student.getName()+"',email='"+student.getEmail()+"' where rollNo='"+student.getRollNo()+"'");
            selectStudent(student.getRollNo());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    static void selectStudent(int rollNo){
        Student student = new Student();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mumbai", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from student where rollNo = '"+rollNo+"'");
            while (rs.next()){
                student.setRollNo(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setEmail(rs.getString(3));
            }
            System.out.println(student.getRollNo());
            System.out.println(student.getName());
            System.out.println(student.getEmail());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    static void insertStudent() {
        Student student = new Student();
        student.setRollNo(sc.nextInt());
        student.setName(sc.next());
        student.setEmail(sc.next());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mumbai", "root", "");
            Statement st = con.createStatement();
            st.executeUpdate("insert into student values('" + student.getRollNo() + "','" + student.getName() + "','" + student.getEmail() + "')");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
