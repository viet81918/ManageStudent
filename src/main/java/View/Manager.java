package View;

import Controller.Management;
import Model.Report;
import Model.Students;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Manager extends Menu<String> {

    public static Scanner sc = new Scanner(System.in);
    static String[] mc = {"Create", "Find and Sort", "Update/Delete", "Report", "Exit"};
    protected Management managers;

    public Manager() {
        super("Menu", mc);
        managers = new Management();
    }

    @Override
    public void execute(String n) {
        switch (n) {
            case "1":
                createNew();

                break;
            case "2":
                findAndSort();
                break;
            case "3":
                updateOrDelete();
                break;
            case "4":

                break;
            case "5":
                System.exit(0);
        }
    }

    private void findAndSort() {
        System.out.println("Input name or part of the name to search for:");
        String searchName = sc.nextLine();

        ArrayList<Students> foundStudents = managers.search(student
                -> student.getName().toLowerCase().contains(searchName.toLowerCase())
        );

        if (foundStudents.isEmpty()) {
            System.out.println("No students found matching the search criteria.");
        } else {
            // Sort the found students by name
            managers.sortStudentsByName(foundStudents);
            // Display the sorted list
            System.out.println("Student name\tSemester\tCourse Name");
            for (Students student : foundStudents) {
                System.out.println(
                        student.getName() + "\t"
                        + student.getSemester() + "\t"
                        + student.getCourse()
                );
            }
        }
    }

    private void updateOrDelete() {
        System.out.println("Enter student ID  to update/delete:");
        String studentID = sc.nextLine();

        if (!managers.updateOrDeleteStudent(studentID).isEmpty()) {
            Students studentToUpdateOrDelete = managers.updateOrDeleteStudent(studentID).get(0); // Assuming the ID is unique

            System.out.println("Student found:");
            System.out.println(studentToUpdateOrDelete);

            System.out.println("Do you want to update (U) or delete (D) this student?");
            String choice = sc.nextLine().trim().toUpperCase();

            if (choice.equals("U")) {
                managers.updateStudent(studentToUpdateOrDelete);
            } else if (choice.equals("D")) {
                managers.deleteStudent(studentToUpdateOrDelete);
            } else {
                System.out.println("Invalid choice. No action taken.");
            }
        } else {
            System.out.println("Student with ID " + studentID + " not found.");
        }
    }

    public static void report(ArrayList<Students> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Report> lr = new ArrayList<>();
        int size = ls.size();
        for (int i = 0; i < size; i++) {
            int total = 0;
            for (Students student : ls) {
                String id = student.getID();
                String courseName = student.getCourse();
                String studentName = student.getName();
                for (Students studentCountTotal : ls) {
                    if (id.equalsIgnoreCase(studentCountTotal.getID())
                            && courseName.equalsIgnoreCase(studentCountTotal.getCourse())) {
                        total++;
                    }
                }
                if (Validation.checkReportExist(lr, studentName,
                        courseName, total)) {
                    lr.add(new Report(student.getName(), studentName, total));
                }
            }
        }
        //print report
        for (int i = 0; i < lr.size(); i++) {
            System.out.printf("%-15s|%-10s|%-5d\n", lr.get(i).getStudentName(),
                    lr.get(i).getCourseName(), lr.get(i).getTotalCourse());
        }
    }

    private void createNew() {
        ArrayList<Students> studentsList = managers.getStulist();

        while (studentsList.size() < 10) {
            System.out.println("Input student information:");

            // Call the addNew method from the Management class to add a new student
            managers.addNew();

            if (studentsList.size() >= 10) {
                System.out.println("You have reached the minimum requirement of 10 students.");
                System.out.print("Do you want to continue (Y/N)? ");
                if (!Validation.checkInputYN()) {
                    break;
                }
            } else {
                System.out.println("You need to create at least " + (10 - studentsList.size()) + " more students.");
            }
        }

    }
}
