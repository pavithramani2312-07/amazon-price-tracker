    package tests;

    import base.BaseClass;
    import listeners.TestListener;
    import org.testng.Assert;
    import org.testng.annotations.Listeners;
    import org.testng.annotations.Test;

    @Listeners(TestListener.class)
    public class PriceTrackerTest {

        @Test
        public void samplePassTest() {

            System.out.println("Running pass test");

            Assert.assertTrue(true);
        }

        @Test
        public void sampleFailTest() {

            System.out.println("Running fail test");

                BaseClass.getDriver();

                BaseClass.driver.get("https://www.amazon.in");

                Assert.assertTrue(true);

                BaseClass.driver.quit();
            }
        }
