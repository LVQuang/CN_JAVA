package main;

import main.Dao.ManufactureDAO;
import main.Dao.PhoneDAO;
import main.Domain.Manufacture;
import main.Domain.Phone;

import java.util.List;

public class App
{
    private final PhoneDAO phoneDao = new PhoneDAO();

    private final ManufactureDAO manufacturerDAO = new ManufactureDAO();

    private void addPhones() {
        Phone phone1 = new Phone(1L, "Iphone", 1000, "white", "US", 100);
        Phone phone2 = new Phone(2L, "Galaxy", 1000, "black", "Korea", 500);
        Phone phone3 = new Phone(3L, "XiaoMi", 500,"black", "China", 1000);
        phoneDao.add(phone1);
        phoneDao.add(phone2);
        phoneDao.add(phone3);
    }

    private void addManufactures() {
        Manufacture manufacturer1 = new Manufacture(1L, "Apple", "Cupertino", 100000);
        Manufacture manufacturer2 = new Manufacture(2L, "Samsung", "Suwon-si", 200000);
        Manufacture manufacturer3 = new Manufacture(3L, "XiaoMi", "Beijing", 500000);
        manufacturerDAO.add(manufacturer1);
        manufacturerDAO.add(manufacturer2);
        manufacturerDAO.add(manufacturer3);
    }

    private void updatePhones() {
        Phone phone1 = phoneDao.get(1L);
        Phone phone2 = phoneDao.get(2L);
        Phone phone3 = phoneDao.get(3L);
        Manufacture manufacturer1 = manufacturerDAO.get(1L);
        Manufacture manufacturer2 = manufacturerDAO.get(2L);
        Manufacture manufacturer3 = manufacturerDAO.get(3L);
        phone1.setManufacture(manufacturer1);
        phoneDao.update(phone1);
        phone2.setManufacture(manufacturer2);
        phoneDao.update(phone2);
        phone3.setManufacture(manufacturer3);
        phoneDao.update(phone3);
    }

    private void initData() {
        addPhones();
        addManufactures();
        updatePhones();
    }

    private void showHighestPricePhone() {
        List<Phone> phoneList = phoneDao.getHighestPricePhones();
        if (phoneList != null) {
            System.out.println("The highest price phones are: ");
            for (Phone phone : phoneList)
                System.out.println(phone);;
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    private void sortByCountry() {
        List<Phone> phoneList = phoneDao.sortByCountry();
        if (phoneList != null) {
            System.out.println("The phones are sorted by country ascendingly and then by price descendingly are: ");
            for (Phone phone : phoneList)
                System.out.println(phone);;
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    private void getPhonesHigherThanPrice(Double price) {
        List<Phone> phoneList = phoneDao.getPhonesHigherThanPrice(price);
        if (phoneList != null) {
            System.out.println("The phones are more expensive than $" + price + " are: ");
            for (Phone phone : phoneList)
                System.out.println(phone);;
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    private void getPhonesWithColorAndPrice(String color, Double price) {
        List<Phone> phoneList = phoneDao.getPhonesWithColorAndPrice(color, price);
        if (phoneList != null) {
            System.out.println("The phones with color " + color + " and are more expensive than $" + price + " are: ");
            for (Phone phone : phoneList)
                System.out.println(phone);
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    private void checkManufactureEmployees(Long number) {
        if (manufacturerDAO.allHaveMoreEmployees(number)) {
            System.out.println("All manufactures have more than " + number + " employees");
        } else {
            System.out.println("Some manufacture has less than or equal to " + number + " employees");
        }
    }

    private void findTotalEmployees() {
        System.out.println("The total number of employees is: " + manufacturerDAO.getTotalEmployees());
    }

    private void findManufacturesByLocation(String location) {
        List<Manufacture> manufacturerList = manufacturerDAO.findManufacturesByLocation(location);
        if (manufacturerList != null) {
            System.out.println("All the manufactures from " + location + " are: ");
            for (Manufacture manufacturer : manufacturerList)
                System.out.println(manufacturer);
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    public static void main( String[] args )
    {
        App app = new App();
//        app.initData();
        app.showHighestPricePhone();
        // app.sortByCountry();
        // app.getPhonesHigherThanPrice(500.0);
        // app.getPhonesWithColorAndPrice("white", 100.0);
        // app.checkManufactureEmployees(10000L);
        // app.findTotalEmployees();
        // app.findManufacturesByLocation("Cupertino");
    }
}
