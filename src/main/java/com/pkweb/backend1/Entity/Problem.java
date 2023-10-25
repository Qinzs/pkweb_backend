package com.pkweb.backend1.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "problem")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming problemID is an auto-incrementing primary key
    @Column(name = "problemID")
    private int problemID;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "inputFormat")
    private String inputFormat;

    @Column(name = "outputFormat")
    private String outputFormat;

    @Column(name = "sampleInput")
    private String sampleInput;

    @Column(name = "sampleOutput")
    private String sampleOutput;

    @Column(name = "difficultyLevel")
    private String difficultyLevel;

    @Column(name = "BasicCode")
    private String addBy;

    // ... Getters, setters, and other methods ...
    public int getProblemID() {
        return problemID;
    }
    public void setProblemID(int problemID) {
        this.problemID = problemID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getInputFormat() {
        return inputFormat;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getAddBy() {
        return addBy;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }


    public Long getId() {
        return (long) problemID;
    }
}
