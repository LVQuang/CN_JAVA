package dev.lvpq;

import dev.lvpq.dao.ManufacturerDAO;
import dev.lvpq.dao.PhoneDAO;
import dev.lvpq.domain.Manufacturer;
import dev.lvpq.domain.Phone;

import java.util.List;

public class App
{
    private final PhoneDAO phoneDao;
    private final ManufacturerDAO manufacturerDAO;

    public App() {
        this.phoneDao = new PhoneDAO();
        this.manufacturerDAO = new ManufacturerDAO();
    }

    private void addPhones()
    {
        Phone phone1 =  Phone.builder()
                .name("Iphone")
                .color("white")
                .country("US")
                .price(1000.0)
                .quantity(100)
                .build();

        Phone phone2 =  Phone.builder()
                .name("Galaxy")
                .color("black")
                .country("Korea")
                .price(1000.0)
                .quantity(500)
                .build();

        Phone phone3 =  Phone.builder()
                .name("XiaMi")
                .color("black")
                .country("China")
                .price(500.0)
                .quantity(1000)
                .build();

        phoneDao.add(phone1);
        phoneDao.add(phone2);
        phoneDao.add(phone3);
    }

    private void addManufactures()
    {
        Manufacturer manufacturer1 = Manufacturer.builder()
                .name("Apple")
                .location("Cupertino")
                .employeeNumber(100000)
                .build();

        Manufacturer manufacturer2 = Manufacturer.builder()
                .name("Samsung")
                .location("Su-won-si")
                .employeeNumber(200000)
                .build();

        Manufacturer manufacturer3 = Manufacturer.builder()
                .name("XiaMi")
                .location("Beijing")
                .employeeNumber(500000)
                .build();

        manufacturerDAO.add(manufacturer1);
        manufacturerDAO.add(manufacturer2);
        manufacturerDAO.add(manufacturer3);
    }

    private void updatePhones()
    {
        Phone phone1 = phoneDao.get(1L);
        Phone phone2 = phoneDao.get(2L);
        Phone phone3 = phoneDao.get(3L);
        Manufacturer manufacturer1 = manufacturerDAO.get(1L);
        Manufacturer manufacturer2 = manufacturerDAO.get(2L);
        Manufacturer manufacturer3 = manufacturerDAO.get(3L);
        phone1.setManufacturer(manufacturer1);
        phoneDao.update(phone1);
        phone2.setManufacturer(manufacturer2);
        phoneDao.update(phone2);
        phone3.setManufacturer(manufacturer3);
        phoneDao.update(phone3);
    }

    private void showHighestPricePhone() {
        List<Phone> phoneList = phoneDao.getHighestPricePhones();
        if (phoneList != null) {
            System.out.println("The highest price phones are: ");
            for (Phone phone : phoneList)
                phone.print();
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    private void sortByCountry() {
        List<Phone> phoneList = phoneDao.sortByCountry();
        if (phoneList != null) {
            System.out.println("The phones are sorted by country ascendingly and then by price descendingly are: ");
            for (Phone phone : phoneList)
                phone.print();
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    private void getPhonesHigherThanPrice(Double price) {
        List<Phone> phoneList = phoneDao.getPhonesHigherThanPrice(price);
        if (phoneList != null) {
            System.out.println("The phones are more expensive than $" + price + " are: ");
            for (Phone phone : phoneList)
                phone.print();
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    private void getPhonesWithColorAndPrice(String color, Double price) {
        List<Phone> phoneList = phoneDao.getPhonesWithColorAndPrice(color, price);
        if (phoneList != null) {
            System.out.println("The phones with color " + color + " and are more expensive than $" + price + " are: ");
            for (Phone phone : phoneList)
                phone.print();
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

    private void initData() {
        addPhones();
        addManufactures();
        updatePhones();
    }

    private void findManufacturesByLocation(String location) {
        List<Manufacturer> manufacturerList = manufacturerDAO.findManufacturesByLocation(location);
        if (manufacturerList != null) {
            System.out.println("All the manufactures from " + location + " are: ");
            for (Manufacturer manufacturer : manufacturerList)
                manufacturer.print();
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    public static void main( String[] args ) {
        App app = new App();
        app.initData();
        app.showHighestPricePhone();
        app.sortByCountry();
        app.getPhonesHigherThanPrice(500.0);
        app.getPhonesWithColorAndPrice("white", 100.0);
        app.checkManufactureEmployees(10000L);
        app.findTotalEmployees();
        app.findManufacturesByLocation("Cupertino");
    }
}
