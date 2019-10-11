package com.company;

import com.company.xml.Artist;
import com.company.xml.Root;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
* <root>
*	<artist>
*		<name>yong</name>
*		<price>100000</price>
*	</artist>
* </root>
*/

public class Main {
    private static File file = new File("/tmp/foo/bar.txt");
    private static Root root = new Root();

    public static void main(String[] args) {
        writeToFile();
        System.out.println(jaxbXmlFileToObject());
    }

    private static void writeToFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
                prefillArtists();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void prefillArtists() {
        if(root.getArtists().size() == 0) {
            List<Artist> vector = new Vector<>();
            Artist artist1 = new Artist();
            artist1.setName("Kirkorov");
            artist1.setPrice(100);

            Artist artist2 = new Artist();
            artist2.setName("Baskov");
            artist2.setPrice(230);

            vector.add(artist1);
            vector.add(artist2);

            root.setArtists(vector);
        }

        jaxbObjectToXML(root);
    }

    private static void jaxbObjectToXML(Root root) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Root.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        Marshaller m = null;
        try {
            m = context.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (PropertyException e) {
            e.printStackTrace();
        }

        // Write to File
        try {
            m.marshal(root, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static List<Artist> jaxbXmlFileToObject() {
        // create JAXB context and instantiate marshaller
        // here bookStore class is container , which contains Book
        try {
            JAXBContext context = JAXBContext.newInstance(Root.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // read out xml file, and populate values in java object
            // here xml created before are going to read
            Unmarshaller um = context.createUnmarshaller();
            Root root = (Root) um.unmarshal(new FileReader(file));
            return root.getArtists();
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Can't parse");
    }
}
