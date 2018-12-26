import org.openqa.selenium.WebElement;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;



public class ImdbCrawl extends ImdbCrawlComponents implements BaseConfiguration{

    public static void main(String[] args) throws IOException {


        if(CRWAL_LIMIT == 250) {
            getTop250(MOVIE_URL_250);
            writeToCsvFile();
            getAdditionalLinksToCrawl();
        }

        if(CRWAL_LIMIT > 250) {
            for (int genreMovieCounter = 0; genreMovieCounter < additionalLinksToCrawl.size() ;genreMovieCounter++){
                getMovieLinks(additionalLinksToCrawl.get(genreMovieCounter));
            }
        }

    }

    public static void writeToCsvFile() throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(RESULTS));

        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("Movie Title", "Director", "Rating"));

        for (int recordNumber = 0; recordNumber < CRWAL_LIMIT; recordNumber ++) {
            csvPrinter.printRecord(movieTitlelist.get(recordNumber).getText(),
                                    movieTitlelist.get(recordNumber).getAttribute("title").split("\\(dir.")[0],
                                        movieRatingList.get(recordNumber).getText().split(" ")[0]);
        }

        csvPrinter.flush();
    }

    public static void getTop250(String movieURL) {
        driver.get(BASE_URL+movieURL);
        movieTitlelist = driver.findElementsByCssSelector(Locators.top250ListLocator);
        movieRatingList = driver.findElementsByCssSelector(Locators.top250RatingLocator);
    }

    public static void getMovieLinks(String url) {
        driver.get(url);
        movieTitlelist.clear();
        movieRatingList.clear();
        movieTitlelist = driver.findElementsByCssSelector(Locators.genreMovieTitleLocator);
        movieRatingList = driver.findElementsByCssSelector(Locators.genreMovieRatingLocator);


    }

    public static void getAdditionalLinksToCrawl() {
      List <WebElement> elements = driver.findElementsByCssSelector(Locators.additionalMovieLinksLocator);
                elements.forEach(webElement -> additionalLinksToCrawl.add(webElement.getAttribute("href")));
                additionalLinksToCrawl.forEach(element -> System.out.println(element));
    }
}
