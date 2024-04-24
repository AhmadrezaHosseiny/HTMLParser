package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Parser {
    static List<Country> countries = new ArrayList<>();
    public List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        sortedByName.sort(new Comparator<Country>() {
            @Override
            public int compare(Country c1, Country c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
        return  sortedByName;
    }
    public List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        sortedByPopulation.sort(new Comparator<Country>() {
            @Override
            public int compare(Country c1, Country c2) {
                return c2.getPopulation() - c1.getPopulation();
            }
        });
        return sortedByPopulation;
    }
    public List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        sortedByArea.sort(new Comparator<Country>() {
            @Override
            public int compare(Country c1, Country c2) {
                return Double.compare(c2.getArea(), c1.getArea());
            }
        });
        return sortedByArea;
    }
    public void setUp() throws IOException {
        File f = new File("src/main/resources/country-list.html");
        if (f.exists()) {
            System.out.println("File founded.");
            Document d = Jsoup.parse(f, "UTF-8");
            Elements e = d.getElementsByClass("country");
            for (Element element : e) {
                String countryName = element.select(".country-name").text();
                String countryCapital = element.select(".country-capital").text();
                int countryPopulation = Integer.parseInt(element.select(".country-population").text());
                double countryArea = Double.parseDouble(element.select(".country-area").text());
                Country c = new Country(countryName, countryCapital, countryPopulation, countryArea);
                countries.add(c);
            }
        }
        else {
            System.out.println("File not founded.");
        }
    }
    public static void main(String[] args) throws IOException {
        Parser p = new Parser();
        p.setUp();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(br.readLine());
        switch (choice) {
            case 1:
                for (int i = 0; i < countries.size(); i++) {
                    System.out.println(p.sortByName().get(i));
                }
            case 2:
                for (int i = 0; i < countries.size(); i++) {
                    System.out.println(p.sortByPopulation().get(i));
                }
            case 3:
                for (int i = 0; i < countries.size(); i++) {
                    System.out.println(p.sortByArea().get(i));
                }
        }
    }
}
