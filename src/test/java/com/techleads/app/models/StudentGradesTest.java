package com.techleads.app.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentGradesTest {

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
    @Autowired
    ApplicationContext context;

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

    @Test
    void isGradeGreaterTrue() {
        assertThat(studentGrades.isGradeGreater(90, 75)).isTrue();

    }

    @Test
    void isGradeGreaterFalse() {
        assertThat(studentGrades.isGradeGreater(60, 75)).isFalse();

    }

    @Test
    void checkNullNotNull() {
        assertNotNull(studentGrades.checkNull(collegeStudent.getStudentGrades().getMathGradeResults()));
    }

    void createStudentWithoutGradesInit() {
        CollegeStudent student = context.getBean("collegeStudent", CollegeStudent.class);
        student.setFirstname("dill");
        student.setLastname("anupoju");
        student.setEmailAddress("dill@us.com");
        assertNotNull(student.getFirstname());
        assertNotNull(student.getLastname());
        assertNotNull(student.getEmailAddress());
        assertNotNull(studentGrades.checkNull(student.getStudentGrades()));
    }

    @Test
    void verifyTheStudentArePrototype() {
        CollegeStudent student = context.getBean("collegeStudent", CollegeStudent.class);
        assertNotSame(student, collegeStudent);
    }

    @Test
    void testFindGradePointAverage() {
        assertAll(
                () -> assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(collegeStudent.getStudentGrades().getMathGradeResults())),
                () -> assertEquals(88.31, studentGrades.findGradePointAverage(collegeStudent.getStudentGrades().getMathGradeResults()))
        );
    }
}