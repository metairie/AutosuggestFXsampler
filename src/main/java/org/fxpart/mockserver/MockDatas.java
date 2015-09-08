package org.fxpart.mockserver;

import javafx.collections.FXCollections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by metairie on 24-Jun-15.
 */
public class MockDatas {
    private final static Logger LOG = LoggerFactory.getLogger(MockDatas.class);


    // --------------------------------------------
    //  static
    // --------------------------------------------

    // ----------
    // LOCATION
    // ----------
    public static String loadLocationJson() {
        // data for Location
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("{ \"id\": 1, \"code\":\"LO1\", \"name\": \"Point of View 1\" },");
        sb.append("{ \"id\": 2, \"code\":\"LO2\", \"name\": \"Poland\" },");
        sb.append("{ \"id\": 3, \"code\":\"LO3\", \"name\": \"Forest\" },");
        sb.append("{ \"id\": 4, \"code\":\"LO4\", \"name\": \"Office\" },");
        sb.append("{ \"id\": 5, \"code\":\"LO5\", \"name\": \"Swimming pool\" },");
        sb.append("{ \"id\": 6, \"code\":\"LO6\", \"name\": \"Tribune\" },");
        sb.append("{ \"id\": 7, \"code\":\"LO7\", \"name\": \"Office 6 \" },");
        sb.append("{ \"id\": 8, \"code\":\"LO8\", \"name\": \"Garden\" }");
        sb.append("]");
        return sb.toString();
    }

    public static List<KeyValueString> loadStaticLocation() {
        // data for Location
        KeyValueString lb1 = new KeyValueString("LO1", "Point of View 1");
        KeyValueString lb2 = new KeyValueString("LO2", "Poland");
        KeyValueString lb3 = new KeyValueString("LO3", "Forest");
        KeyValueString lb4 = new KeyValueString("LO4", "Office");
        KeyValueString lb5 = new KeyValueString("LO5", "Swimming pool");
        KeyValueString lb6 = new KeyValueString("LO6", "Tribune");
        KeyValueString lb7 = new KeyValueString("LO7", "Office 6");
        KeyValueString lb8 = new KeyValueString("LO8", "Garden");
        return Arrays.asList(lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8);
    }

    public List<LocationBean> loadLocationBeans() {
        // data for Location
        LocationBean lb1 = new LocationBean("LO1", "Point of View 1");
        LocationBean lb2 = new LocationBean("LO2", "Poland");
        LocationBean lb3 = new LocationBean("LO3", "Forest");
        LocationBean lb4 = new LocationBean("LO4", "Office");
        LocationBean lb5 = new LocationBean("LO5", "Swimming pool");
        LocationBean lb6 = new LocationBean("LO6", "Tribune");
        LocationBean lb7 = new LocationBean("LO7", "Office 6");
        LocationBean lb8 = new LocationBean("LO8", "Garden");
        LocationBean lb9 = new LocationBean("LO9", "");
        return Arrays.asList(lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9);
    }

    public List<KeyValueString> loadLocation() {
        // data for Location
        KeyValueString lb1 = new KeyValueString("LO1", "Point of View 1");
        KeyValueString lb2 = new KeyValueString("LO2", "Poland");
        KeyValueString lb3 = new KeyValueString("LO3", "Forest");
        KeyValueString lb4 = new KeyValueString("LO4", "Office");
        KeyValueString lb5 = new KeyValueString("LO5", "Swimming pool");
        KeyValueString lb6 = new KeyValueString("LO6", "Tribune");
        KeyValueString lb7 = new KeyValueString("LO7", "Office 6");
        KeyValueString lb8 = new KeyValueString("LO8", "Garden");
        return Arrays.asList(lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8);
    }

    public static Collection<?> loadLocationStrings() {
        return FXCollections.observableArrayList("Point of View", "Poland", "Forest", "Office", "Swimming pool", "Tribune", "Office", "Garden");
    }

    // ----------
    // PROFESSION
    // ----------

    public static String loadProfessionJson() {
        // data for Profession
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("{ \"id\": 1, \"code\":\"PR1\", \"name\": \"Photographer\" },");
        sb.append("{ \"id\": 2, \"code\":\"PR2\", \"name\": \"Politic\" },");
        sb.append("{ \"id\": 3, \"code\":\"PR3\", \"name\": \"Priest\" },");
        sb.append("{ \"id\": 4, \"code\":\"PR4\", \"name\": \"Podiatrist\" },");
        sb.append("{ \"id\": 5, \"code\":\"PR5\", \"name\": \"Swimmer\" },");
        sb.append("{ \"id\": 6, \"code\":\"PR6\", \"name\": \"Spokesman\" },");
        sb.append("{ \"id\": 7, \"code\":\"PR7\", \"name\": \"Developer\" },");
        sb.append("{ \"id\": 8, \"code\":\"PR8\", \"name\": \"Gardener\" }");
        sb.append("]");
        return sb.toString();
    }

    public List<KeyValueString> loadProfession() {
        // data for Location
        KeyValueString lb1 = new KeyValueString("PR1", "Photographer 1");
        KeyValueString lb2 = new KeyValueString("PR2", "Politic");
        KeyValueString lb3 = new KeyValueString("PR3", "Priest");
        KeyValueString lb4 = new KeyValueString("PR4", "Podiatrist");
        KeyValueString lb5 = new KeyValueString("PR5", "Swimmer");
        KeyValueString lb6 = new KeyValueString("PR6", "Spokesman");
        KeyValueString lb7 = new KeyValueString("PR7", "Developer 6");
        KeyValueString lb8 = new KeyValueString("PR8", "Gardener");
        return Arrays.asList(lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8);
    }

    // ----------
    // other
    // ----------
    public List<KVIntegerDouble> loadKVID() {
        KVIntegerDouble kvid1 = new KVIntegerDouble(1, 100.54D);
        KVIntegerDouble kvid2 = new KVIntegerDouble(2, 120.36232D);
        KVIntegerDouble kvid3 = new KVIntegerDouble(3, 1.04D);
        KVIntegerDouble kvid4 = new KVIntegerDouble(4, 1D);
        KVIntegerDouble kvid5 = new KVIntegerDouble(5, 0D);
        KVIntegerDouble kvid6 = new KVIntegerDouble(6, 100.99D);
        KVIntegerDouble kvid7 = new KVIntegerDouble(7, 12900.14D);
        KVIntegerDouble kvid8 = new KVIntegerDouble(8, 982.44D);
        return Arrays.asList(kvid1, kvid2, kvid3, kvid4, kvid5, kvid6, kvid7, kvid8);
    }

}
