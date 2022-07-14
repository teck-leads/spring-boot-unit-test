package com.techleads.test;

import com.techleads.app.MvcTestingExampleApplication;
import com.techleads.app.models.CollegeStudent;
import com.techleads.app.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@SpringBootTest(classes = {MvcTestingExampleApplication.class})
public class ApplicationExTestDiffPkg {

    private static int count = 0;
    @Value("${info.app.name}")
    private String appInfoName;

    @Value("${info.app.description}")
    private String description;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.school.name}")
    private String schoolName;
    @Autowired
    CollegeStudent collegeStudent;
    @Autowired
    StudentGrades studentGrades;

    @BeforeEach
    void setup() {
        count = count + 1;
        System.out.println("Testing: " + appInfoName + " which is " + description + " version " + appVersion + " Execution of test method " + count);
        collegeStudent.setFirstname("madhav");
        collegeStudent.setLastname("anupoju");
        collegeStudent.setEmailAddress("madhav@tecm.com");
        studentGrades.setMathGradeResults(Arrays.asList(100.0, 85.0, 76.50, 91.75));
        collegeStudent.setStudentGrades(studentGrades);

    }

    @Test
    void addGradeResultsForStudentGradeEquals() {

        assertThat(353.25).isEqualTo(studentGrades.addGradeResultsForSingleClass(
                collegeStudent.getStudentGrades().getMathGradeResults()
        ));

    }

    @Test
    void addGradeResultsForStudentGradesNotEquals() {

        assertThat(0).isNotEqualTo(studentGrades.addGradeResultsForSingleClass(
                collegeStudent.getStudentGrades().getMathGradeResults()
        ));

    }
}
