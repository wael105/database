package Data;

import java.io.*;

public class StudentDAO {
    private static final Cache<Integer, Student> cache = new Cache<>(10);
    private final Database database = new Database();

    public synchronized void insert(Student student){
        database.insert(student);
    }


    public Student search(int id) {
        Student student = cache.get(id);
        if(student != null)
            return student;
        student = database.search(id);
        if (student != null) {
            cache.put(student.getId(), student);
        }
        return student;
    }


    public synchronized void update(int id, Student student) {
        Student student1 = search(id);
        student1.setID(student.getId());
        student1.setName(student.getName());
        database.update(id, student1);
    }


    public synchronized void delete(int id){
        cache.delete(id);
        database.delete(id);
    }

}
