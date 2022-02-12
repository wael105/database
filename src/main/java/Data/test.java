package Data;

public class test {
  public static void printStudent(Student s){
    System.out.println("Name: "+ s.getName());
    System.out.println("ID: "+ s.getId());
  }



  public static void main(String[] args) throws Exception {
    Student s = new Student("t",3);
    StudentDAO dao = new StudentDAO();
    dao.insert(s);
    printStudent(dao.search(9));
  }

}
