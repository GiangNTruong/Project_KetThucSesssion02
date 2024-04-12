package business.designImpl;

import business.design.ManagerContract;
import business.entity.Contract;
import business.entity.Customer;
import business.entity.Employee;
import business.utils.IOFile;
import business.utils.InputMethods;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static business.designImpl.ManageCustomerImpl.customerList;
import static business.designImpl.ManageEmployeeImplement.employeeList;



public class ManageContractImpl implements ManagerContract {

    public static List<Contract> contractList ;
    static {
        contractList = IOFile.readFromFile(IOFile.CONTRACT_PATH);
        if (contractList==null){
            contractList = new ArrayList<>();
        }
    }
    @Override
    public void addNew() {

        System.out.println("Nhập số lượng hợp đồng bạn muốn thêm mới");
        byte n = InputMethods.getByte();
        for (int i = 0; i < n; i++) {
            Contract newContract = new Contract();
            newContract.inputContractId();
            newContract.inputContractName();
            newContract.inputEmployeeId(employeeList);
            newContract.inputCustomerId(customerList);
            newContract.inputCreatedDate();
            newContract.inputExpiryDate();
            newContract.inputTotalAmount();
            newContract.inputDescription();
            contractList.add(newContract);
        }
        IOFile.writeToFile(IOFile.CONTRACT_PATH,contractList);
        System.out.println("Thêm hợp đồng thành công ");
    }

    @Override
    public void displayAllList() {
        contractList = IOFile.readFromFile(IOFile.CONTRACT_PATH);
        if (contractList==null||contractList.isEmpty()){
            System.err.println("Không có hợp đồng nào để hiển thị");
            return;
        }
        System.out.println("Danh sách hợp đông");
        for (Contract contract:contractList){
            contract.displayData();
        }
    }

    @Override
    public void update() {
        System.out.println("Nhập mã hợp đồng bạn cập nhật ");
        Integer id = InputMethods.getInteger();
        Contract updateContract = findById(id);
        if(updateContract == null){
            System.err.println("Mã hợp đồng không tìm thấy");
            return;
        }
        System.out.println("Bạn muốn cập nhât thông tin gì ?");
        System.out.println("1. Tên hợp đồng bạn muốn cập nhật  ");
        System.out.println("2. Nhân viên phụ trách ");
        System.out.println("3. Khách hàng ");
        System.out.println("4. Ngày kí hợp đồng ");
        System.out.println("5. Ngày hết hạn hợp đồng ");
        System.out.println("6. Tổng money hợp đồng ");
        System.out.println("7. Mô tả hợp đồng ");
        byte choice  = InputMethods.getByte();
        switch (choice){
            case 1:
                System.out.println("Nhập tên hợp đồng mới");
                updateContract.inputContractName();
                break;
            case 2:
                System.out.println("Nhập nhân viên phụ trách mới");
                updateContract.inputEmployeeId(employeeList);
                break;
            case 3:
                System.out.println("Nhập khách hàng mới ");
                updateContract.inputCustomerId(customerList);
                break;
            case 4:
                System.out.println("NHập ngày kí hợp đồng mới");
                updateContract.inputCreatedDate();
                break;
            case 5:
                System.out.println("Nhập ngày hết hạn hợp đồng ");
               updateContract.inputExpiryDate();
                break;
            case 6:
                System.out.println("Tổng money hợp đồng mới");
                updateContract.inputTotalAmount();
                break;
            case 7:
                System.out.println("Nhập mô tả mới ");
               updateContract.inputDescription();
                break;
            default:
                System.out.println("Lựa chọn ko hợp lệ . VUi lòng thử lại ");
                break;
        }
        IOFile.writeToFile(IOFile.CONTRACT_PATH,contractList);
        System.out.println("Cập nhật thông tin dự án thành công");
    }

    @Override
    public Contract findById(Integer id) {
        return contractList.stream().filter(contract -> contract.getContractId()==id).findFirst().orElse(null);
    }

    @Override
    public void deleteContract() {
        System.out.println("Nhập mã hợp đồng bạn muốn xóa : ");
        Integer id = InputMethods.getInteger();
        Contract delete = findById(id);
        if (delete==null){
            System.err.println("id ko tìm thấy");
            return;
        }
        contractList.remove(delete);
        IOFile.writeToFile(IOFile.CONTRACT_PATH,contractList);
        System.out.println("Xóa thành công");
    }

    @Override
    public void searchContractByName() {
        System.out.println("Nhập tên dự án bạn muốn xem ");
        String searchNameContract = InputMethods.getString();
        List<Contract> filterContract = contractList.stream().filter(contract -> contract.getContractName().toLowerCase().contains(searchNameContract.toLowerCase())).toList();
        if (filterContract.isEmpty()){
            System.err.println("Không tìm thấy khác hàng nào với tên " + searchNameContract);
        }else {
            System.out.println("Kết quả tìm kiếm");
            for (Contract contract : filterContract){
                contract.displayData();
            }
        }
    }
}
