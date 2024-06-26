package business.entity;

import business.utils.IOFile;
import business.utils.InputMethods;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import static business.designImpl.ManageContractImpl.contractList;
import static business.designImpl.ManageEmployeeImplement.employeeList;
import static business.designImpl.ManageProjectImpl.projectList;

public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    private int projectId;
    private String projectName;
    private int contractId;
    private int leaderId;
    private int totalMember;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean status;
    private String description;
    private String technology;

    public Project() {
    }

    public Project(int projectId, String projectName, int contractId, int leaderId, int totalMember, LocalDate startDate, LocalDate endDate, boolean status, String description, String technology) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.contractId = contractId;
        this.leaderId = leaderId;
        this.totalMember = totalMember;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.description = description;
        this.technology = technology;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public int getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(int totalMember) {
        this.totalMember = totalMember;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public void inputProjectId() {
        while (true) {
            int projectId = InputMethods.getInteger();
            if (projectList.stream().anyMatch(project -> project.getProjectId()==projectId)) {
                System.out.println("ProjectId đã tồn tại, vui lòng nhập lại!");
            } else {
                this.projectId = projectId;
                break;
            }
        }
    }



    public void inputProjectName() {

        this.projectName = InputMethods.getString();
    }

    public void inputContractId(List<Contract> contractList) {
        Scanner sc = new Scanner(System.in);
        // Hiển thị danh sách hợp đồng
        for (int i = 0; i < contractList.size(); i++) {
            System.out.printf("| STT : %d | Name : %-15s |\n", i+1, contractList.get(i).getContractName());
        }
        while (true){
            System.out.println("Nhập vào vị trí hợp đồng (Theo STT)");
            int index = sc.nextInt();
            if (index >= 1 && index <= contractList.size()){
                this.contractId = contractList.get(index-1).getContractId();
                break;
            }
            System.out.println("Vị trí nhập không hợp lệ, nhập lại");
        }
    }

    public void inputLeaderId(List<Employee> employeeList) {
        Scanner sc = new Scanner(System.in);
        // Hiển thị danh sách nhân viên
        for (int i = 0; i < employeeList.size(); i++) {
            System.out.printf("| STT : %d | Name : %-15s |\n", i+1, employeeList.get(i).getEmployeeName());
        }
        while (true){
            System.out.println("Nhập vào vị trí người trưởng dự án bạn muốn  (Theo STT)");
            int index = sc.nextInt();
            if (index >= 1 && index <= employeeList.size()){
                this.leaderId = employeeList.get(index-1).getEmployeeId();
                break;
            }
            System.out.println("Vị trí nhập không hợp lệ, nhập lại");
        }
    }


    public void inputTotalMember() {

        this.totalMember = InputMethods.getInteger();
    }

    public void inputStartDate() {

        this.startDate = InputMethods.getLocalDate();
    }

    public void inputEndDate() {

        this.endDate = InputMethods.getLocalDate();
    }

    public void inputStatus() {

        this.status = InputMethods.getBoolean();
    }

    public void inputDescription() {

        this.description = InputMethods.getString();
    }

    public void inputTechnology() {

        this.technology = InputMethods.getString();
    }

    public void displayData() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //Tìm dự án theo id
        Contract contract = contractList.stream().filter(c->c.getContractId()==this.contractId).findFirst().orElse(null);
        String contractName = contract!=null?contract.getContractName():"N/A";

        //Tìm kiếm theo employee làm leader
        Employee employee = employeeList.stream().filter(e->e.getEmployeeId()==this.leaderId).findFirst().orElse(null);
        String employeeName;
        if (employee!=null){
            employeeName=employee.getEmployeeName();
        }else {
            employeeName="n/A";
        }
        System.out.printf("Mã dự án: %d | Tên dự án: %s | Hợp đồng: %d - %s | Người trưởng dự án: %d - %s | Số lượng thành viên: %d | Ngày bắt đầu: %s | Ngày kết thúc: %s | Trạng thái: %s | Mô tả: %s | Công nghệ: %s\n",
                projectId,
                projectName,
                contractId,contractName,
                leaderId,employeeName,
                totalMember,
                (startDate != null ? startDate.format(dtf) : "N/A"),
                (endDate != null ? endDate.format(dtf) : "N/A"),
                (status ? "Hoạt động" : "Không hoạt động"),
                description,
                technology);
    }
}

