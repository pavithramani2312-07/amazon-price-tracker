    package model;

    import data.ExcelReader;

    public class Product {
        String asin;
        String productname;
        Double targetprice;

        public Product(String asin, String productname, double targetprice){
            this.asin=asin;
            this.productname=productname;
            this.targetprice=targetprice;
        }
        public String getAsin(){
            return asin;
        }
        public String getProductname(){
            return productname;
        }
        public double gettargetprice(){
            return targetprice;
        }

    }
