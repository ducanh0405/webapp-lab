package com.student.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.student.model.Student;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDAO - Data Access Object
 * Use JSON file for storage instead of Database
 */
public class StudentDAO {

    private String filePath;
    private Gson gson = new Gson();

    public StudentDAO(String filePath) {
        this.filePath = filePath;
        // Initialize file if not exists
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                // Initialize sample data if file is empty
                List<Student> initialData = new ArrayList<>();
                initialData.add(new Student(1, "Nguyen Van A", "vana@example.com", 20));
                initialData.add(new Student(2, "Tran Thi B", "thib@example.com", 21));
                initialData.add(new Student(3, "Le Van C", "vanc@example.com", 22));
                saveStudents(initialData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Read list from JSON file
    private List<Student> loadStudents() {
        try (Reader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<ArrayList<Student>>(){}.getType();
            List<Student> students = gson.fromJson(reader, listType);
            if (students == null) {
                students = new ArrayList<>();
            }
            return students;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Write list to JSON file
    private void saveStudents(List<Student> students) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(students, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        return loadStudents();
    }

    public Student getStudentById(int id) {
        List<Student> students = loadStudents();
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void addStudent(Student student) {
        List<Student> students = loadStudents();
        int nextId = 1;
        for (Student s : students) {
            if (s.getId() >= nextId) {
                nextId = s.getId() + 1;
            }
        }
        student.setId(nextId);
        students.add(student);
        saveStudents(students);
    }

    public void updateStudent(Student updated) {
        List<Student> students = loadStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == updated.getId()) {
                students.set(i, updated);
                saveStudents(students);
                return;
            }
        }
    }

    public void deleteStudent(int id) {
        List<Student> students = loadStudents();
        students.removeIf(s -> s.getId() == id);
        saveStudents(students);
    }
}
