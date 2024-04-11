package business.designImpl;

import business.design.ManageEmployee;
import business.entity.Department;
import business.entity.Employee;
import business.entity.Users;
import business.utils.IOFile;
import business.utils.InputMethods;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ManageEmployeeImplement implements ManageEmployee {
    public static List<Employee> employeeList ;
    public static List<Department> departmentList ;
    static {
        employeeList = IOFile.readFromFile(IOFile.EMPLOYEE_PATH);
        departmentList = IOFile.readFromFile(IOFile.DEPARTMENT_PATH);
        // kiem tra khoi tao khi userList null
        if (employeeList==null){
            employeeList = new ArrayList<>();
        }
        if (departmentList==null){
            departmentList = new ArrayList<>();
        }
    }
    @Override
    public void addNew() {
        if (departmentList.isEmpty()){
            System.err.println("Chưa có phòng ban, thêm phòng ban trước ");
            return;
        }
        System.out.println("Nhập bao nhiêu nhân viên thêm mới");
        byte n = InputMethods.getByte();
        for (int i = 0; i < n; i++) {
            Employee newEmployee = new Employee();
            newEmployee.setEmployeeId(getNewIdEmployee());
            System.out.println("Nhập thông tin cho nhân viên thêm mới ");
            newEmployee.inputData(employeeList,departmentList);
            employeeList.add(newEmployee);
        }
        IOFile.writeToFile(IOFile.EMPLOYEE_PATH,employeeList);
        System.out.println("Thêm mới thành công");
    }

    @Override
    public void displayAllList() {
        if (employeeList==null || employeeList.isEmpty()){
            System.err.println("Khong có nhan viên nào để hiển thị ");
            return;
        }
        System.out.println("Danh sách nhân viên");
        for (Employee employee : employeeList){
            employee.displayData();
        }
    }

    @Override
    public void update() {
        System.out.println("Nhập mã nhân viên cần cập nhật: ");
        Integer employeeId = InputMethods.getInteger();
        Employee employeeToUpdate = findById(employeeId);

        if (employeeToUpdate == null) {
            System.err.println("Không tìm thấy nhân viên với mã " + employeeId);
            return;
        }
        System.out.println("Nhập thông tin mới cho nhân viên");
        employeeToUpdate.inputData(employeeList,departmentList);

        IOFile.writeToFile(IOFile.EMPLOYEE_PATH, employeeList);
        System.out.println("Cập nhật nhân viên thành công!");
    }

    @Override
    public Employee findById(Integer id) {
        return employeeList.stream()
                .filter(employee -> employee.getEmployeeId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public void deleteEmployee() {
        System.out.println("Nhập id bạn muốn xóa  : ");
        Integer idDelete = InputMethods.getInteger();
        Employee delete = findById(idDelete);
        if (delete == null) {
            System.err.println("Id không tìm thấy");
            return;
        }
        employeeList.remove(delete);

        // ghi danh sách cập nhật vào file
        IOFile.writeToFile(IOFile.EMPLOYEE_PATH, employeeList);

        System.out.println("Xóa thành công");
    }

    @Override
    public void searchEmployeeByName() {
        System.out.println("Nhập tên nhân viên bạn muốn tìm: ");
        String searchEmployee = InputMethods.getString();

        // Tìm danh sách nhân viên theo tên
        List<Employee> filteredEmployees = employeeList.stream()
                .filter(employee -> employee.getEmployeeName().toLowerCase().contains(searchEmployee.toLowerCase()))
                .toList();

        if (filteredEmployees.isEmpty()) {
            System.err.println("Không tìm thấy nhân viên nào với tên '" + searchEmployee + "'");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Employee employee : filteredEmployees) {
                employee.displayData();
            }
        }
    }

    private int getNewIdEmployee(){
        int max = employeeList.stream().map(Employee::getEmployeeId).max(Comparator.naturalOrder()).orElse(0);
        return max+1;
    }
}
