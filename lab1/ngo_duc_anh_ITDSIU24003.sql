CREATE DATABASE UniversityDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE UniversityDB;

CREATE TABLE Department (
    DepartmentID VARCHAR(20) PRIMARY KEY,
    DepartmentName VARCHAR(100) NOT NULL
);

CREATE TABLE Course (
    CourseID VARCHAR(20) PRIMARY KEY,
    CourseName VARCHAR(100) NOT NULL
);

CREATE TABLE Student (
    StudentID VARCHAR(20) PRIMARY KEY,
    StudentName VARCHAR(100) NOT NULL,
    DoB DATE,
    Major VARCHAR(50)
);

-- Bảng Lecturer có quan hệ 1-N với Department
CREATE TABLE Lecturer (
    LecturerID VARCHAR(20) PRIMARY KEY,
    LecturerName VARCHAR(100) NOT NULL,
    DepartmentID VARCHAR(20),
    FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID)
        ON DELETE SET NULL 
        ON UPDATE CASCADE
);

-- Bảng Register: Giải quyết quan hệ N-N giữa Student và Course
CREATE TABLE Register (
    StudentID VARCHAR(20),
    CourseID VARCHAR(20),
    PRIMARY KEY (StudentID, CourseID),
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID)
        ON DELETE CASCADE,
    FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
        ON DELETE CASCADE
);

-- Bảng Teach: Giải quyết quan hệ N-N giữa Lecturer và Course
CREATE TABLE Teach (
    LecturerID VARCHAR(20),
    CourseID VARCHAR(20),
    PRIMARY KEY (LecturerID, CourseID),
    FOREIGN KEY (LecturerID) REFERENCES Lecturer(LecturerID)
        ON DELETE CASCADE,
    FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
        ON DELETE CASCADE
);

-- Insert dữ liệu vào các bảng độc lập trước
INSERT INTO Department (DepartmentID, DepartmentName) VALUES 
('IT', 'Information Technology'),
('DS', 'Data Science');

INSERT INTO Course (CourseID, CourseName) VALUES 
('CS101', 'Web Application Development'),
('DS201', 'Principles of Database Management');

INSERT INTO Student (StudentID, StudentName, DoB, Major) VALUES 
('ITDSIU24003', 'Ngo Duc Anh', '2005-05-15', 'Data Science'),
('ITDSIU24004', 'Nguyen Van A', '2004-02-10', 'Information Technology');

-- Insert dữ liệu vào bảng phụ thuộc sau
INSERT INTO Lecturer (LecturerID, LecturerName, DepartmentID) VALUES 
('LEC001', 'MSc. Nguyen Trung Nghia', 'IT'),
('LEC002', 'Dr. Tran Thi B', 'DS');

-- Thiết lập các mối quan hệ (Bảng trung gian)
INSERT INTO Register (StudentID, CourseID) VALUES 
('ITDSIU24003', 'CS101'),
('ITDSIU24003', 'DS201');

INSERT INTO Teach (LecturerID, CourseID) VALUES 
('LEC001', 'CS101'),
('LEC002', 'DS201');