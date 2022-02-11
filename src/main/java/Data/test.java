package Data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.commons.io.input.CloseShieldInputStream;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class test {
  public static void printStudent(Student s){
    System.out.println("Name: "+ s.getName());
    System.out.println("ID: "+ s.getId());
  }



  public static void main(String[] args) throws Exception {
    Student s = new Student("oar",6);
    StudentDAO dao = new StudentDAO();
    dao.Update(3, s);
  }

}
