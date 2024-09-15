package ge.tbc.itacademy.data.dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "MinAndMaxPrices")
    public Object[][] minAndMaxPrices(){
        return new Object[][]{
                {"0","1"},
                {"150","200"},
                {"200","250"},
                {"250","300"},
                {"300","350"},
                {"50","100"},
        };

    }
}
