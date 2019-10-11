package com.company.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "artist")
// arrange property/element order of xml element, this is Optional
@XmlType(propOrder = { "name", "price" })
public class Artist {

    private String name;
    private int price;

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}