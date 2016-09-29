package chapter_2.section_3;

import java.io.OutputStream;

//Subclassing para
class Address {

    protected String street;
    protected String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String s) {
        street = s;
    }

    // ...
    public void printLabel(OutputStream s) {
    }

}
