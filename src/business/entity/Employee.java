package business.entity;



import business.utils.InputMethods;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer employeeId;
    private String employeeName;
    private Integer departmentId;
    private LocalDate birthday;
    private boolean sex;
    private LocalDate createDate;
    private double salary;

    public Employee() {
    }

    public Employee(Integer employeeId, String employeeName, Integer departmentId, LocalDate birthday, boolean sex, LocalDate createDate, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.departmentId = departmentId;
        this.birthday = birthday;
        this.sex = sex;
        this.createDate = createDate;
        this.salary = salary;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public void inputData( List<Employee> employeeList, List<Department> departmentList){
        System.out.println("Nhập tên nhân viên");
        this.employeeName = InputMethods.getString();
        this.departmentId = inputDepartmentId(departmentList);
        System.out.println("Nhập ngày sinh");
        this.birthday = InputMethods.getLocalDate();
        System.out.println("Nhập (true: name) hoặc (false:nữ)");
        this.sex = InputMethods.getBoolean();
        System.out.println("Nhập ngày tham gia");
        this.createDate = InputMethods.getLocalDate();
        System.out.println("Nhập lương");
        this.salary = InputMethods.getDouble();
    }

    // Phương thức để nhập mã phòng ban
    public int inputDepartmentId(List<Department> departmentList) {
        do {
            // Hiển thị danh sách phòng ban
            System.out.println("Danh sách phòng ban:");
            for (int i = 0; i < departmentList.size(); i++) {
                System.out.println((i + 1) + ". " + departmentList.get(i).getDepartmentName());
            }
            // Yêu cầu người dùng nhập mã phòng ban
            System.out.println("Nhập mã phòng ban:");
            int inputId = InputMethods.getInteger();
            // Kiểm tra xem mã phòng ban có hợp lệ không
            if (isValidDepartmentId(departmentList, inputId)) {
                // Nếu hợp lệ, trả về mã phòng ban
                return inputId;
            } else {
                // Nếu không hợp lệ, yêu cầu người dùng nhập lại và thông báo rằng mã ID không tồn tại
                System.out.println("Mã ID không tồn tại. Nhập lại:");
            }
        } while (true);
    }


    // Phương thức để kiểm tra xem mã phòng ban có hợp lệ không
    public boolean isValidDepartmentId(List<Department> departmentList, int departmentId) {
        // Duyệt qua danh sách phòng ban
        return departmentList.stream()
                .anyMatch(department -> department.getDepartmentID().equals(departmentId));
    }

//    private void updateEmployName(Employee employee){
//        System.out.println("Nhập tên nhân viên mới");
//        String newName = InputMethods.getString();
//        employee.setEmployeeName(newName);
//    }
//    private void updateEmployeeDepartment(Employee employee, List<Department> departmentList) {
//        System.out.println("Nhập mã phòng ban mới: ");
//        byte newDepartmentId = InputMethods.getByte();
//        employee.setDepartmentId(inputDepartmentId(departmentList));
//    }



    public void displayData() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formattedSlary = formatter.format(this.salary)+" VNĐ";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("[ employeeId: %d | employeeName: %s | departmentId: %d | birthday: %s | sex: %b | createDate: %s | salary: %s ] \n",
                this.employeeId,
                this.employeeName,
                this.departmentId,
                (this.birthday != null) ? this.birthday.format(dtf) : "N/A",
                this.sex ? "Nam" : "Nữ",
                (this.createDate != null) ? this.createDate.format(dtf) : "N/A",
                formattedSlary);
    }



}
//
//
//this.departmentName = departmentList.stream()
//            .filter(department -> department.getDepartmentID().equals(this.departmentId))
//        .findFirst()
//            .map(Department::getDepartmentName)
//            .orElse("Unknown Department"); // get the department name
//
//
//public void updateEmployee() {
//    // Read employee list from file
//    employeeList = IOFile.readFromFile(IOFile.EMPLOYEE_PATH);
//
//    if (employeeList == null || employeeList.isEmpty()) {
//        System.out.println("Không có nhân viên nào để cập nhật.");
//        return;
//    }
//
//    // Get employee ID to update
//    System.out.println("Nhập mã nhân viên cần cập nhật:");
//    int updateId = InputMethods.getInteger();
//
//    // Find employee by ID
//    Employee employeeToUpdate = null;
//    for (Employee employee : employeeList) {
//        if (employee.getEmployeeId() == updateId) {
//            employeeToUpdate = employee;
//            break;
//        }
//    }
//
//    if (employeeToUpdate == null) {
//        System.out.println("Không tìm thấy nhân viên với mã " + updateId);
//        return;
//    }
//
//    // Display update options
//    System.out.println("Chọn thông tin cần cập nhật:");
//    System.out.println("1. Tên nhân viên");
//    System.out.println("2. Phòng ban");
//    System.out.println("3. Ngày sinh");
//    System.out.println("4. Giới tính");
//    System.out.println("5. Ngày tham gia");
//    System.out.println("6. Lương");
//    System.out.println("7. Thoát");
//
//    // Get user choice
//    int choice = InputMethods.getInteger();
//
//    switch (choice) {
//        case 1:
//            updateEmployeeName(employeeToUpdate);
//            break;
//        case 2:
//            updateDepartment(employeeToUpdate, departmentList);
//            break;
//        case 3:
//            updateBirthday(employeeToUpdate);
//            break;
//        case 4:
//            updateSex(employeeToUpdate);
//            break;
//        case 5:
//            updateCreateDate(employeeToUpdate);
//            break;
//        case 6:
//            updateSalary(employeeToUpdate);
//            break;
//        case 7:
//            System.out.println("Đã thoát cập nhật nhân viên.");
//            break;
//        default:
//            System.out.println("Lựa chọn không hợp lệ.");
//    }
//
//    // Write updated employee list to file
//    IOFile.writeToFile(IOFile.EMPLOYEE_PATH, employeeList);
//}
//
//private void updateEmployeeName(Employee employee) {
//    System.out.println("Nhập tên nhân viên mới:");
//    String newEmployeeName = InputMethods.getString();
//    employee.setEmployeeName(newEmployeeName);
//    System.out.println("Cập nhật tên nhân viên thành công.");
//}
//
//private void updateDepartment(Employee employee, List<Department> departmentList) {
//    int newDepartmentId = inputDepartmentId(departmentList);
//    employee.setDepartmentId(newDepartmentId);
//    System.out.println("Cập nhật phòng ban nhân viên thành công.");
//}
//
//private void updateBirthday(Employee employee) {
//    System.out.println("Nhập ngày sinh mới:");
//    LocalDate newBirthday = InputMethods.getLocalDate();
//    employee.setBirthday(newBirthday);
//    System.out.println("Cập nhật ngày sinh nhân viên thành công.");
//}
//
//private void updateSex(Employee employee) {
//    System.out.println("Nhập giới tính mới (true: Nam, false: Nữ):");
//    boolean newSex = InputMethods.getBoolean();
//    employee.setSex(newSex);
//    System.out.println("Cập nhật giới tính nhân viên thành công.");
//}
//
//private void updateCreateDate(Employee employee) {
//    System.out.println("Nhập ngày tham gia mới:");
//    LocalDate newCreateDate = InputMethods.getLocalDate();
//    employee.setCreateDate(newCreateDate);
//    System.out.println("Cập nhật ngày tham gia nhân viên thành công.");
//}
//
//private void updateSalary(Employee employee) {
//    System.out.println("Nhập lương mới:");
//    double newSalary = InputMethods.getDouble();
//    employee.setSalary(newSalary);
//    System.out.println("Cập nhật lương nhân viên thành công.");
//}
