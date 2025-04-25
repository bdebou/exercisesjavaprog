package be.abis.exercise.repository;

import be.abis.exercise.exception.CourseAlreadyExistsException;
import be.abis.exercise.exception.MissingTokenException;
import be.abis.exercise.exception.RepositoryException;
import be.abis.exercise.model.Course;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class FileCourseRepository extends CourseCommonRepository {
    private final Path courseFile;

    public FileCourseRepository(Path inputFile) {
        super();
        this.courseFile = inputFile;
        try (BufferedReader reader = Files.newBufferedReader(this.courseFile)) {
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null)
                super.addCourse(this.parseCourse(currentLine));
        } catch (IOException | RepositoryException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private Course parseCourse(String line) throws MissingTokenException {
        //Programming with Spring;3;525.0;21/3/2008
        String[] items = line.split(";");
        if (items.length == 4) {
            String title = items[0];
            int nbDays = Integer.parseInt(items[1]);
            double price = Double.parseDouble(items[2]);
            LocalDate releaseDate = LocalDate.parse(items[3], Course.DATE_FORMATTER);
            return new Course(title, nbDays, price, releaseDate);
        } else {
            throw new MissingTokenException("Missing token for <" + line + ">");
        }
    }

    @Override
    public void addCourse(Course c) throws CourseAlreadyExistsException {
        super.addCourse(c);
        try (BufferedWriter writer = Files.newBufferedWriter(this.courseFile, StandardOpenOption.APPEND)) {
            writer.write(c.formatCSV());
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
