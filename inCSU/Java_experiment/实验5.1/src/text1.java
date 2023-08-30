public class text1 {
    public static void main(String[] args){
        Person person = new Person("Ben","12345678910");
        Person student = new Student("大二","Amy","98765432101");
        Person employee = new Employee("10000/月",new MyDate("2020","5","20"),"Peter","110120119");

        m1(person);
        m2(person);
        m3(person);

        m1(student);
        m2(student);
        m3(student);

        m1(employee);
        m2(employee);
        m3(employee);
    }

    public static void m1(Person p){
        System.out.println(p.getName());
    }

    public static void m2(Person p){
        System.out.println(p.toString());
    }

    public static void m3(Person p){
        if(p instanceof Student)
            ((Student)p).displayObject();
        else if(p instanceof Employee)
            ((Employee)p).displayObject();
    }
}

class Person{
    private String name;
    private String phoneNum;

    Person(){

    }

    Person(String name,String phoneNum){
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "Person类：姓名为"+name+" 电话为"+phoneNum;
    }
}

class Student extends Person{
    private String gradeStatus;

    Student(){

    }

    Student(String gradeStatus,String name,String phoneNum){
        super(name, phoneNum);
        this.gradeStatus =gradeStatus;
    }

    public String getGradeStatus() {
        return gradeStatus;
    }

    public void setGradeStatus(String gradeStatus) {
        this.gradeStatus = gradeStatus;
    }

    public void displayObject(){
        System.out.println("to "+super.getName()+"：请按时交实验报告。");
    }

    @Override
    public String toString() {
        return "Student类：姓名为"+super.getName()+" 电话为"+super.getPhoneNum()+" 年级为"+gradeStatus;
    }
}

class Employee extends Person {
    private String salary;
    private MyDate dateOfEmployment=new MyDate();

    Employee(){

    }

    Employee(String salary,MyDate dateOfEmployment,String name,String phoneNum){
        super(name,phoneNum);
        this.salary = salary;
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getSalary(){
        return salary;
    }

    public void setSalary(String salary){
        this.salary = salary;
    }

    public MyDate getDateOfEmployment(){
        return dateOfEmployment;
    }

    public void setDateOfEmployment(MyDate dateOfEmployment){
        this.dateOfEmployment = dateOfEmployment;
    }

    public void displayObject(){
        System.out.println("to "+ super.getName() + ": 请按时上班");
    }

    public String toString() {
        return "Employee类：姓名为" + super.getName() + "  电话为" + super.getPhoneNum() + "  工资为" + salary;
    }
}

class MyDate{
    private String year;
    private String month;
    private String day;

    MyDate(){

    }

    MyDate(String year, String month, String day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getYear(){
        return year;
    }

    public String getMonth(){
        return month;
    }

    public String getDay(){
        return day;
    }

    public void setYear(){
        this.year = year;
    }

    public void setMonth(String month){
        this.month = month;
    }

    public void setDay(String day){
        this.day = day;
    }
}
