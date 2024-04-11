package presentation;

import business.design.*;

import business.designImpl.*;
import business.utils.InputMethods;

public class MenuManger {
    static RoleAdmin roleAdmin = new RoleAdminImplement();
    static ManagerDepartment managerDepartment = new ManageDepartmentImpl();
    static ManageEmployee manageEmployee = new ManageEmployeeImplement();
    static ManagerCustomer managerCustomer = new ManageCustomerImpl();
    static ManagerContract managerContract = new ManageContractImpl();
    static ManageProject manageProject = new ManageProjectImpl();
    public void displayMenuManager() {
        while (true) {

            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"====================Menu quản lý===================="+Main.ANSI_RESET);
            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"1. Quản lý Phòng ban"+Main.ANSI_RESET);
            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"2. Quản lý nhân viên"+Main.ANSI_RESET);
            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"3. Quản lý khách hàng"+Main.ANSI_RESET);
            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"4. Quản lý hợp đồng"+Main.ANSI_RESET);
            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"5. Quản lý dự án"+Main.ANSI_RESET);
            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"6. Đăng xuât"+Main.ANSI_RESET);
            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"--------------------"+Main.ANSI_RESET);
            System.out.print(Main.ANSI_CYAN+Main.BLACK_BG+"Chọn chức năng: "+Main.ANSI_RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    displayMenuDepartment();
                    break;
                case 2:

                    displayMenuEmployee();
                    break;
                case 3:

                    displayMenuCustomer();
                    break;
                case 4:

                    displayMenuContract();
                    break;
                case 5:
                    displayMenuProject();
                    break;
                case 6:
                    roleAdmin.logout();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }
            if(choice == 6) {
                break;
            }

        }


    }
    private void displayMenuDepartment() {
        while (true){
            // Code hiển thị menu quản lý nhân viên
            System.out.println("====================");
            System.out.println("====================Menu quản lý phòng ban====================");
            System.out.println("1. Thêm mới phòng ban");
            System.out.println("2. Xem danh sách phòng ban");
            System.out.println("3. Cập nhật thông tin phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("5. Quay lại menu chính");
            System.out.println("--------------------");
            System.out.print("Chọn chức năng: ");
            byte choice = InputMethods.getByte();

            switch (choice){
                case 1:
                    managerDepartment.addNew();
                    break;
                case 2:
                    managerDepartment.displayAllList();
                    break;
                case 3:
                    managerDepartment.update();
                    break;
                case 4:
                    managerDepartment.deleteDepartment();
                    break;
                case 5:
                    System.out.println("QUay lại menu chính");
                    return;
                default:
                    System.err.println("KHông hợp lệ vui lòng nhập từ 1-5");

            }
        }
    }
    private void displayMenuEmployee() {
      while (true){
          System.out.println("====================");
          System.out.println("====================Menu quản lý nhân viên====================");
          System.out.println("1. Thêm mới nhân viên");
          System.out.println("2. Xem danh sách nhân viên");
          System.out.println("3. Cập nhật thông tin nhân viên");
          System.out.println("4. Xóa nhân viên");
          System.out.println("5. Tìm kiếm nhân viên theo tên");
          System.out.println("6. Quay lại menu chính");
          System.out.println("--------------------");
          System.out.print("Chọn chức năng: ");
          byte choice = InputMethods.getByte();
          switch (choice){
              case 1:
                  manageEmployee.addNew();
                  break;
              case 2:
                  manageEmployee.displayAllList();
                  break;
              case 3:
                  manageEmployee.update();
                  break;
              case 4:
                  manageEmployee.deleteEmployee();
                  break;
              case 5:
                  manageEmployee.searchEmployeeByName();
                  break;
              case 6:
                  System.out.println("QUay lại menu chính");
                  return;
              default:
                  System.err.println("KHông hợp lệ vui lòng nhập từ 1-6");

          }
      }
    }

    private void displayMenuCustomer() {
        while (true){
            System.out.println("====================");
            System.out.println("====================Menu quản lý khách hàng====================");
            System.out.println("2. Xem danh sách khách hàng");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("5. Tìm kiếm khách hàng theo tên");
            System.out.println("6. Quay lại menu chính");
            System.out.println("--------------------");
            System.out.print("Chọn chức năng: ");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    managerCustomer.addNew();
                    break;
                case 2:
                    managerCustomer.displayAllList();
                    break;
                case 3:
                    managerCustomer.update();
                    break;
                case 4:
                    managerCustomer.deleteCustomer();
                    break;
                case 5:
                    managerCustomer.searchCustomerByName();
                    break;
                case 6:
                    System.out.println("QUay lại menu chính");
                    return;
                default:
                    System.err.println("KHông hợp lệ vui lòng nhập từ 1-6");

            }
        }
    }

    private void displayMenuContract() {
        while (true){
            System.out.println("====================");
            System.out.println("====================Menu quản lý hợp đồng====================");
            System.out.println("1. Thêm mới hợp đồng");
            System.out.println("2. Xem danh sách hợp đồng");
            System.out.println("3. Cập nhật thông tin hợp đồng");
            System.out.println("4. Xóa hợp đồng");
            System.out.println("5. Tìm kiếm hợp đồng theo tên");
            System.out.println("6. Quay lại menu chính");
            System.out.println("--------------------");
            System.out.print("Chọn chức năng: ");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    managerContract.addNew();
                    break;
                case 2:
                    managerContract.displayAllList();
                    break;
                case 3:
                    managerContract.update();
                    break;
                case 4:
                    managerContract.deleteContract();
                    break;
                case 5:
                    managerContract.searchContractByName();
                    break;
                case 6:
                    System.out.println("QUay lại menu chính");
                    return;
                default:
                    System.err.println("KHông hợp lệ vui lòng nhập từ 1-6");

            }
        }
    }

    private void displayMenuProject() {
        while (true){
            System.out.println("====================");
            System.out.println("**Menu quản lý dự án**");
            System.out.println("1. Thêm mới dự án");
            System.out.println("2. Xem danh sách dự án");
            System.out.println("3. Cập nhật thông tin dự án");
            System.out.println("4. Cập nhật dự án");
            System.out.println("5. Quay lại menu chính");
            System.out.println("--------------------");
            System.out.print("Chọn chức năng: ");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    manageProject.addNew();
                    break;
                case 2:
                    manageProject.displayAllList();
                    break;
                case 3:
                    manageProject.update();
                    break;
                case 4:
                    manageProject.updateProjectStatus();
                    break;
                case 5:
                    System.out.println("QUay lại menu chính");
                    return;
                default:
                    System.err.println("KHông hợp lệ vui lòng nhập từ 1-6");

            }
        }
    }

}
