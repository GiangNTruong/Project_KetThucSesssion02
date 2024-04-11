package business.entity;

import business.utils.InputMethods;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private int customerId;
    private String customerName;
    private LocalDate birthday;
    private boolean sex;
    private String address;
    private String email;
    private String phoneNumber;
    private String note;
    private int priority;

    public Customer() {
    }

    public Customer(int customerId, String customerName, LocalDate birthday, boolean sex, String address, String email, String phoneNumber, String note, int priority) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.birthday = birthday;
        this.sex = sex;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.note = note;
        this.priority = priority;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    public void inputCustomerId() {
        System.out.println("Nhập mã KH:");
        this.customerId = InputMethods.getInteger();
    }

    public void inputCustomerName() {
        System.out.println("Nhập tên KH:");
        this.customerName = InputMethods.getString();
    }

    public void inputBirthday() {
        System.out.println("Nhập ngày sinh (dd/MM/yyyy):");
       this.birthday = InputMethods.getLocalDate();
    }

    public void inputSex() {
        System.out.println("Nhập giới tính (true = Nam, false = Nữ):");
        this.sex = InputMethods.getBoolean();
    }

    public void inputAddress() {
        System.out.println("Nhập địa chỉ:");
        this.address = InputMethods.getString();
    }

    public void inputEmail() {
        System.out.println("Nhập email:");
        this.email = InputMethods.getString();
    }

    public void inputPhoneNumber() {
        System.out.println("Nhập số điện thoại:");
        this.phoneNumber = InputMethods.getString();
    }

    public void inputNote() {
        System.out.println("Nhập ghi chú:");
        this.note = InputMethods.getString();
    }

    public void inputPriority() {
        System.out.println("Nhập độ ưu tiên (1-3):");
        System.out.println("1. Khách hàng VIP");
        System.out.println("2. Khách hàng Tiềm năng");
        System.out.println("3. Khách hàng Bình thường");
        int input = InputMethods.getInteger();
        switch(input) {
            case 1:
                this.priority = 1;
                break;
            case 2:
                this.priority = 2;
                break;
            case 3:
                this.priority = 3;
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Độ ưu tiên mặc định là bình thường.");
                this.priority = 3;
                break;
        }
    }


    public void displayData() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String priorityDescription = getPriorityCustomer();
        System.out.printf("Mã KH: %d | Tên KH: %s | Ngày sinh: %s | Giới tính: %s | Địa chỉ: %s | Email: %s | Số điện thoại: %s | Ghi chú: %s | Độ ưu tiên: %d - %s\n",
                customerId, customerName, (birthday != null ? birthday.format(dtf) : "N/A"),
                (sex ? "Nam" : "Nữ"),
                address, email, phoneNumber, note, priority,priorityDescription);
    }
    public String getPriorityCustomer() {
        switch (priority) {
            case 1:
                return "Khách hàng VIP";
            case 2:
                return "Khách hàng Tiềm năng";
            case 3:
                return "Khách hàng Bình thường";
            default:
                return "Không xác định";
        }
    }

}

