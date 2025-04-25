package be.abis.exercise.test;

import be.abis.exercise.exception.*;
import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.services.UnitOfWork;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TestMain4 {
    public static void main(String[] args) {
        try {
            Path coursePath = Paths.get("C:\\temp\\javacourses\\courses.csv");
            UnitOfWork unitOfWork = new UnitOfWork(coursePath);
            CourseRepository courseRepository = unitOfWork.getCourseRepository();
            System.out.println("----- 1a&b -----");
            courseRepository.findAllCourses().forEach(System.out::println);
            Course course = new Course(String.format("Bruno course _%s_", LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"))), 12, 352, LocalDate.of(2025, 5, 9));
            courseRepository.addCourse(course);
            courseRepository.findAllCourses().forEach(System.out::println);
            System.out.println("----- 2 -----");
            BasicFileAttributes data = Files.readAttributes(Paths.get("C:\\temp\\sample_data.csv"), BasicFileAttributes.class);
            System.out.println("Is path a directory? " + data.isDirectory());
            System.out.println("Is path a regular file? " + data.isRegularFile());
            System.out.println("Is path a symbolic link? " + data.isSymbolicLink());
            System.out.println("Path not a file, directory, nor symbolic link? " + data.isOther());
            System.out.println("Size (in bytes): " + FileUtils.byteCountToDisplaySize(data.size()));

            DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
            ZoneId utcZone = ZoneId.of("UTC");
            System.out.println("Creation UTC date/time: " + data.creationTime());
            ZonedDateTime creationTime = ZonedDateTime.ofInstant(data.creationTime().toInstant(), utcZone);
            System.out.println("Creation UTC date/time: " + creationTime.format(dtf));
            System.out.println("Creation date/time (withZoneSameInstant): " + creationTime.withZoneSameInstant(ZoneId.systemDefault()).format(dtf));
            System.out.println("Last modified UTC date/time: " + ZonedDateTime.ofInstant(data.lastModifiedTime().toInstant(), utcZone).withZoneSameInstant(ZoneId.systemDefault()).format(dtf));
            System.out.println("Last accessed UTC date/time: " + ZonedDateTime.ofInstant(data.lastAccessTime().toInstant(), utcZone).withZoneSameInstant(ZoneId.systemDefault()).format(dtf));
            System.out.println("Unique file identifier (if available): " + data.fileKey());

            System.out.println("----- 3 -----");
            System.out.println(coursePath);
            String fileName = coursePath.getFileName().toString();
            int idx = fileName.lastIndexOf(".");
            String targetFileName = fileName.substring(0, idx) + "2" + fileName.substring(idx);
            Path targetPath = Paths.get(coursePath.getParent().toString(), targetFileName);
            System.out.println(targetPath);
            if (targetPath.toFile().exists()) {
                System.out.println("File <" + targetPath + "> already exists. We will remove it");
                if (targetPath.toFile().delete()) {
                    System.out.println("Existing files removed");
                }else{
                    System.out.println("Failed to remove existing file.");
                }
            }
            Files.copy(coursePath, targetPath);
            System.out.println("File <" + coursePath + "> copied to <" + targetPath + ">");
            Path targetMovePath = Paths.get(targetPath.getParent().toString(), "inputfiles", targetPath.getFileName().toString());
            if (!targetMovePath.getParent().toFile().exists()) {
                if (targetMovePath.getParent().toFile().mkdirs()) {
                    System.out.println("Folders created success");
                } else {
                    System.out.println("Failed to created folders");
                }
            }
            Files.move(targetPath, targetMovePath);
            System.out.println("File <" + targetPath + "> moved to <" + targetMovePath + ">");
        } catch (RepositoryException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
