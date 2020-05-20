package demos.client.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class Discount implements Serializable {
    
    private BigDecimal value;
    private String date;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
