package business.designImpl;
import business.design.ManagerCustomer;
import business.entity.Customer;
import business.utils.IOFile;
import business.utils.InputMethods;
import java.util.ArrayList;
import java.util.List;

import static business.designImpl.RoleCustomerImplement.usersList;

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
        if (usersList.isEmpty()){
            System.err.println("Chưa có tài khoản USER, liên hệ admin để thêm mới tài khoản USER");
            return;
        }
        usersList = IOFile.readFromFile(IOFile.USER_PATH);
        System.out.println("Nhập số khách hàng thêm mới");
        int n = InputMethods.getInteger();
        for (int i = 0; i < n; i++) {
            Customer newCustomer = new Customer();
            System.out.println("Nhập mã KH:");
            newCustomer.inputCustomerId();
            System.out.println("Nhập tên KH:");
            newCustomer.inputCustomerName();
            System.out.println("Nhập ngày sinh (dd/MM/yyyy):");
            newCustomer.inputBirthday();
            System.out.println("Nhập giới tính (true = Nam, false = Nữ):");
            newCustomer.inputSex();
            System.out.println("Nhập địa chỉ:");
            newCustomer.inputAddress();
            System.out.println("Nhập email:");
            newCustomer.inputEmail();
            System.out.println("Nhập số điện thoại:");
            newCustomer.inputPhoneNumber();
            System.out.println("Nhập ghi chú:");
            newCustomer.inputNote();
            System.out.println("Nhập độ ưu tiên (1-3):");
            System.out.println("1. Khách hàng VIP");
            System.out.println("2. Khách hàng Tiềm năng");
            System.out.println("3. Khách hàng Bình thường");
            newCustomer.inputPriority();
            System.out.print("Hãy nhập vị trí user bạn muốn chọn : ");
            newCustomer.inputUserId(usersList);
            customerList.add(newCustomer);
        }
        // Ghi danh sách khách hàng đã cập nhật vào file
        IOFile.writeToFile(IOFile.CUSTOMER_PATH, customerList);
        System.out.println("Thêm khách hàng thành công");
    }

    @Override
    public void displayAllList() {
        customerList = IOFile.readFromFile(IOFile.CUSTOMER_PATH);
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
       while (true){
           System.out.println("Bạn muốn cập nhật thông tin gì?");
           System.out.println("1. Tên KH");
           System.out.println("2. Ngày sinh");
           System.out.println("3. Giới tính");
           System.out.println("4. Địa chỉ");
           System.out.println("5. Email");
           System.out.println("6. Số điện thoại");
           System.out.println("7. Ghi chú");
           System.out.println("8. Độ ưu tiên");
           System.out.println("9. User ");
           System.out.println("10. Thoát");
           int choice = InputMethods.getInteger();
           switch (choice) {
               case 1:
                   System.out.println("Nhập tên KH mới:");
                   updateCustomer.inputCustomerName();
                   break;
               case 2:
                   System.out.println("Nhập ngày sinh mới (dd/MM/yyyy):");
                   updateCustomer.inputBirthday();
                   break;
               case 3:
                   System.out.println("Nhập giới tính mới (true = Nam, false = Nữ):");
                   updateCustomer.inputSex();
                   break;
               case 4:
                   System.out.println("Nhập địa chỉ mới:");
                   updateCustomer.inputAddress();
                   break;
               case 5:
                   System.out.println("Nhập email mới:");
                   updateCustomer.inputEmail();
                   break;
               case 6:
                   System.out.println("Nhập số điện thoại mới:");
                   updateCustomer.inputPhoneNumber();
                   break;
               case 7:
                   System.out.println("Nhập ghi chú mới:");
                   updateCustomer.inputNote();
                   break;
               case 8:
                   System.out.println("Nhập độ ưu tiên mới(1-3) mới:");
                   System.out.println("1. Khách hàng VIP");
                   System.out.println("2. Khách hàng Tiềm năng");
                   System.out.println("3. Khách hàng Bình thường");
                   updateCustomer.inputPriority();
                   break;
               case 9:
                   System.out.print("Hãy nhập vị trí mới cho user bạn muốn chọn mới: ");
                   updateCustomer.inputUserId(usersList);
               case 10:
                   System.out.println("Thoát thành công");
                   // Ghi danh sách khách hàng đã cập nhật vào file
                   IOFile.writeToFile(IOFile.CUSTOMER_PATH, customerList);
                   System.out.println("Cập nhật thông tin khách hàng thành công");
                   return;
               default:
                   System.err.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                   break;
           }
       }
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
