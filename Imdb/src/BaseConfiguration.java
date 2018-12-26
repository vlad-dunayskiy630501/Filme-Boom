import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public interface BaseConfiguration {

    String BASE_URL = "https://www.imdb.com";
    String MOVIE_URL_250 = "/chart/top";
    String RESULTS = "./Results.csv";
    int CRWAL_LIMIT = 250;
    HtmlUnitDriver driver = new HtmlUnitDriver();

}
