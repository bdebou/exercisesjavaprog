package be.abis.exercise.model;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Course implements Comparable<Course> {
    public static final double DEFAULT_VAT = 21D;
    public static int counter = 0;
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");

    private final int courseNumber;
    private String title;
    private int days;
    private double dailyPrice;
    private LocalDate releaseDate;

    public Course() {
        courseNumber = ++counter;
    }

    public Course(String title, int days, double dailyPrice, LocalDate releaseDate) {
        this();
        this.title = title;
        this.days = days;
        this.dailyPrice = dailyPrice;
        this.releaseDate = releaseDate;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public String getTitle() {
        return title;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "title:" + this.getTitle() + " , duration:" + this.getDays() + " , price:" + this.getDailyPrice();
    }

    public String formatCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTitle()).append(';');
        sb.append(this.getDays()).append(';');
        sb.append(this.getDailyPrice()).append(';');
        sb.append(this.getReleaseDate().format(DATE_FORMATTER));
        return sb.toString();
    }

    @Override
    public int compareTo(Course o) {
        int result = this.getTitle().compareTo(o.getTitle());
        if (result == 0) {
            return this.getDays() - o.getDays();
        }
        return result;
    }

    public double getTotalPrice() {
        return this.getDailyPrice() * this.getDays();
    }

    public double getTotalPriceWithVAT() {
        return getTotalPriceWithVAT(DEFAULT_VAT);
    }

    public double getAmountOfVAT() {
        return this.getAmountOfVAT(DEFAULT_VAT);
    }

    public double getAmountOfVAT(double vat) {
        return this.getTotalPrice() * (vat / 100);
    }

    public double getTotalPriceWithVAT(double vat) {
        return getTotalPrice() + this.getAmountOfVAT(vat);
    }
}