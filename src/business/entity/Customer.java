package business.entity;

import business.utils.IOFile;
import business.utils.InputMethods;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Customer implements Serializable {
    static List<Users> usersList;
    static {
        usersList = IOFile.readFromFile(IOFile.CUSTOMER_PATH);
    }
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
    private int userID;


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }



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
    public void inputUserId(List<Users> users){
        System.out.println("Danh sách userList ");
        for (int i = 0; i < users.size() ; i++) {
            System.out.println((i+1)+" . " + users.get(i).getUserId() + "-" + users.get(i).getUsername() + "-" + users.get(i).getRole());
        }
        System.out.print("Hãy nhập vị trí user bạn muốn chọn : ");
       byte index = InputMethods.getByte();
        this.userID = users.get(index-1).getUserId();
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
        //\\.: Ký tự này khớp với dấu chấm (.) trong địa chỉ email.
        String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        while (true) {
            System.out.println("Nhập email:");
            this.email = InputMethods.getString();

            if (this.email.matches(regexEmail)) {
                break;
            } else {
                System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
            }
        }
    }


    public void inputPhoneNumber() {
        //nó sẽ khớp với “84” hoặc bắt đầu bằng “0” theo sau là một trong các số sau: 3, 5, 7, 8, 9.
        //+: Ký tự này đánh dấu rằng chuỗi khớp với mẫu trước đó phải xuất hiện ít nhất một lần. Điều này có nghĩa là số điện thoại phải bắt đầu bằng “84” hoặc một đầu số di động hợp lệ.
        //\\b: Ký tự này đánh dấu ranh giới của một từ, đảm bảo rằng không có chữ số nào khác sau chuỗi 8 chữ số
        String regexPhoneNumber = "^(84|0[3|5|7|8|9])+([0-9]{8})\\b";
        while (true) {
            System.out.println("Nhập số điện thoại:");
            this.phoneNumber = InputMethods.getString();

            if (this.phoneNumber.matches(regexPhoneNumber)) {
                break;
            } else {
                System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
            }
        }
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
        System.out.printf("Mã KH: %d | Tên KH: %s | Ngày sinh: %s | Giới tính: %s | Địa chỉ: %s | Email: %s | Số điện thoại: %s | Ghi chú: %s | Độ ưu tiên: %d - %s | UserId : %d \n",
                customerId, customerName, (birthday != null ? birthday.format(dtf) : "N/A"),
                (sex ? "Nam" : "Nữ"),
                address, email, phoneNumber, note, priority,priorityDescription,userID);
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

