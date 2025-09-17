package com.example.studentManager.service;

import com.example.studentManager.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataInitializationService implements CommandLineRunner {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ClassroomService classroomService;
    private final SubjectService subjectService;
    private final EnrollmentService enrollmentService;
    private final GradeService gradeService;

    public DataInitializationService(StudentService studentService, 
                                   TeacherService teacherService,
                                   ClassroomService classroomService,
                                   SubjectService subjectService,
                                   EnrollmentService enrollmentService,
                                   GradeService gradeService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.classroomService = classroomService;
        this.subjectService = subjectService;
        this.enrollmentService = enrollmentService;
        this.gradeService = gradeService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Always initialize sample data for testing
        initializeSampleData();
    }

    public void initializeSampleData() {
        // Check if sample data already exists
        if (!teacherService.getAll().isEmpty()) {
            System.out.println("Sample data already exists, skipping initialization");
            return;
        }
        
        // Create teachers
        Teacher teacher1 = new Teacher();
        teacher1.setTeacherCode("GV001");
        teacher1.setFullName("Nguyễn Văn A");
        teacher1.setEmail("nguyenvana@example.com");
        teacher1.setDepartment("Công nghệ thông tin");
        teacherService.save(teacher1);

        Teacher teacher2 = new Teacher();
        teacher2.setTeacherCode("GV002");
        teacher2.setFullName("Trần Thị B");
        teacher2.setEmail("tranthib@example.com");
        teacher2.setDepartment("Toán học");
        teacherService.save(teacher2);

        // Create classrooms
        Classroom classroom1 = new Classroom();
        classroom1.setClassCode("CNTT01");
        classroom1.setClassName("Công nghệ thông tin 01");
        classroom1.setTeacher(teacher1);
        classroomService.save(classroom1);

        Classroom classroom2 = new Classroom();
        classroom2.setClassCode("CNTT02");
        classroom2.setClassName("Công nghệ thông tin 02");
        classroom2.setTeacher(teacher2);
        classroomService.save(classroom2);

        // Create subjects
        Subject subject1 = new Subject();
        subject1.setSubjectCode("CS101");
        subject1.setSubjectName("Lập trình Java");
        subject1.setCredit(3);
        subject1.setTeacher(teacher1);
        subjectService.save(subject1);

        Subject subject2 = new Subject();
        subject2.setSubjectCode("CS102");
        subject2.setSubjectName("Cơ sở dữ liệu");
        subject2.setCredit(3);
        subject2.setTeacher(teacher2);
        subjectService.save(subject2);

        Subject subject3 = new Subject();
        subject3.setSubjectCode("MATH101");
        subject3.setSubjectName("Toán cao cấp");
        subject3.setCredit(4);
        subject3.setTeacher(teacher2);
        subjectService.save(subject3);

        // Create students with different performance levels
        createStudentWithGrades("SV001", "Nguyễn Văn Xuất Sắc", "xuatsac@example.com", classroom1, 
                               List.of(subject1, subject2, subject3), List.of(9.0, 9.5, 8.8));

        createStudentWithGrades("SV002", "Trần Thị Khá", "kha@example.com", classroom1,
                               List.of(subject1, subject2, subject3), List.of(7.5, 8.0, 7.2));

        createStudentWithGrades("SV003", "Lê Văn Trung Bình", "trungbinh@example.com", classroom2,
                               List.of(subject1, subject2, subject3), List.of(6.0, 6.5, 5.8));

        createStudentWithGrades("SV004", "Phạm Thị Yếu", "yeu@example.com", classroom2,
                               List.of(subject1, subject2, subject3), List.of(4.0, 5.0, 4.5));

        createStudentWithGrades("SV005", "Hoàng Văn Chưa Có Điểm", "chuacodiem@example.com", classroom1,
                               List.of(subject1, subject2), List.of());

        createStudentWithGrades("SV006", "Võ Thị Tốt", "tot@example.com", classroom2,
                               List.of(subject1, subject2, subject3), List.of(8.0, 8.5, 7.8));

        createStudentWithGrades("SV007", "Đặng Văn Trung Bình 2", "trungbinh2@example.com", classroom1,
                               List.of(subject1, subject2), List.of(6.2, 5.9));

        createStudentWithGrades("SV008", "Bùi Thị Xuất Sắc 2", "xuatsac2@example.com", classroom2,
                               List.of(subject1, subject2, subject3), List.of(9.2, 9.0, 8.9));
    }

    private void createStudentWithGrades(String studentCode, String fullName, String email, 
                                       Classroom classroom, List<Subject> subjects, List<Double> grades) {
        // Create student
        Student student = new Student();
        student.setStudentCode(studentCode);
        student.setFullName(fullName);
        student.setEmail(email);
        student.setBirthday("2000-01-01");
        student.setClassroom(classroom);
        studentService.saveStudent(student);

        // Create enrollments and grades
        for (int i = 0; i < subjects.size(); i++) {
            Subject subject = subjects.get(i);
            
            // Create enrollment
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setSubject(subject);
            enrollment.setClassroom(classroom);
            enrollmentService.save(enrollment);

            // Create grade if provided
            if (i < grades.size()) {
                Grade grade = new Grade();
                grade.setEnrollment(enrollment);
                grade.setMidtermScore(grades.get(i) - 0.5); // Midterm slightly lower
                grade.setFinalScore(grades.get(i));
                grade.setTotalScore(grades.get(i)); // Total score same as final
                gradeService.save(grade);
            }
        }
    }
}
