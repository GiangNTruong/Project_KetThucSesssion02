package business.utils;

import business.entity.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    public static final String USER_PATH = "src/business/data/user.txt";
    public static final String EMPLOYEE_PATH = "src/business/data/employee.txt";
    public static final String DEPARTMENT_PATH = "src/business/data/department.txt";
    public static final String CUSTOMER_PATH = "src/business/data/customer.txt";
    public static final String CONTRACT_PATH = "src/business/data/contract.txt";
    public static final String PROJECT_PATH = "src/business/data/project.txt";
    public static final String USERLOGIN_PATH = "src/business/data/userLogin.txt";


    public static <T> void writeToFile(String path, List<T> list) {
        File file = new File(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos!=null){
                    oos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> List<T> readFromFile(String path) {
        List<T> list = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            list = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (EOFException e) {

        }catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois!=null){
                    ois.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    public static <T> void writetoUserLogin(String path, T user) {
        File file = new File(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos!=null){
                    oos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static <T> T readFromToUser(String path) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        T t =null;
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            t = (T) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (EOFException e) {

        }catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois!=null){
                    ois.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return t;
    }
}
