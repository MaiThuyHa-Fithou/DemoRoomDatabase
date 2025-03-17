package vn.edu.hou.mttha.demosqlite;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentRepository {
    private final StudentDAO studentDAO;
    private final LiveData<List<Student>> allStudents;
    private final ExecutorService executorService
            = Executors.newSingleThreadExecutor();
    public StudentRepository(Application application){
        StudentDatabase db = StudentDatabase.getDatabase(application);
        studentDAO= db.studentDAO();
        allStudents = studentDAO.getAllStudent();
    }

    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }
    public void insert(Student student){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                studentDAO.insert(student);
            }
        });
    }

    public void update(Student student){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                studentDAO.update(student);
            }
        });
    }

    public void delete(Student student){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                studentDAO.delete(student);
            }
        });
    }

    public void deleteAll(){
        executorService.execute(studentDAO::deleteAll);
    }
}
