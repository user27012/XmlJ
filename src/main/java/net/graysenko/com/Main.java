package net.graysenko.com;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
class City {
    private String name;
    private String size;
    private Street street;

    @XmlAttribute
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "street")
    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }
}

class Street {
    private String name;
    private List<Building> buildings = new ArrayList<>();

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "dom")
    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}

class Building {
    private int number;

    public Building(int i) {
    }

    @XmlAttribute
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

public class Main {

    public static void main(String[] args) {
        City city = new City();
        city.setName("Kiev");
        city.setSize("huge");

        Street street = new Street();
        street.setName("Shevchenko");

        street.getBuildings().add(new Building(1));
        street.getBuildings().add(new Building(2));

        city.setStreet(street);

        try {
            JAXBContext context = JAXBContext.newInstance(City.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File xmlFile = new File("city.xml");
            marshaller.marshal(city, xmlFile);
            System.out.println("XML файл создан по пути: " + xmlFile.getAbsolutePath());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
