import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class projektPD {

    WebDriver driver = new ChromeDriver();


    @Test
    public void emptyUsernameTest() {
        driver.manage().window().maximize();
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Moje konto")).click();
        driver.findElement(By.id("password")).sendKeys("rarara");
        driver.findElement(By.cssSelector(".woocommerce-form-login__submit")).click();
        Assertions.assertEquals("Błąd: Nazwa użytkownika jest wymagana.",
                driver.findElement(By.cssSelector(".woocommerce-error")).getText());
        driver.quit();
    }

    @Test
    public void emptyPaswordTest() {
        driver.manage().window().maximize();
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Moje konto")).click();
        driver.findElement(By.cssSelector("#username")).sendKeys("test");
        driver.findElement(By.cssSelector(".woocommerce-form-login__submit")).click();
        Assertions.assertEquals("Błąd: pole hasła jest puste.",
                driver.findElement(By.cssSelector(".woocommerce-error")).getText());
        driver.quit();
    }

    @Test
    public void checkLogoAndSearchOnHomePage() {
        driver.manage().window().maximize();
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Moje konto")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//a[text() = 'Softie Metal Shop']")).isDisplayed());
        driver.findElement(By.cssSelector("#woocommerce-product-search-field-0")).isDisplayed();
        driver.quit();
    }

    @Test
    public void checkContact() {
        driver.manage().window().maximize();
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Kontakt")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//*[@id='menu-item-132']")).isDisplayed());
        driver.quit();
    }

    @Test
    public void checkMainSite() {
        driver.manage().window().maximize();
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/register/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElements(By.className("beta site-title"));
        // driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/");
        Assertions.assertTrue(driver.findElement(By.xpath("//*[@id='masthead']/div[1]/div[1]/div/a")).isDisplayed());
        driver.quit();

    }


    @Test

    public void checkErrorContact() {
        driver.manage().window().maximize();
        driver.get("http://serwer169007.lh.pl/autoinstalator/serwer169007.lh.pl/wordpress10772/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        // driver.findElement(By.linkText("Kontakt")).click();
        driver.findElement(By.id("menu-item-132")).click();


        Faker faker = new Faker();
        String username = "Piotr Piotrowicz";
        System.out.println(username);
        String email = "piotrpiotrowicz@o2.pl";
        System.out.println(email);
        String temat = "Temat";
        System.out.println(temat);
        String message = faker.lorem().paragraph();
        System.out.println(message);


        // driver.findElement(By.xpath("//*[@data-name='your-name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='your-name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='your-email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='your-subject']")).sendKeys(temat);
        driver.findElement(By.xpath("//textarea[@name='your-message']")).sendKeys(message);
        driver.findElement(By.xpath("//input[@value='Wyślij']")).click();
        String text = driver.findElement(By.cssSelector(".wpcf7-response-output")).getText();
        System.out.println(text);
        Assertions.assertEquals("Wystąpił problem z wysłaniem twojej wiadomości. Spróbuj ponownie później.",
                "Wystąpił problem z wysłaniem twojej wiadomości. Spróbuj ponownie później.");
        driver.quit();
    }
}
