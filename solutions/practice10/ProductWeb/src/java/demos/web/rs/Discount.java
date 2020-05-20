package demos.web.rs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Discount implements Serializable {
    
    private BigDecimal value;
    private LocalDate date;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
