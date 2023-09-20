package Controller;

import Model.Report;
import Model.Students;
import View.Validation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Management {

    Scanner sc = new Scanner(System.in);
    ArrayList<Students> stulist = new ArrayList<>();

    public ArrayList<Students> getStulist() {
        return stulist;
    }

    public void addNew() {
        
        System.out.println("Input ID:");
        String ID = Validation.checkInputString();
        System.out.println("Input Name:");
        String name = Validation.checkInputString();
        System.out.println("Input semester:");
        int semester = Validation.checkInputIntLimit(1, 9);
        System.out.println("Input Cousrs:");
        String course = Validation.checkInputCourse();

        stulist.add(new Students(ID, name, String.valueOf(semester), course));
    }

    public <Students> ArrayList<Students> search(Predicate<Students> p) {
        ArrayList<Students> result = new ArrayList<>();
        for (Object stu : stulist) {
            if (p.test((Students) stu)) {
                result.add((Students) stu);
            }
        }
        return result;
    }

    public void sortStudentsByName(ArrayList<Students> stuList) {
        Collections.sort(stuList, new Comparator<Students>() {
            @Override
            public int compare(Students student1, Students student2) {
                return student1.getName().compareToIgnoreCase(student2.getName());
            }
        });
    }

    public ArrayList<Students> updateOrDeleteStudent(String studentID) {
        Predicate<Students> filterByID = student -> student.getID().equals(studentID);

        return search(filterByID);

    }

    public void deleteStudent(Students studentToDelete) {
        stulist.remove(studentToDelete);
        System.out.println("Student deleted successfully.");
    }

    public void updateStudent(Students studentToUpdate) {
        System.out.println("Current student information:");
        System.out.println(studentToUpdate);

        System.out.println("Input new name (or press Enter to keep current name):");
        String newName = Validation.checkInputString();

        studentToUpdate.setName(newName);

        System.out.println("Input new semester (or press Enter to keep current semester):");
        int newSemesterInput = Validation.checkInputIntLimit(1, 9);
        studentToUpdate.setSemester(String.valueOf(newSemesterInput));

        System.out.println("Input new course (or press Enter to keep current course):");
        String newCourse = Validation.checkInputCourse();

        studentToUpdate.setCourse(newCourse);

        System.out.println("Student information updated successfully.");
    }

    public void displayStudentCourseReport() {
        List<Report> studentCourseCounts = new ArrayList<>();

        // Count the courses for each student
        for (Students student : stulist) {
            String studentName = student.getName();
            String course = student.getCourse();

            boolean found = false;
            for (Report report : studentCourseCounts) {
                if (report.getStudentName().equals(studentName) && report.getCourseName().equals(course)) {
                    report.setTotalCourse(report.getTotalCourse() + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                studentCourseCounts.add(new Report(studentName, course, 1));
            }
        }

        // Display the report
        System.out.println("Student name | Course | Total Courses");
        for (Report report : studentCourseCounts) {
            System.out.println(report.getStudentName() + " | " + report.getCourseName() + " | " + report.getTotalCourse());
        }
    }

}
