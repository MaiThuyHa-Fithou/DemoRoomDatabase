package vn.edu.hou.mttha.demosqlite;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "student_table")
public class Student {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
