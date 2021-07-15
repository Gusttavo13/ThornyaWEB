package club.thornya.thornyaweb.Driver;

import club.thornya.thornyaweb.ThornyaWeb;
import club.thornya.thornyaweb.Utils.ConfigT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverSetup {

    public static WebDriver driver = null;
	
	public static String message = "DEFAULT";

    public static String lastMessage = "LASTDEFAULT";

    private static String sudoCommand = "Command";

    private static Boolean commandUsed = false;

    private static final String CONTATO = Objects.requireNonNull(ConfigT.getFile("configuration").getString("Whatsapp.group_name"));



    //private static final String CONTATO = "Arquivos2";

	public static void setupWeb() throws InterruptedException {
        String  path = ThornyaWeb.TW.getDataFolder().getPath() + "\\data\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        //System.setProperty("webdriver.http.factory", "netty");
        //System.setProperty("webdriver.chrome.driver", "C:\\Maven\\lib\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions()
                .addArguments("--no-sandbox")
                .addArguments("disable-infobars") // disabling infobars
                .addArguments("--disable-extensions") // disabling extensions
                .addArguments("--disable-gpu") // applicable to windows os only

                .addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                //.setBinary(System.getProperty("user.home") + "\\AppData\\Local\\Programs\\Opera\\76.0.4017.177\\opera.exe")
                //.addArguments("--user-data-dir=" + System.getProperty("user.home") + "\\AppData\\Roaming\\Opera Software\\Opera Stable");
                //.addArguments("--user-data-dir=" + System.getProperty("user.home") + Objects.requireNonNull(ConfigT.getFile("configuration").getString("PATH_DIR_CHROME")));
        setDriver(new ChromeDriver());

        getDriver().get("https://web.whatsapp.com/");
		//Bukkit.getConsoleSender().sendMessage("Iniciando Whatsapp Web");

        TimeUnit.SECONDS.sleep(15);

        selectGroup(CONTATO);
		//Bukkit.getConsoleSender().sendMessage("Abrindo Chat - " + CONTATO);

    }

    public static void listener() {
        if(!message.equalsIgnoreCase("/parar")) {
            lastMessage = "Null";
            if(!lastMessage.equalsIgnoreCase(message)) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int mensagensSize = getDriver().findElements(By.className(Objects.requireNonNull(ConfigT.getFile("configuration").getString("Elements.class_box_default_message")))).size();
                if (!getDriver().findElements(By.xpath(Objects.requireNonNull(ConfigT.getFile("configuration").getString("Elements.element_message_span")).replace("$ID$", String.valueOf(mensagensSize)))).isEmpty()) {
                    WebElement mensagemPath = getDriver().findElement(By.xpath(Objects.requireNonNull(ConfigT.getFile("configuration").getString("Elements.element_message_span")).replace("$ID$", String.valueOf(mensagensSize))));
                    message = mensagemPath.getText();
                    setCommandUsed(false);
                    try {
                        Triggers.triggers();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    message = "Variantes";
                }
            }
        }else{
            shutdown();
        }
    }

    public static void selectGroup(String chat) throws InterruptedException {

        WebElement contato = getDriver().findElement(By.xpath(Objects.requireNonNull(ConfigT.getFile("configuration").getString("Elements.box_select_contacts")).replace("$contato$", chat)));

        contato.click();
        TimeUnit.SECONDS.sleep(5);

    }
	


    public static void shutdown(){

        if(DriverSetup.getDriver() != null) { DriverSetup.getDriver().quit(); DriverSetup.getDriver().close();}
    }

    public static String getSudoCommand() {
        return "/" + DriverSetup.sudoCommand;
    }

    public static void setSudoCommand(String sudoCommand) {
        DriverSetup.sudoCommand = sudoCommand;
    }

    public static Boolean getCommandUsed() {
        return DriverSetup.commandUsed;
    }

    public static void setCommandUsed(Boolean commandUsed) {
        DriverSetup.commandUsed = commandUsed;
    }
	
	public static WebDriver getDriver() {
        return DriverSetup.driver;
    }

    public static void setDriver(WebDriver driver) {
        DriverSetup.driver = driver;
    }

}
