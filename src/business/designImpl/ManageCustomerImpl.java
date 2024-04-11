package business.designImpl;

import business.design.ManagerContract;
import business.design.ManagerCustomer;
import business.entity.Contract;
import business.entity.Customer;
import business.utils.IOFile;
import business.utils.InputMethods;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManageCustomerImpl implements ManagerCustomer {
    public static List<Customer> customerList;
    static {
        customerList = IOFile.readFromFile(IOFile.CUSTOMER_PATH);
        if (customerList==null){
            customerList = new ArrayList<>();
        }
    }

    @Override
    public void addNew() {
        System.out.println("Nhập số khách hàng thêm mới");
        int n = InputMethods.getInteger();
        for (int i = 0; i < n; i++) {
            Customer newCustomer = new Customer();
            newCustomer.inputCustomerId();
            newCustomer.inputCustomerName();
            newCustomer.inputBirthday();
            newCustomer.inputSex();
            newCustomer.inputAddress();
            newCustomer.inputEmail();
            newCustomer.inputPhoneNumber();
            newCustomer.inputNote();
            newCustomer.inputPriority();
            customerList.add(newCustomer);
        }
        // Ghi danh sách khách hàng đã cập nhật vào file
        IOFile.writeToFile(IOFile.CUSTOMER_PATH, customerList);
        System.out.println("Thêm khách hàng thành công");
    }

    @Override
    public void displayAllList() {
        if (customerList==null || customerList.isEmpty()){
            System.err.println("KO có khách hàng để hiển thi  ");
            return;
        }
        System.out.println("Danh sách khách hàng ");
        for (Customer customer: customerList){
            customer.displayData();
        }
    }

    @Override
    public void update() {
        System.out.println("Nhập mã KH bạn muốn cập nhật:");
        Integer id = InputMethods.getInteger();
        Customer updateCustomer = findById(id);
        if (updateCustomer == null) {
            System.err.println("Mã KH không tìm thấy");
            return;
        }
        System.out.println("Bạn muốn cập nhật thông tin gì?");
        System.out.println("1. Tên KH");
        System.out.println("2. Ngày sinh");
        System.out.println("3. Giới tính");
        System.out.println("4. Địa chỉ");
        System.out.println("5. Email");
        System.out.println("6. Số điện thoại");
        System.out.println("7. Ghi chú");
        System.out.println("8. Độ ưu tiên");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                System.out.println("Nhập tên KH mới:");
                String newName = InputMethods.getString();
                updateCustomer.setCustomerName(newName);
                break;
            case 2:
                System.out.println("Nhập ngày sinh mới (dd/MM/yyyy):");
                LocalDate newBirthday = InputMethods.getLocalDate();
                updateCustomer.setBirthday(newBirthday);
                break;
            case 3:
                System.out.println("Nhập giới tính mới (true = Nam, false = Nữ):");
                boolean newSex = InputMethods.getBoolean();
                updateCustomer.setSex(newSex);
                break;
            case 4:
                System.out.println("Nhập địa chỉ mới:");
                String newAddress = InputMethods.getString();
                updateCustomer.setAddress(newAddress);
                break;
            case 5:
                System.out.println("Nhập email mới:");
                String newEmail = InputMethods.getString();
                updateCustomer.setEmail(newEmail);
                break;
            case 6:
                System.out.println("Nhập số điện thoại mới:");
                String newPhoneNumber = InputMethods.getString();
                updateCustomer.setPhoneNumber(newPhoneNumber);
                break;
            case 7:
                System.out.println("Nhập ghi chú mới:");
                String newNote = InputMethods.getString();
                updateCustomer.setNote(newNote);
                break;
            case 8:
                System.out.println("Nhập độ ưu tiên mới(1-3):");
                System.out.println("1. Khách hàng VIP");
                System.out.println("2. Khách hàng Tiềm năng");
                System.out.println("3. Khách hàng Bình thường");
                int newPriority = InputMethods.getInteger();
                updateCustomer.setPriority(newPriority);
                break;
            default:
                System.err.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                break;
        }
        // Ghi danh sách khách hàng đã cập nhật vào file
        IOFile.writeToFile(IOFile.CUSTOMER_PATH, customerList);
        System.out.println("Cập nhật thông tin khách hàng thành công");
    }

    @Override
    public Customer findById(Integer id) {
        return customerList.stream()
                .filter(customer -> customer.getCustomerId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteCustomer() {
        System.out.println("Nhập mã khách hàng bạn muốn xóa ");
        Integer idDelete = InputMethods.getInteger();
        Customer delete = findById(idDelete);
        if (delete == null){
            System.err.println("id ko tìm thấy");
            return;
        }
        customerList.remove(delete);
        IOFile.writeToFile(IOFile.CUSTOMER_PATH,customerList);
        System.out.println("Xóa thành công ");
    }

    @Override
    public void searchCustomerByName() {
        System.out.println("Nhap ten khach hàng bạn muốn tìm");
        String searchCustomerName = InputMethods.getString();

        //TÌm danh sách theo tên
        List<Customer> filterCustomer = customerList.stream()
                .filter(customer -> customer.getCustomerName().toLowerCase().contains(searchCustomerName.toLowerCase()))
                .toList();
        if (filterCustomer.isEmpty()){
            System.err.println("Không tìm thấy khách hàng nào với tên ' "+ searchCustomerName+"'");
        }else {
            System.out.println("Kết quả tìm kiếm");
            for (Customer customer : filterCustomer){
                customer.displayData();
            }
        }
    }
}
