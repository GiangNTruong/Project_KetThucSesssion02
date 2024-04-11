//package business.designImpl;
//
//import business.design.RoleManager;
//import business.entity.*;
//import business.utils.IOFile;
//import business.utils.InputMethods;
//import org.mindrot.jbcrypt.BCrypt;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//
//public class RoleManageIMplement implements RoleManager {
//    public static List<Department> departmentList;
//    public static List<Customer> customerList;
//
//    public static List<Users> usersList;
//    public static List<Employee> employeeList;
//    public static List<Contract> contractList;
//    public static List<Project> projectList;
//    static {
//        projectList = IOFile.readFromFile(IOFile.PROJECT_PATH);
//        departmentList = IOFile.readFromFile(IOFile.DEPARTMENT_PATH);
//        usersList = IOFile.readFromFile(IOFile.USER_PATH);
//        employeeList = IOFile.readFromFile(IOFile.EMPLOYEE_PATH);
//        customerList = IOFile.readFromFile(IOFile.CUSTOMER_PATH);
//        contractList = IOFile.readFromFile(IOFile.CONTRACT_PATH);
//        // kiem tra khoi tao khi userList null
//        if (usersList == null){
//            usersList = new ArrayList<>();
//        }
//        if (employeeList==null){
//            employeeList = new ArrayList<>();
//        }
//        if (departmentList==null){
//            departmentList = new ArrayList<>();
//        }
//        if (customerList==null){
//            customerList=new ArrayList<>();
//        }
//        if (contractList==null){
//            contractList=new ArrayList<>();
//        }
//        if (projectList==null){
//            projectList=new ArrayList<>();
//        }
//    }
//    @Override
//    public Users login(String username, String password) {
//        Users userLogin  = getUserFormUsername(username);
//        if (userLogin==null){
//            return null;
//        }
//        boolean checkLogin = BCrypt.checkpw(password,userLogin.getPassword()); // kiem tra mat khau khop hay khong
//        if (checkLogin){
//            return userLogin;
//        }
//        return null;
//    }
//
//    @Override
//    public void logout(Users currentUser) {
//
//    }
//
////    @Override
////    public void addEmployee() {
////        if (departmentList.isEmpty()){
////            System.err.println("Chưa có phòng ban, thêm phòng ban trước ");
////            return;
////        }
////        System.out.println("Nhập bao nhiêu nhân viên thêm mới");
////        byte n = InputMethods.getByte();
////        for (int i = 0; i < n; i++) {
////            Employee newEmployee = new Employee();
////            newEmployee.setEmployeeId(getNewIdEmployee());
////            System.out.println("Nhập thông tin cho nhân viên thêm mới ");
////            newEmployee.inputData(employeeList,departmentList);
////            employeeList.add(newEmployee);
////        }
////        IOFile.writeToFile(IOFile.EMPLOYEE_PATH,employeeList);
////        System.out.println("Thêm mới thành công");
////    }
////
////    @Override
////    public void viewEmployeeList() {
////        if (employeeList==null || employeeList.isEmpty()){
////            System.err.println("Khong có nhan viên nào để hiển thị ");
////            return;
////        }
////        System.out.println("Danh sách nhân viên");
////        for (Employee employee : employeeList){
////            employee.displayData();
////        }
////    }
////
////    @Override
////    public void updateEmployee() {
////        System.out.println("Nhập mã nhân viên cần cập nhật: ");
////        byte employeeId = InputMethods.getByte();
////        Employee employeeToUpdate = findEmployeeById(employeeId);
////
////        if (employeeToUpdate == null) {
////            System.err.println("Không tìm thấy nhân viên với mã " + employeeId);
////            return;
////        }
////        System.out.println("Nhập thông tin mới cho nhân viên");
////        employeeToUpdate.inputData(employeeList,departmentList);
////
////        IOFile.writeToFile(IOFile.EMPLOYEE_PATH, employeeList);
////        System.out.println("Cập nhật nhân viên thành công!");
////    }
////    private Employee findEmployeeById(int employeeId) {
////        return employeeList.stream()
////                .filter(employee -> employee.getEmployeeId() == employeeId)
////                .findFirst().orElse(null);
////    }
////
////    @Override
////    public void deleteEmployee() {
////        System.out.println("Nhập id bạn muốn xóa  : ");
////        byte idDelete = InputMethods.getByte();
////        Employee delete = findEmployeeById(idDelete);
////        if (delete == null) {
////            System.err.println("Id không tìm thấy");
////            return;
////        }
////        employeeList.remove(delete);
////
////        // ghi danh sách cập nhật vào file
////        IOFile.writeToFile(IOFile.EMPLOYEE_PATH, employeeList);
////
////        System.out.println("Xóa thành công");
////    }
////
////    @Override
////    public void searchEmployeeByName() {
////        System.out.println("Nhập tên nhân viên bạn muốn tìm: ");
////        String searchEmployee = InputMethods.getString();
////
////        // Tìm danh sách nhân viên theo tên
////        List<Employee> filteredEmployees = employeeList.stream()
////                .filter(employee -> employee.getEmployeeName().toLowerCase().contains(searchEmployee.toLowerCase()))
////                .toList();
////
////        if (filteredEmployees.isEmpty()) {
////            System.err.println("Không tìm thấy nhân viên nào với tên '" + searchEmployee + "'");
////        } else {
////            System.out.println("Kết quả tìm kiếm:");
////            for (Employee employee : filteredEmployees) {
////                employee.displayData();
////            }
////        }
////    }
//
////    @Override
////    public void viewProject() {
////        if (projectList==null|| projectList.isEmpty()){
////            System.err.println("Không có dự án nào để hiển thị");
////            return;
////        }
////        System.out.println("Danh sách dự án ");
////        for (Project project:projectList){
////            project.displayData();
////        }
////    }
////
////    @Override
////    public void createProject() {
////        System.out.println("Nhập số lượng dự án bạn muốn thêm mới");
////        byte n = InputMethods.getByte();
////        for (int i = 0; i < n; i++) {
////            Project newProject = new Project();
////            newProject.inputProjectId();
////            newProject.inputProjectName();
////            newProject.inputContractId(contractList);
////            newProject.inputLeaderId(employeeList);
////            newProject.inputTotalMember();
////            newProject.inputStartDate();
////            newProject.inputEndDate();
////            newProject.inputStatus();
////            newProject.inputDescription();
////            newProject.inputTechnology();
////            projectList.add(newProject);
////        }
////        IOFile.writeToFile(IOFile.PROJECT_PATH,projectList);
////        System.out.println("Thêm mới dự án thành công ");
////    }
////
////    @Override
////    public void updateProjectInfo() {
////        System.out.println("Nhập mã dự án bạn muốn cập nhật ");
////        byte id = InputMethods.getByte();
////        Project updateProject = findByIdProject(id);
////        if (updateProject==null){
////            System.err.println("Mã dự án không tìm thấy");
////            return;
////        }
////        System.out.println("Bạn muốn cập nhật thông tin gì ");
////        System.out.println("1. Tên dự án ");
////        System.out.println("2. Hợp đồng ");
////        System.out.println("3. Người trưởng dự án");
////        System.out.println("4. Số lượng thành viên ");
////        System.out.println("5. Ngày bắt đầu ");
////        System.out.println("6. Ngày kết thúc ");
////        System.out.println("7. Trạng thái dự án");
////        System.out.println("8. Mô tả dự án");
////        System.out.println("9. Công nghệ dự án");
////        byte choice = InputMethods.getByte();
////        switch (choice){
////            case 1:
////                System.out.println("Nhập tên dự án mới");
////                String newName = InputMethods.getString();
////                updateProject.setProjectName(newName);
////                break;
////            case 2:
////                System.out.println("Nhập hợp đồng mới");
////                byte newContract = InputMethods.getByte();
////                updateProject.setContractId(newContract);
////                break;
////            case 3:
////                System.out.println("Nhập người trưởng dự án mới");
////                byte newLeader = InputMethods.getByte();
////                updateProject.setLeaderId(newLeader);
////                break;
////            case 4:
////                System.out.println("Nhập số lượng thành viên trong dự án mới");
////                byte newTotalMember = InputMethods.getByte();
////                updateProject.setTotalMember(newTotalMember);
////                break;
////            case 5 :
////                System.out.println("Nhập ngày bắt đầu mới");
////                LocalDate newStartDate = InputMethods.getLocalDate();
////                updateProject.setStartDate(newStartDate);
////                break;
////            case 6:
////                System.out.println("Nhập ngày kết thúc mới ");
////                LocalDate newEndDate = InputMethods.getLocalDate();
////                updateProject.setEndDate(newEndDate);
////                break;
////            case 7:
////                System.out.println("Nhập trạng thái dự án mới true = Hoạt động và false = Không Hoạt động  ");
////                boolean newStatus = InputMethods.getBoolean();
////                updateProject.setStatus(newStatus);
////                break;
////            case 8:
////                System.out.println("Nhập mô tả mới");
////                String newDescription = InputMethods.getString();
////                updateProject.setDescription(newDescription);
////                break;
////            case 9:
////                System.out.println("Nhập công nghệ mới");
////                String newTechnology = InputMethods.getString();
////                updateProject.setTechnology(newTechnology);
////                break;
////            default:
////                System.err.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
////                break;
////        }
////        IOFile.writeToFile(IOFile.PROJECT_PATH,projectList);
////        System.out.println("Cập nhật thành công");
////    }
////    public Project findByIdProject(int id){
////        return projectList.stream().filter(project -> project.getProjectId()==id).findFirst().orElse(null);
////    }
////
////    @Override
////    public void updateProjectStatus() {
////        System.out.println("Nhập mã dự án bạn muốn cập nhật trạng thái : ");
////        byte id = InputMethods.getByte();
////        Project updateStatusProject =findByIdProject(id);
////        if (updateStatusProject==null){
////            System.err.println("Mã dự án không tìm thấy");
////            return;
////        }
////        System.out.println("Nhập mã trạng thái mới cho dự án  (true = Hoạt động , false = Không hoạt động) ");
////        boolean newStatus = InputMethods.getBoolean();
////        updateStatusProject.setStatus(newStatus);
////        IOFile.writeToFile(IOFile.PROJECT_PATH,projectList);
////        System.out.println("Cập nhật trạng thái thành công");
////    }
//
////    @Override
////    public void viewContract() {
////        if (contractList==null||contractList.isEmpty()){
////            System.err.println("Không có hợp đồng nào để hiển thị");
////            return;
////        }
////        System.out.println("Danh sách khách hàng");
////        for (Contract contract:contractList){
////            contract.displayData();
////        }
////    }
////
////    @Override
////    public void createContract() {
////        System.out.println("Nhập số lượng hợp đồng bạn muốn thêm mới");
////        byte n = InputMethods.getByte();
////        for (int i = 0; i < n; i++) {
////            Contract newContract = new Contract();
////            newContract.inputContractId();
////            newContract.inputContractName();
////            newContract.inputEmployeeId(employeeList);
////            newContract.inputCustomerId(customerList);
////            newContract.inputCreatedDate();
////            newContract.inputExpiryDate();
////            newContract.inputTotalAmount();
////            newContract.inputDescription();
////            contractList.add(newContract);
////        }
////        IOFile.writeToFile(IOFile.CONTRACT_PATH,contractList);
////        System.out.println("Thêm hợp đồng thành công ");
////    }
////    @Override
////    public void updateContractInfo() {
////        System.out.println("Nhập mã hợp đồng bạn cập nhật ");
////        byte id = InputMethods.getByte();
////        Contract updateContract = findByIdContract(id);
////        if(updateContract == null){
////            System.err.println("Mã hợp đồng không tìm thấy");
////            return;
////        }
////        System.out.println("Bạn muốn cập nhât thông tin gì ?");
////        System.out.println("1. Tên hợp đồng bạn muốn cập nhật  ");
////        System.out.println("2. Nhân viên phụ trách ");
////        System.out.println("3. Khách hàng ");
////        System.out.println("4. Ngày kí hợp đồng ");
////        System.out.println("5. Ngày hết hạn hợp đồng ");
////        System.out.println("6. Tổng money hợp đồng ");
////        System.out.println("7. Mô tả hợp đồng ");
////        byte choice  = InputMethods.getByte();
////        switch (choice){
////            case 1:
////                System.out.println("Nhập tên hợp đồng mới");
////                String newName = InputMethods.getString();
////                updateContract.setContractName(newName);
////                break;
////            case 2:
////                System.out.println("Nhập nhân viên phụ trách mới");
////                byte newEmployee = InputMethods.getByte();
////                updateContract.setCustomerId(newEmployee);
////                break;
////            case 3:
////                System.out.println("Nhập khách hàng mới ");
////                byte newCusomer = InputMethods.getByte();
////                updateContract.setCustomerId(newCusomer);
////                break;
////            case 4:
////                System.out.println("NHập ngày kí hợp đồng mới");
////                LocalDate newCreateDate = InputMethods.getLocalDate();
////                updateContract.setCreatedDate(newCreateDate);
////                break;
////            case 5:
////                System.out.println("Nhập ngày hết hạn hợp đồng ");
////                LocalDate newExpiryDate = InputMethods.getLocalDate();
////                updateContract.setExpiryDate(newExpiryDate);
////                break;
////            case 6:
////                System.out.println("Tổng money hợp đồng mới");
////                double newTotalAmount = InputMethods.getDouble();
////                updateContract.setTotalAmount(newTotalAmount);
////                break;
////            case 7:
////                System.out.println("Nhập mô tả mới ");
////                String newDescription = InputMethods.getString();
////                updateContract.setDescription(newDescription);
////                break;
////            default:
////                System.out.println("Lựa chọn ko hợp lệ . VUi lòng thử lại ");
////                break;
////        }
////        IOFile.writeToFile(IOFile.CONTRACT_PATH,contractList);
////        System.out.println("Cập nhật thông tin dự án thành công");
////    }
////    public Contract findByIdContract(int id){
////        return contractList.stream().filter(contract -> contract.getContractId()==id).findFirst().orElse(null);
////    }
////
////    @Override
////    public void deleteContract() {
////        System.out.println("Nhập mã hợp đồng bạn muốn xóa : ");
////        byte id = InputMethods.getByte();
////        Contract delete = findByIdContract(id);
////        if (delete==null){
////            System.err.println("id ko tìm thấy");
////            return;
////        }
////        contractList.remove(delete);
////        IOFile.writeToFile(IOFile.CONTRACT_PATH,contractList);
////        System.out.println("Xóa thành công");
////    }
////
////    @Override
////    public void searchContractByName() {
////        System.out.println("Nhập tên dự án bạn muốn xem ");
////        String searchNameContract = InputMethods.getString();
////        List<Contract> filterContract = contractList.stream().filter(contract -> contract.getContractName().toLowerCase().contains(searchNameContract.toLowerCase())).toList();
////        if (filterContract.isEmpty()){
////            System.err.println("Không tìm thấy khác hàng nào với tên " + searchNameContract);
////        }else {
////            System.out.println("Kết quả tìm kiếm");
////            for (Contract contract : filterContract){
////                contract.displayData();
////            }
////        }
////    }
//
////    //Khách hàng
////    @Override
////    public void addCustomer() {
////        System.out.println("Nhập số khách hàng thêm mới");
////        int n = InputMethods.getInteger();
////        for (int i = 0; i < n; i++) {
////            Customer newCustomer = new Customer();
////            newCustomer.inputCustomerId();
////            newCustomer.inputCustomerName();
////            newCustomer.inputBirthday();
////            newCustomer.inputSex();
////            newCustomer.inputAddress();
////            newCustomer.inputEmail();
////            newCustomer.inputPhoneNumber();
////            newCustomer.inputNote();
////            newCustomer.inputPriority();
////            customerList.add(newCustomer);
////        }
////        // Ghi danh sách khách hàng đã cập nhật vào file
////        IOFile.writeToFile(IOFile.CUSTOMER_PATH, customerList);
////        System.out.println("Thêm khách hàng thành công");
////    }
////
////    @Override
////    public void viewCustomerList() {
////        if (customerList==null || customerList.isEmpty()){
////            System.err.println("KO có khách hàng để hiển thi  ");
////            return;
////        }
////        System.out.println("Danh sách khách hàng ");
////        for (Customer customer: customerList){
////            customer.displayData();
////        }
////    }
////
////    @Override
////    public void updateCustomer() {
////        System.out.println("Nhập mã KH bạn muốn cập nhật:");
////        byte id = InputMethods.getByte();
////        Customer updateCustomer = findByIdCustomer(id);
////        if (updateCustomer == null) {
////            System.err.println("Mã KH không tìm thấy");
////            return;
////        }
////        System.out.println("Bạn muốn cập nhật thông tin gì?");
////        System.out.println("1. Tên KH");
////        System.out.println("2. Ngày sinh");
////        System.out.println("3. Giới tính");
////        System.out.println("4. Địa chỉ");
////        System.out.println("5. Email");
////        System.out.println("6. Số điện thoại");
////        System.out.println("7. Ghi chú");
////        System.out.println("8. Độ ưu tiên");
////        int choice = InputMethods.getInteger();
////        switch (choice) {
////            case 1:
////                System.out.println("Nhập tên KH mới:");
////                String newName = InputMethods.getString();
////                updateCustomer.setCustomerName(newName);
////                break;
////            case 2:
////                System.out.println("Nhập ngày sinh mới (dd/MM/yyyy):");
////                LocalDate newBirthday = InputMethods.getLocalDate();
////                updateCustomer.setBirthday(newBirthday);
////                break;
////            case 3:
////                System.out.println("Nhập giới tính mới (true = Nam, false = Nữ):");
////                boolean newSex = InputMethods.getBoolean();
////                updateCustomer.setSex(newSex);
////                break;
////            case 4:
////                System.out.println("Nhập địa chỉ mới:");
////                String newAddress = InputMethods.getString();
////                updateCustomer.setAddress(newAddress);
////                break;
////            case 5:
////                System.out.println("Nhập email mới:");
////                String newEmail = InputMethods.getString();
////                updateCustomer.setEmail(newEmail);
////                break;
////            case 6:
////                System.out.println("Nhập số điện thoại mới:");
////                String newPhoneNumber = InputMethods.getString();
////                updateCustomer.setPhoneNumber(newPhoneNumber);
////                break;
////            case 7:
////                System.out.println("Nhập ghi chú mới:");
////                String newNote = InputMethods.getString();
////                updateCustomer.setNote(newNote);
////                break;
////            case 8:
////                System.out.println("Nhập độ ưu tiên mới(1-3):");
////                System.out.println("1. Khách hàng VIP");
////                System.out.println("2. Khách hàng Tiềm năng");
////                System.out.println("3. Khách hàng Bình thường");
////                int newPriority = InputMethods.getInteger();
////                updateCustomer.setPriority(newPriority);
////                break;
////            default:
////                System.err.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
////                break;
////        }
////        // Ghi danh sách khách hàng đã cập nhật vào file
////        IOFile.writeToFile(IOFile.CUSTOMER_PATH, customerList);
////        System.out.println("Cập nhật thông tin khách hàng thành công");
////    }
////
////    public Customer findByIdCustomer(int id) {
////        return customerList.stream()
////                .filter(customer -> customer.getCustomerId() == id)
////                .findFirst()
////                .orElse(null);
////    }
////
////
////    @Override
////    public void deleteCustomer() {
////        System.out.println("Nhập mã khách hàng bạn muốn xóa ");
////        byte idDelete = InputMethods.getByte();
////        Customer delete = findByIdCustomer(idDelete);
////        if (delete == null){
////            System.err.println("id ko tìm thấy");
////            return;
////        }
////        customerList.remove(delete);
////        IOFile.writeToFile(IOFile.CUSTOMER_PATH,customerList);
////        System.out.println("Xóa thành công ");
////    }
////
////    @Override
////    public void searchCustomerByName() {
////        System.out.println("Nhap ten khach hàng bạn muốn tìm");
////        String searchCustomerName = InputMethods.getString();
////
////        //TÌm danh sách theo tên
////        List<Customer> filterCustomer = customerList.stream()
////                .filter(customer -> customer.getCustomerName().toLowerCase().contains(searchCustomerName.toLowerCase()))
////                .toList();
////        if (filterCustomer.isEmpty()){
////            System.err.println("Không tìm thấy khách hàng nào với tên ' "+ searchCustomerName+"'");
////        }else {
////            System.out.println("Kết quả tìm kiếm");
////            for (Customer customer : filterCustomer){
////                customer.displayData();
////            }
////        }
////
////    }
//
//
//    private Users getUserFormUsername(String username){
//        return usersList.stream().filter(users -> users.getUsername().equals(username)).findFirst().orElse(null);
//    }
//    private int getNewIdEmployee(){
//        int max = employeeList.stream().map(Employee::getEmployeeId).max(Comparator.naturalOrder()).orElse(0);
//        return max+1;
//    }
////
////    @Override
////    public void addDepartment() {
////        System.out.println("Nhập số phòng ban bạn muốn thêm");
////        byte n = InputMethods.getByte();
////        for (int i = 0; i < n; i++) {
////            Department newDepartment = new Department();
////            System.out.println("Nhập thông tin cho phòng ban thêm mới ");
////            newDepartment.inputData(departmentList);
////            departmentList.add(newDepartment);
////        }
////        IOFile.writeToFile(IOFile.DEPARTMENT_PATH,departmentList);
////        System.out.println("Thêm thành công");
////    }
////
////    @Override
////    public void viewDepartmentList() {
////        departmentList = IOFile.readFromFile(IOFile.DEPARTMENT_PATH);
////        if (departmentList==null || departmentList.isEmpty()){
////            System.err.println("KHông có phòng ban nào để hiển thị ");
////            return;
////        }
////        System.out.println("Danh sách phòng ban");
////        for (Department department : departmentList){
////            department.displayData();
////        }
////    }
////
////    @Override
////    public void updateDepartment() {
////        System.out.println("Nhập ID của phòng ban bạn muốn cập nhật:");
////        int id = InputMethods.getInteger();
////        for (Department department : departmentList) {
////            if (department.getDepartmentID().equals(id)) {
////                System.out.println("Bạn muốn cập nhật thông tin gì?");
////                System.out.println("1. Tên phòng ban");
////                System.out.println("2. Mô tả");
////                int choice = InputMethods.getInteger();
////                switch (choice) {
////                    case 1:
////                        System.out.println("Nhập tên phòng ban mới:");
////                        String newName = InputMethods.getString();
////                        if (!Department.isDuplicate(departmentList, newName)) {
////                            department.setDepartmentName(newName);
////                        } else {
////                            System.out.println("Tên phòng ban này đã tồn tại. Vui lòng thử lại.");
////                        }
////                        break;
////                    case 2:
////                        System.out.println("Nhập mô tả mới:");
////                        String newDescription = InputMethods.getString();
////                        department.setDescription(newDescription);
////                        break;
////                    default:
////                        System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
////                        break;
////                }
////                // ghi danh sách cập nhật vào file
////                IOFile.writeToFile(IOFile.DEPARTMENT_PATH, departmentList);
////                System.out.println("Cập nhật thành công và đã lưu vào file.");
////                return;
////            }
////        }
////        System.err.println("Không tìm thấy phòng ban với ID đã cung cấp.");
////    }
////
////
////
////    @Override
////    public void deleteDepartment() {
////        System.out.println("Chọn id bạn muốn xóa phòng ban");
////        Integer id = InputMethods.getInteger();
////        Department delete = findByIdDepartment(id);
////        if (delete == null) {
////            System.err.println("id không tìm thấy");
////            return;
////        }
////
////        // kiểm tra xem có nhân viên hay không
////        if (employeeList.stream().anyMatch(e -> e.getDepartmentId().equals(id))) {
////            // có nhân viên
////            System.err.println("Phòng ban này có nhân viên, không thể xóa");
////            return;
////        }
////
////        departmentList.remove(delete);
////        IOFile.writeToFile(IOFile.DEPARTMENT_PATH,departmentList);
////        System.out.println("Xóa thành công");
////    }
////
////    public Department findByIdDepartment(Integer id) {
////        return departmentList.stream()
////                .filter(department -> department.getDepartmentID().equals(id))
////                .findFirst()
////                .orElse(null);
////    }
////== là toán tử so sánh tham chiếu, nghĩa là nó kiểm tra xem hai biến có trỏ đến cùng một đối tượng không.
////equals() là một phương thức được định nghĩa trong lớp Object (lớp cha của tất cả các lớp trong Java) và có thể được ghi đè để kiểm tra xem hai đối tượng có “bằng nhau” theo một tiêu chí nào đó không.
////    Trong trường hợp của Integer, equals() kiểm tra xem hai số nguyên có bằng nhau không, trong khi == kiểm tra xem hai biến Integer có trỏ đến cùng một đối tượng không.
//}
