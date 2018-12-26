package com.imdb.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileWriter;
import java.io.IOException;

public class IMDB250 {
    public static void main(String[] args) throws IOException {
        final Document document = Jsoup.connect("http://www.imdb.com/chart/top").get();
        FileWriter writer;
        writer = new FileWriter("G:\\250.txt");

        String title;
        String rating;
        int count = 0;

        for (Element row : document.select("table.chart.full-width tr")){

            title = row.select("> .titleColumn a").text();
            rating = row.select(".imdbRating").text();

            if(count != 0){
                System.out.println(count + ". "+ title + " -> Rating: " + rating);
                writer.write(count + ". "+ title + " -> Rating: " + rating);
                writer.write("\n");
            }

            count++;

        }
        writer.close();
    }
}
