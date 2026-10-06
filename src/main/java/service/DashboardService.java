package service;

import java.io.FileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;

public class DashboardService {
    private StringBuilder html;
    private static final Logger log = LoggerFactory.getLogger(DashboardService.class);
    public void createDashboard(){
        html = new StringBuilder();
        html.append("<html>");
        html.append("<head>");
        html.append("<title>Amazon Price Tracker Dashboard</title>");

        html.append("<style>");
        html.append("table {border-collapse: collapse; width: 100%; }");
        html.append("th, td { border: 1px solid black; padding: 8px; }");
        html.append("th { background-color: #f2f2f2; }");
        html.append("</style>");

        html.append("</head>");
        html.append("<body>");

        html.append("<h1> Amazon Price Tracker Dashboard</h>");

        html.append("<table>");

        html.append("<tr>");
        html.append("<th>Product Name</th>");
        html.append("<th>Target Price</th>");
        html.append("<th>Current Price</th>");
        html.append("<th>Status</th>");
        html.append("<th>Link</th>");
        html.append("</tr>");
    }
    public void addRow(String productName, double targetPrice, double currentPrice, String status, String productUrl){
        html.append("<tr>");

        html.append("<td>").append(productName).append("</td>");
        html.append("<td>").append(targetPrice).append("</td>");
        html.append("<td>").append(currentPrice).append("</td>");

        if(status.equals("DROPPED")){
            html.append("<td style='color:green'>")
                    .append(status)
                    .append("</td>");
        }
        else if(status.equals("HIGHER")){
            html.append("<td style='color:red'>")
                    .append(status)
                    .append("</td>");
        }
        else{
            html.append("<td style='color:orange'>")
                    .append(status)
                    .append("</td>");
        }

        html.append("<td>");
        html.append("<a href = '").append(productUrl)
                .append("'>Open product</a>");
        html.append("</td>");
        html.append("</tr>");
    }
    public void saveDashboard(){
        try{
            html.append("</table>");
            html.append("</body>");
            html.append("</html>");
            FileWriter writer=new FileWriter(Constants.DASHBOARD_FILE);
            writer.write(html.toString());
            writer.close();
            log.info("Dashboard created successfully");
        }catch (Exception e){
            log.error("Failed to create dashboard", e);
        }

    }
}
