package be.abis.exercise.repository;

import be.abis.exercise.exception.CourseAlreadyExistsException;
import be.abis.exercise.exception.CourseNotFoundException;
import be.abis.exercise.model.Course;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import javax.swing.text.DateFormatter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CourseCommonRepository implements CourseRepository {
    private final static String SEPARATOR_LINE = "----------------------------------------------------------------------------------------------";
    private final List<Course> courses = new ArrayList<>();

    @Override
    public List<Course> findAllCourses() {
        return courses;
    }

    @Override
    public void addCourse(Course c) throws CourseAlreadyExistsException {
        if (this.courses.contains(c)) {
            throw new CourseAlreadyExistsException();
        }
        courses.add(c);
    }

    @Override
    public Course findByName(String name) throws CourseNotFoundException {
        return this.courses.stream()
                .filter(c -> c.getTitle().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public Course findByID(int id) throws CourseNotFoundException {
        return this.courses.stream()
                .filter(c -> c.getCourseNumber() == id)
                .findFirst()
                .orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public String formatCourse(Course course) {
        StringBuilder sb = new StringBuilder();
        sb.append(course.getTitle()).append(';');
        sb.append(course.getDays()).append(';');
        sb.append(course.getDailyPrice()).append(';');
        sb.append(course.getReleaseDate().format(DateTimeFormatter.ofPattern("d/M/yyyy")));
        return sb.toString();
    }


    @Override
    public void printAllCourseAsTable(File targetFile) {
        if (targetFile.exists()) {
            System.out.println("The file <" + targetFile.getPath() + "> already exists and will be replaced.");
        }
        try (BufferedWriter bw = Files.newBufferedWriter(targetFile.toPath(), StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
            bw.write(this.printAllCourseAsTable(120, Locale.getDefault()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String printAllCourseAsTable() {
        return printAllCourseAsTable(80, Locale.getDefault());
    }

    @Override
    public String printAllCourseAsTable(Locale locale) {
        return printAllCourseAsTable(80, locale);
    }

    private String printAllCourseAsTable(int width, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("courseResources", locale);
        AsciiTable at = new AsciiTable();
        at.addRule();
        AT_Row title = at.addRow(null, null, bundle.getString("title.main"));
        title.setTextAlignment(TextAlignment.CENTER);
        at.addRule();
        at.addRow(bundle.getString("subtitle.col1"), bundle.getString("subtitle.col2"), bundle.getString("subtitle.col3"));
        at.addRule();
        for (Course course : this.courses) {
            at.addRow(course.getTitle()
                    , NumberFormat.getCurrencyInstance(Locale.FRANCE).format(course.getTotalPriceWithVAT())
                    , course.getReleaseDate().format(DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.FRANCE))
            );
        }
        at.addRule();
        return at.render(width);
    }

    @Override
    public String printAllCourses() {
        return printAllCourses(Locale.getDefault());
    }

    @Override
    public String printAllCourses(Locale locale) {
        return printAllCourses(locale, "MMM d, yyyy");
    }

    public String printAllCourses(Locale locale, String datePattern) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getHeader());
        for (Course course : this.courses) {
            sb.append(getFormattedOnThreeColLeftAligned(course.getTitle()))
                    .append(getFormattedOnThreeColLeftAligned(NumberFormat.getCurrencyInstance(locale).format(course.getTotalPriceWithVAT())))
                    .append(getFormattedOnThreeColRightAligned(course.getReleaseDate().format(DateTimeFormatter.ofPattern(datePattern, locale))))
                    .append("\r\n");
        }
        return sb.toString();
    }

    private String getHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append(SEPARATOR_LINE).append("\r\n");
        sb.append(getFormattedInMiddle("Course Overview")).append("\r\n");
        sb.append(SEPARATOR_LINE).append("\r\n");
        sb.append(getFormattedOnThreeColLeftAligned("Course Title"))
                .append(getFormattedOnThreeColLeftAligned("Total Price with VAT"))
                .append(getFormattedOnThreeColRightAligned("Release Date"))
                .append("\r\n");
        sb.append(SEPARATOR_LINE).append("\r\n");
        return sb.toString();
    }

    private String getFormattedInMiddle(String strValue) {
        int paddingLength = (SEPARATOR_LINE.length() - strValue.length()) / 2;
        StringBuilder sb = new StringBuilder();
        sb.repeat(" ", paddingLength)
                .append(strValue);
        return sb.toString();
    }

    private static String getFormattedOnThreeColLeftAligned(String strValue) {
        int paddingThreeCol = (SEPARATOR_LINE.length() / 3) * -1;
        return getFormattedOnThreeCol(strValue, paddingThreeCol);
    }

    private static String getFormattedOnThreeColRightAligned(String strValue) {
        int paddingThreeCol = SEPARATOR_LINE.length() / 3;
        return getFormattedOnThreeCol(strValue, paddingThreeCol);
    }

    private static String getFormattedOnThreeCol(String strValue, int paddingValue) {
        return String.format("%" + paddingValue + "s", strValue);
    }
}
