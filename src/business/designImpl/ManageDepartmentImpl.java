package business.designImpl;

import business.design.ManagerDepartment;
import business.entity.Department;
import business.utils.IOFile;
import business.utils.InputMethods;

import java.util.ArrayList;
import java.util.List;

public class ManageDepartmentImpl implements ManagerDepartment {
    public static List<Department> departmentList ;
    static {
        departmentList = IOFile.readFromFile(IOFile.DEPARTMENT_PATH);
        if (departmentList==null){
            departmentList=new ArrayList<>();
        }
    }
    @Override
    public void addNew() {
        System.out.println("Nhập số phòng ban bạn muốn thêm");
        byte n = InputMethods.getByte();
        for (int i = 0; i < n; i++) {
            Department newDepartment = new Department();
            System.out.println("Nhập thông tin cho phòng ban thêm mới ");
            newDepartment.inputData(departmentList);
            departmentList.add(newDepartment);
        }
        IOFile.writeToFile(IOFile.DEPARTMENT_PATH,departmentList);
        System.out.println("Thêm thành công");
    }

    @Override
    public void displayAllList() {
        departmentList = IOFile.readFromFile(IOFile.DEPARTMENT_PATH);
        if (departmentList==null || departmentList.isEmpty()){
            System.err.println("KHông có phòng ban nào để hiển thị ");
            return;
        }
        System.out.println("Danh sách phòng ban");
        for (Department department : departmentList){
            department.displayData();
        }
    }

    @Override
    public void update() {
        System.out.println("Nhập id của phòng ban bạn muốn cập nhật");
        Integer id = InputMethods.getInteger();
        Department department = findById(id);
        if(department !=null){
            System.out.println("Bạn muốn cập nhật thông tin gì?");
            System.out.println("1. Tên phòng ban");
            System.out.println("2. Mô tả");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    System.out.println("Nhập tên phòng ban mới : ");
                    String newName = InputMethods.getString();
                    if (!Department.isDuplicate(departmentList,newName)){
                        department.setDepartmentName(newName);
                    }else {
                        System.err.println("Tên phòng ban này đã tồn tại , Vui lòng thử lại");
                    }
                    break;
                case 2:
                    System.out.println("Nhập mô tả mới:");
                    String newDescription = InputMethods.getString();
                    department.setDescription(newDescription);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
            // ghi danh sách cập nhật vào file
            IOFile.writeToFile(IOFile.DEPARTMENT_PATH, departmentList);
            System.out.println("Cập nhật thành công và đã lưu vào file.");
        }else {
            System.err.println("Không tìm thấy phòng ban với ID đã cung cấp.");
        }
    }

    @Override
    public Department findById(Integer id) {
        return departmentList.stream()
                .filter(department -> department.getDepartmentID().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteDepartment() {
        System.out.println("Nhập ID của phòng ban bạn muốn xóa");
        Integer id = InputMethods.getInteger();
        Department department = findById(id);
        if (department!=null){
            departmentList.remove(department);
            IOFile.writeToFile(IOFile.DEPARTMENT_PATH,departmentList);
            System.out.println("Xóa thành công và đã được lưu vào file ");
        }else {
            System.err.println("Không tìm thấy phòng ban ID ");
        }
    }
}
