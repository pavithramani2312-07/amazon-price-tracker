# Amazon Price Tracker

Amazon Price Tracker is a Java-based Selenium automation project that monitors Amazon product prices and notifies users when products reach or fall below a predefined target price.

## Tech Stack

* Java
* Selenium WebDriver
* Gradle
* Page Object Model (POM)
* Data-Driven Testing (Apache POI)
* Excel
* Email

## Features

* Read product information from Excel
* Open Amazon product pages using product IDs (ASIN)
* Extract and validate current product prices
* Compare prices against user-defined target values
* Send notifications when prices meet the desired threshold
* Generate a mobile-friendly product summary page
* Scheduled execution for periodic price checks

## Future Enhancements

* Database integration
* Dashboard with price history
* Multiple notification channels
* Docker deployment
* CI/CD with GitHub Actions

-------------------------------------------------------------------
# Amazon Price Tracker

A Java-based Selenium automation project that tracks Amazon product prices and alerts users when prices drop below a target value.

## Features Completed

- Gradle project setup
- Selenium WebDriver integration
- Apache POI integration for Excel reading
- Read product details from Excel
- Read:
    - ASIN
    - Product Name
    - Target Price
- Generate Amazon product URLs from ASIN
- Launch Chrome browser using Selenium
- Open Amazon product pages automatically

## Tech Stack

- Java
- Gradle
- Selenium WebDriver
- Apache POI
- WebDriverManager

## Project Structure

src/main/java

- base
    - BaseClass.java
- data
    - ExcelReader.java
- pages
    - AmazonPage.java
- runner
    - PriceTrackerRunner.java

src/main/resources

- product_list.xlsx

## Next Steps

- Extract current Amazon price
- Compare actual price with target price
- Generate reports
- Send push notifications
- Create dashboard webpage
- Schedule execution every 3 hours



Amazon Price Tracker
Overview

Amazon Price Tracker helps monitor the prices of products on Amazon and notifies users when prices change.

Instead of manually checking product prices every day, the application automatically:

Checks the latest price of selected Amazon products
Compares the current price with your desired target price
Identifies products whose prices have dropped
Generates a detailed report
Creates a dashboard for easy viewing
Sends an email containing the report and dashboard
How It Works
1. Add Products

The products you want to monitor are stored in an Excel file.

For each product, you provide:

Product ID (ASIN)
Product Name
Target Price

Example:

Product Name	Target Price
Shoe Rack	₹4000
Door Mat	₹100
Sofa Cum Bed	₹15000
2. Automatic Price Check

The system opens each product page on Amazon and retrieves the latest available price.

3. Price Comparison

The current price is compared with the target price.

If:

Current Price ≤ Target Price

the product is marked as:

DROPPED

Otherwise it is marked as:

HIGHER
4. Report Generation

A detailed Excel report is automatically generated containing:

Product Name
Target Price
Current Price
Status
Product Link

This helps users quickly understand which products are worth purchasing.

5. Dashboard Generation

A simple web dashboard is created showing:

All tracked products
Current prices
Price status
Direct links to Amazon product pages

Users can open the dashboard in a browser and view all product information in one place.

6. Email Notification

After the price check is completed:

The Excel report is attached to an email
The dashboard is attached to the email
A summary of the price check is included

This allows users to review product prices without opening the application.

Features

✅ Track multiple Amazon products

✅ Read product information from Excel

✅ Automatically check current Amazon prices

✅ Compare prices against user-defined targets

✅ Generate Excel reports

✅ Create a web-based dashboard

✅ Send email notifications with attachments

✅ Direct links to Amazon products

Benefits
Saves time by avoiding manual price checks
Helps identify price drops quickly
Provides reports for easy tracking
Centralized dashboard for monitoring products
Email updates delivered automatically
Future Enhancements

Planned improvements include:

Automatic execution every 3 hours
Online dashboard hosting
Price history tracking
Charts and analytics
Mobile notifications
Multi-user support
Sample Workflow
Add Products
↓
Run Price Tracker
↓
Fetch Current Amazon Prices
↓
Compare with Target Prices
↓
Generate Excel Report
↓
Generate Dashboard
↓
Send Email Notification

Project Status: Completed MVP (Minimum Viable Product)

Current version supports end-to-end Amazon price monitoring, report generation, dashboard creation, and email notifications. 🚀