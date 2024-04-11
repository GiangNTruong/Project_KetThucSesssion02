package business.designImpl;

import business.design.ManageProject;
import business.entity.Contract;
import business.entity.Employee;
import business.entity.Project;
import business.utils.IOFile;
import business.utils.InputMethods;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static business.designImpl.ManageContractImpl.contractList;
import static business.designImpl.ManageEmployeeImplement.employeeList;

public class ManageProjectImpl implements ManageProject {
   public static List<Project> projectList;
    static {
        projectList= IOFile.readFromFile(IOFile.PROJECT_PATH);
        if (projectList==null){
            projectList= new ArrayList<>();
        }
    }
    @Override
    public void addNew() {
        System.out.println("Nhập số lượng dự án bạn muốn thêm mới");
        byte n = InputMethods.getByte();
        for (int i = 0; i < n; i++) {
            Project newProject = new Project();
            newProject.inputProjectId();
            newProject.inputProjectName();
            newProject.inputContractId(contractList);
            newProject.inputLeaderId(employeeList);
            newProject.inputTotalMember();
            newProject.inputStartDate();
            newProject.inputEndDate();
            newProject.inputStatus();
            newProject.inputDescription();
            newProject.inputTechnology();
            projectList.add(newProject);
        }
        IOFile.writeToFile(IOFile.PROJECT_PATH,projectList);
        System.out.println("Thêm mới dự án thành công ");
    }

    @Override
    public void displayAllList() {
        projectList= IOFile.readFromFile(IOFile.PROJECT_PATH);
        if (projectList==null|| projectList.isEmpty()){
            System.err.println("Không có dự án nào để hiển thị");
            return;
        }
        System.out.println("Danh sách dự án ");
        for (Project project:projectList){
            project.displayData();
        }
    }

    @Override
    public void update() {
        System.out.println("Nhập mã dự án bạn muốn cập nhật ");
        Integer id = InputMethods.getInteger();
        Project updateProject = findById(id);
        if (updateProject==null){
            System.err.println("Mã dự án không tìm thấy");
            return;
        }
        System.out.println("Bạn muốn cập nhật thông tin gì ");
        System.out.println("1. Tên dự án ");
        System.out.println("2. Hợp đồng ");
        System.out.println("3. Người trưởng dự án");
        System.out.println("4. Số lượng thành viên ");
        System.out.println("5. Ngày bắt đầu ");
        System.out.println("6. Ngày kết thúc ");
        System.out.println("7. Trạng thái dự án");
        System.out.println("8. Mô tả dự án");
        System.out.println("9. Công nghệ dự án");
        byte choice = InputMethods.getByte();
        switch (choice){
            case 1:
                System.out.println("Nhập tên dự án mới");
                String newName = InputMethods.getString();
                updateProject.setProjectName(newName);
                break;
            case 2:
                System.out.println("Nhập hợp đồng mới");
                byte newContract = InputMethods.getByte();
                updateProject.setContractId(newContract);
                break;
            case 3:
                System.out.println("Nhập người trưởng dự án mới");
                byte newLeader = InputMethods.getByte();
                updateProject.setLeaderId(newLeader);
                break;
            case 4:
                System.out.println("Nhập số lượng thành viên trong dự án mới");
                byte newTotalMember = InputMethods.getByte();
                updateProject.setTotalMember(newTotalMember);
                break;
            case 5 :
                System.out.println("Nhập ngày bắt đầu mới");
                LocalDate newStartDate = InputMethods.getLocalDate();
                updateProject.setStartDate(newStartDate);
                break;
            case 6:
                System.out.println("Nhập ngày kết thúc mới ");
                LocalDate newEndDate = InputMethods.getLocalDate();
                updateProject.setEndDate(newEndDate);
                break;
            case 7:
                System.out.println("Nhập trạng thái dự án mới true = Hoạt động và false = Không Hoạt động  ");
                boolean newStatus = InputMethods.getBoolean();
                updateProject.setStatus(newStatus);
                break;
            case 8:
                System.out.println("Nhập mô tả mới");
                String newDescription = InputMethods.getString();
                updateProject.setDescription(newDescription);
                break;
            case 9:
                System.out.println("Nhập công nghệ mới");
                String newTechnology = InputMethods.getString();
                updateProject.setTechnology(newTechnology);
                break;
            default:
                System.err.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                break;
        }
        IOFile.writeToFile(IOFile.PROJECT_PATH,projectList);
        System.out.println("Cập nhật thành công");
    }

    @Override
    public Project findById(Integer id) {
        return projectList.stream().filter(project -> project.getProjectId()==id).findFirst().orElse(null);
    }

    @Override
    public void updateProjectStatus() {
        System.out.println("Nhập mã dự án bạn muốn cập nhật trạng thái : ");
        Integer id = InputMethods.getInteger();
        Project updateStatusProject =findById(id);
        if (updateStatusProject==null){
            System.err.println("Mã dự án không tìm thấy");
            return;
        }
        System.out.println("Nhập mã trạng thái mới cho dự án  (true = Hoạt động , false = Không hoạt động) ");
        boolean newStatus = InputMethods.getBoolean();
        updateStatusProject.setStatus(newStatus);
        IOFile.writeToFile(IOFile.PROJECT_PATH,projectList);
        System.out.println("Cập nhật trạng thái thành công");
    }
}
