# Student Management System

A comprehensive desktop application built with Java Swing and MySQL that provides complete academic management solutions for educational institutions.

## 🚀 Quick Start

**⚠️ Important: This is Sprint #5 implementation. Please checkout to the appropriate branch before running:**

```bash
git checkout sprint#5
```

## 📋 Features

### 🔐 Authentication & Security

- **Role-based Access Control**: Admin, Teacher, and Student roles
- **Secure Login**: SHA-256 password encryption with salt
- **Session Management**: Automatic user session handling

### 👥 User Management

- **Admin Dashboard**: Complete system administration
- **User Account Creation**: Bulk user account management
- **Account Display & Management**: View and modify user accounts

### 🎓 Academic Management

- **Class Management**: Create, update, and manage classes
- **Student Enrollment**: Enroll students in classes
- **Attendance Tracking**: Real-time attendance with analytics charts
- **Grade Management**: Comprehensive grade tracking with Excel import/export

### 📚 Course & Curriculum

- **Course Management**: Manage course offerings
- **Curriculum Tracking**: Monitor academic progress
- **Syllabus Management**: Course syllabus and materials
- **Schedule Management**: Class schedules and timetables

### 📝 Online Examination System

- **Quiz Interface**: Interactive quiz system
- **Timer Functionality**: Countdown timer for exams
- **Lifeline Features**: 50-50 option to eliminate wrong answers
- **Auto-submit**: Automatic submission when time expires
- **Score Calculation**: Automated scoring and results

### 💬 Communication

- **Real-time Chat**: AI-powered chat system for student support
- **Messaging System**: Internal communication platform

### 📊 Reporting & Analytics

- **Attendance Charts**: Visual attendance analytics using JFreeChart
- **Performance Reports**: Student performance tracking
- **Excel Integration**: Import/export data using Apache POI
- **Credits Performance**: Track student credit completion

### 🎨 User Interface

- **Modern Design**: Gradient panels and custom UI components
- **Responsive Layout**: Adaptive interface design
- **Dashboard Views**: Role-specific dashboards
- **Custom Components**: Gradient buttons and date labels

## 🛠️ Technology Stack

- **Frontend**: Java Swing, AWT
- **Backend**: Java Core, JDBC
- **Database**: MySQL
- **Libraries**:
  - `mysql-connector-j-8.0.33.jar` - Database connectivity
  - `jfreechart-1.5.0.jar` - Chart generation
  - `Apache POI` - Excel file handling
  - `Gson` - JSON processing
  - `KGradientPanel.jar` - Custom UI components

## 📁 Project Structure

```
src/
├── model/              # Data models
│   ├── Student.java
│   ├── Classroom.java
│   ├── CreditsPerformance.java
│   └── ClassesManager.java
├── view/               # User interface
│   ├── Admin/          # Admin views
│   ├── Student/        # Student views
│   ├── Teacher/        # Teacher views
│   └── style/          # Custom UI components
├── controller/         # Business logic
│   ├── Admin/          # Admin controllers
│   ├── Student/        # Student controllers
│   ├── Teacher/        # Teacher controllers
│   └── DatabaseConnection.java
└── Assert/             # Resources and libraries
```

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher
- MySQL Server
- Eclipse IDE (recommended)

### Installation

1. **Clone the repository**

   ```bash
   git clone <repository-url>
   cd StudentManagementSystem
   ```

2. **Checkout Sprint #5**

   ```bash
   git checkout sprint#5
   ```

3. **Database Setup**

   - Create MySQL database: `spmdatabase11`
   - Import database schema
   - Update connection details in `DatabaseConnection.java`

4. **Library Setup**

   - Ensure all JAR files are in `src/Assert/Library/`
   - Configure Eclipse project classpath

5. **Run the Application**
   ```bash
   # From Eclipse: Run LoginView.java
   # Or compile and run:
   javac -cp "src/Assert/Library/*" src/view/Admin/LoginView.java
   java -cp "src/Assert/Library/*:bin" view.Admin.LoginView
   ```

## 👥 User Roles

### Admin

- Create and manage user accounts
- View system analytics and reports
- Manage classes and academic settings
- Access attendance rate charts

### Teacher

- Manage assigned classes
- Take attendance
- Input and manage grades
- View student performance
- Access teaching schedule

### Student

- View personal dashboard
- Check class schedule
- Take online examinations
- View grades and transcripts
- Access course materials
- Use chat support system

## 🔧 Configuration

### Database Connection

Update connection details in `src/controller/DatabaseConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/spmdatabase11";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

### Default Credentials

- **Admin**: Check database `accounts` table
- **Teacher**: Check database `accounts` table
- **Student**: Check database `accounts` table

## 📊 Key Features in Detail

### Online Examination System

- **Question Management**: Store questions in database
- **Timer Control**: Configurable exam duration
- **Answer Validation**: Automatic answer checking
- **Results Display**: Immediate score calculation

### Attendance Management

- **Real-time Tracking**: Live attendance updates
- **Analytics**: Visual charts and statistics
- **Reports**: Export attendance data

### Grade Management

- **Excel Integration**: Import/export grades
- **Grade Calculation**: Automated score computation
- **Transcript Generation**: Academic record management

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Failed**

   - Check MySQL server status
   - Verify connection credentials
   - Ensure database exists

2. **Class Not Found Errors**

   - Verify all JAR files are in classpath
   - Check Eclipse project build path

3. **Login Issues**
   - Verify user credentials in database
   - Check password encryption

## 📝 Development Notes

- **Architecture**: MVC (Model-View-Controller) pattern
- **Database**: MySQL with JDBC
- **UI Framework**: Java Swing with custom components
- **Build Tool**: Apache Ant (build.xml included)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

For support and questions:

- Create an issue in the repository
- Contact the development team
- Check the documentation

---

**Note**: This is Sprint #5 implementation. Make sure to checkout the correct branch before running the application.



