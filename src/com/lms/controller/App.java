package com.lms.controller;

import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.*;
import com.lms.service.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        LearnerService learnerService = new LearnerService();
        Track track = new Track();
        Course course = new Course();
        CourseService courseService = new CourseService();

        while (true) {
            System.out.println("1. Add Learner");
            System.out.println("2. View All Learner");
            System.out.println("3. Delete Learner");
            System.out.println("4. Edit Learner");
            System.out.println("5. Get Learner Info by ID");
            System.out.println("6. Add Track");
            System.out.println("7. Add Course");
            System.out.println("8. View All Track");
            System.out.println("9. View All Course");
            System.out.println("0. To Exit");
            System.out.println("Enter your choice");
            int input = sc.nextInt();
            sc.nextLine();
            switch (input) {
                case 1:
                    try {
                        System.out.println("Enter name:");
                        String newName = sc.nextLine();
                        System.out.println("Enter email:");
                        String newEmail = sc.nextLine();

                        Learner learnerNew=new Learner();
                        learnerNew.setName(newName);
                        learnerNew.setEmail(newEmail);
                        learnerService.insert(learnerNew);
                        System.out.println("Learner Addedd Successfully\n");
                    }
                    catch (InvalidInputException | SQLException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                        List<Learner> list = learnerService.getAllLearners();
                        list.stream().forEach(l-> System.out.println(l));
                    break;
                case 3:
                    System.out.println("Enter Id to delete: ");
                    try{
                    learnerService.deleteById(sc.nextInt());
                        System.out.println("Deleted Learner");
                    }
                    catch (InvalidIdException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Enter Learner Id to update");
                    int editId = sc.nextInt();
                    try{
                        Learner existing=learnerService.getById(editId);
                        System.out.println("Old details: "+existing);
                        sc.nextLine();
                        System.out.println("Enter new name (press n to skip):");
                        String newName = sc.nextLine();

                        System.out.println("Enter new email (press n to skip):");
                        String newEmail = sc.nextLine();

                        Learner updatedLearner=new Learner();
                        if(newName.equals("n")) updatedLearner.setName(existing.getName());
                        else updatedLearner.setName(newName);
                        if(newEmail.equals("n")) updatedLearner.setEmail(existing.getEmail());
                        else updatedLearner.setEmail(newEmail);
                        Learner result = learnerService.update(editId, updatedLearner);
                        System.out.println("Update Successfull");
                    } catch (InvalidIdException | InvalidInputException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Enter Learner Id");
                    try {
                        Learner learner=learnerService.getById(sc.nextInt());
                        System.out.println(learner);
                    }
                    catch (InvalidIdException e) {System.out.println(e.getMessage());    }
                    break;
                case 6:
                    System.out.println("Enter track name");
                    track.setName(sc.nextLine());
                    courseService.insertTrack(track);
                    System.out.println("Track added in DB");
                    break;
                case 7:
                    System.out.println("Enter title");
                    course.setTitle(sc.nextLine());
                    System.out.println("Enter fee");
                    course.setFee(sc.nextDouble());
                    System.out.println("Enter discount");
                    course.setDisount(sc.nextDouble());
                    System.out.println("Enter Track Id");
                    int trackId = sc.nextInt();
                    courseService.insertCourse(course,trackId);
                    System.out.println("Course added in DB");
                    break;
                case 8:
                    List<Track> tracks = courseService.getAllTracks();
                    tracks.stream().forEach(l-> System.out.println(l));
                    break;
                case 9:
                    List<Course> courses = courseService.getAllCourse();
                    courses.stream().forEach(l-> System.out.println(l));
                    break;
                case 0:
                    return;
            }
        }
    }
}
