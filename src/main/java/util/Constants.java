package util;
import util.ConfigReader;

public class Constants {
    public static final String AMAZON_URL =
            ConfigReader.getProperty("amazon_url");

    public static final String EXCEL_FILE =
            ConfigReader.getProperty("excel_file");

    public static final String REPORT_FILE =
            ConfigReader.getProperty("report_file");

    public static final String DASHBOARD_FILE =
            ConfigReader.getProperty("dashboard_file");
    public static final String TELEGRAM_BOT_TOKEN =
            ConfigReader.getProperty("telegram_token");
    public static final String TELEGRAM_CHAT_ID = ConfigReader.getProperty("telegram_chatid");
}
