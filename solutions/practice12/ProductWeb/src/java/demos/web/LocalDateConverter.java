package demos.web;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter("LocalDateConverter")
public class LocalDateConverter extends DateTimeConverter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        super.setPattern("yyyy-MM-dd");
        Date date = (value == null) ? null : Date.from(((LocalDate)value).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return super.getAsString(context, component, date);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        super.setPattern("yyyy-MM-dd");
        Object obj = super.getAsObject(context, component, value);
        LocalDate date = (obj == null) ? null :Instant.ofEpochMilli(((Date)obj).getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        return date;
    }
    
}
