package business.entity;

import business.utils.InputMethods;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer departmentID;
    private String departmentName ;
    private String description;

    public Department() {
    }

    public Department(Integer departmentID, String departmentName, String description) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.description = description;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void inputData(List<Department> departmentList){
        System.out.println("Nhập mã phòng ban : ");
        this.departmentID = InputMethods.getInteger();
        System.out.println("Nhập tên phòng ban :");
        this.departmentName = getInputDepartmentName(departmentList);
        System.out.println("Nhập mô tả phòng ban : ");
        this.description = InputMethods.getString();
    }
    public String getInputDepartmentName(List<Department> departmentList){
        String input = InputMethods.getString();
        while (input.isEmpty()|| isDuplicate(departmentList,input)){
            System.out.println("Tên phòng ban không hợp lệ . Tên phòng ban không được để trống và không trùng lặp , Nhập lại : ");
            input = InputMethods.getString();
        }
        return input;
    }
    public static boolean isDuplicate(List<Department> departmentList, String departmentName){
        for (Department department : departmentList){
            if (department.getDepartmentName().equals(departmentName)){
                return true;
            }
        }
        return false;
    }

    public void displayData(){
        System.out.printf("[ departmentID: %d | departmentName : %s | description: %s ] \n", this.departmentID, this.departmentName, this.description);
    }


}
