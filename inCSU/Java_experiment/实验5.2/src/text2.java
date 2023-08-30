import java.util.ArrayList;

public class text2 {
    public static void main(String[] args) {
        Course course1 = new Course("Data Structures");
        Course course2 = new Course("Database Systems");

        course1.addStudent("Peter Jones");
        course1.addStudent("Brian Smith");
        course1.addStudent("Anne Kennedy");

        course2.addStudent("Peter Jones");
        course2.addStudent("Steve Smith");

        //course1的相关操作
        System.out.println("Number of students in course1: "+course1.getCourseName()+" is " + course1.getNumberOfStudents());

        String[] students = course1.getStudents();
        for (int i = 0; i < course1.getNumberOfStudents(); i++)
            System.out.print(students[i] + ", ");

        System.out.println("");
        System.out.println("");

        course1.dropStudent("Anne Kennedy");
        System.out.println("删除Anne Kennedy后，课程"+course1.getCourseName()+" 的学生数有:" + course1.getNumberOfStudents());
        System.out.println("删除Anne Kennedy后，course1的学生有：");
        String[] students0 = course1.getStudents();
        for (int i = 0; i < course1.getNumberOfStudents(); i++)
            System.out.print(students0[i] + ", ");

        //course2的相关操作
        System.out.println("");
        System.out.println("");
        System.out.println("Number of students in course2: "+course2.getCourseName()+" is " + course2.getNumberOfStudents());

        String[] students1 = course2.getStudents();
        for (int i = 0; i < course2.getNumberOfStudents(); i++)
            System.out.print(students1[i] + ", ");

        System.out.println("");
        System.out.println("");

        course2.dropStudent("Steve Smith");
        System.out.println("删除Steve Smith后，课程"+course2.getCourseName()+" 的学生数有:" + course2.getNumberOfStudents());
        System.out.println("删除Steve Smith后，course2的学生有：");
        String[] students2 = course2.getStudents();
        for (int i = 0; i < course2.getNumberOfStudents(); i++)
            System.out.print(students2[i] + ", ");
    }
}

class Course {
    private String courseName;
    private ArrayList<String> students = new ArrayList<>();

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public void addStudent(String student) {
        students.add(student);
    }

    public String[] getStudents() {
        int size = students.size();
        String[] s = new String[size];
        for(int i=0; i<students.size(); i++)
            s[i] = students.get(i);
        return s;
    }

    public int getNumberOfStudents() {
        return students.size();
    }

    public String getCourseName() {
        return courseName;
    }

    public void dropStudent(String student) {
        students.remove(student);
    }
}
