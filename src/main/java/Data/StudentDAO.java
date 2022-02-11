package Data;

import java.io.*;

public class StudentDAO {
    private static final Cache<Integer, Student> cache = new Cache<>(10);
    private final Database database = new Database();

    public synchronized void Insert(Student student){
        database.Insert(student);
    }


    public Student Search(int id){
        Student student = cache.get(id);
        if(student != null)
            return student;
        student = database.Search(id);
        if (student != null) {
            cache.put(student.getId(), student);
        }
        return student;
    }


    public synchronized void Update(int id, Student student){
        Student student1 = Search(id);
        student1.setID(student.getId());
        student1.setName(student.getName());
        database.Update(id, student1);
    }


    public synchronized void Delete(int id){
        cache.delete(id);
        database.Delete(id);
    }

}
