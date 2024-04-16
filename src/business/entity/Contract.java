package business.entity;

import business.utils.IOFile;
import business.utils.InputMethods;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import static business.designImpl.ManageCustomerImpl.customerList;
import static business.designImpl.ManageEmployeeImplement.employeeList;
import static business.designImpl.ManageContractImpl.contractList;


public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;
    private int contractId;
    private String contractName;
    private int employeeId;
    private int customerId;
    private LocalDate createdDate;
    private LocalDate expiryDate;
    private double totalAmount;
    private String description;
    private boolean status;



    private int priority;

    public Contract() {
    }

    public Contract(int contractId, String contractName, int employeeId, int customerId, LocalDate createdDate, LocalDate expiryDate, double totalAmount, String description, boolean status, int priority) {
        this.contractId = contractId;
        this.contractName = contractName;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.createdDate = createdDate;
        this.expiryDate = expiryDate;
        this.totalAmount = totalAmount;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void inputContractId() {
        int contractId ;
        while (true){
            contractId = InputMethods.getByte();
            boolean check = false;
            for (Contract contract : contractList){
                if (contract.getContractId()==contractId){
                    check = true;
                    break;
                }
            }
            if (check){
                System.out.println("Id đã tồn tại");
            }else {
                this.contractId = contractId;
            }
        }

    }

    public void inputContractName() {

        this.contractName = InputMethods.getString();
    }

    // Hàm để nhập mã nhân viên từ danh sách nhân viên
    public void inputEmployeeId(List<Employee> employees) {
        // Hiển thị danh sách nhân viên
        System.out.println("Nhập nhân viên phụ trách theo vị trí :");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println((i + 1) + ". " + employees.get(i).getEmployeeId() + " - " + employees.get(i).getEmployeeName());
        }
        // Nhận lựa chọn từ người dùng
        int choice = InputMethods.getInteger();
        // Đặt mã nhân viên dựa trên lựa chọn của người dùng
        this.employeeId = employees.get(choice - 1).getEmployeeId();
    }

    // Hàm để nhập mã khách hàng từ danh sách khách hàng
    public void inputCustomerId(List<Customer> customers) {
        System.out.println("Nhập khách hàng theo vị trí:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i).getCustomerId() + " - " + customers.get(i).getCustomerName());
        }

        int choice = InputMethods.getInteger();

        this.customerId = customers.get(choice - 1).getCustomerId();
    }


    public void inputCreatedDate() {

        this.createdDate = InputMethods.getLocalDate();
    }

    public void inputExpiryDate() {

       this.expiryDate =InputMethods.getLocalDate();
    }

    public void inputTotalAmount() {
        while (true) {
            try {

                this.totalAmount = InputMethods.getDouble();

                if (this.totalAmount < 0) {
                    System.err.println("Số tiền không thể âm. Vui lòng nhập lại.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.err.println("Đầu vào không hợp lệ. Vui lòng nhập một số.");
            }
        }
    }
    public void inputStatusContract(){
        this.status = InputMethods.getBoolean();
    }

    public void inputDescription() {
        this.description = InputMethods.getString();
    }


    public void displayData() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formattedTotalAmount = formatter.format(this.totalAmount)+ " VNĐ";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String priorityContract = getPriorityContract();
        int priorityContractInt = getPriorityContractInt();

        // Tìm khách hàng từ customerId
        Customer customer = customerList.stream().filter(c -> c.getCustomerId() == this.customerId).findFirst().orElse(null);

        String customerName = customer != null ? customer.getCustomerName() : "N/A";

        //Tìm kiếm nhân viên từ Id
        Employee employee = employeeList.stream().filter(e->e.getEmployeeId()==this.employeeId).findFirst().orElse(null);
        String employeeName = employee != null ? employee.getEmployeeName() : "N/A";


        System.out.printf("Mã hợp đồng: %d | Tên hợp đồng: %s | Nhân viên phụ trách: %d - %s | Khách hàng: %d - %s | Ngày kí: %s | Ngày hết hạn: %s | Tổng tiền: %s | Mô tả: %s | Trạng thái: %s | Độ ưu tiên: %d - %s |\n",
                contractId, contractName, employeeId,employeeName, customerId, customerName,
                (createdDate != null ? createdDate.format(dtf) : "N/A"),
                (expiryDate != null ? expiryDate.format(dtf) : "N/A"),
                formattedTotalAmount,
                description,(status ? "Hoạt động" : "Không hoạt động"),
                priorityContractInt, priorityContract);
    }

    private String getPriorityContract() {
        if (totalAmount >0 && totalAmount <= 200000000) {
            return "Dự án cấp 1";
        } else if (totalAmount > 200000000 && totalAmount <= 500000000) {
            return "Dự án cấp 2";
        } else if (totalAmount > 500000000 ) {
            return "Dự án cấp 3";
        } else {
            return "Không xác định";
        }
    }
    public int getPriorityContractInt() {
        if (totalAmount >0 && totalAmount <= 200000000) {
            return 1;
        } else if (totalAmount > 200000000 && totalAmount <= 500000000) {
            return 2;
        } else if (totalAmount > 500000000 ) {
            return 3;
        } else {
            return 0;
        }
    }

//    private String getPriorityContract() {
//        switch (priority) {
//            case 1:
//                return "Dự án cấp 1";
//            case 2:
//                return "Dự án cấp 2";
//            case 3:
//                return "Dự án cấp 3";
//            default:
//                return "Không xác định";
//        }
//    }


}
//    public void inputPriority() {
//        System.out.println("Chọn độ ưu tiên (càng cao thì được ưu tiên hơn):");
//        System.out.println("1. Dự án cấp 1");
//        System.out.println("2. Dự án cấp 2");
//        System.out.println("3. Dự án cấp 3");
//        int priorityChoice = InputMethods.getInteger();
//
//        switch (priorityChoice) {
//            case 1:
//                this.priority = 1;
//                break;
//            case 2:
//                this.priority = 2;
//                break;
//            case 3:
//                this.priority = 3;
//                break;
//            default:
//                System.out.println("Lựa chọn không hợp lệ. Độ ưu tiên mặc định là 1.");
//                this.priority = 3;
//                break;
//        }
//    }
