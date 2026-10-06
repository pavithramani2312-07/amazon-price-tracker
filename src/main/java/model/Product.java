    package model;

    public class Product {
        private final String asin;
        private final String productname;
        private final Double targetprice;

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
