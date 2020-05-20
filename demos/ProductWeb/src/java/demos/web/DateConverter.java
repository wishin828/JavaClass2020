package demos.web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author oracle
 */
@FacesConverter("LocalDateConverter")
public class DateConverter extends DateTimeConverter {
    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        LocalDate localDate = null;
        Date date = null;
        super.setPattern("yyyy-MM-dd");
        Object obj = super.getAsObject(facesContext, uiComponent, value);
        localDate = (obj==null) ? null : Instant.ofEpochMilli(((Date)obj).getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        Date date = (value == null) ? null : Date.from(((LocalDate)value).atStartOfDay(ZoneId.systemDefault()).toInstant());
        super.setPattern("yyyy-MM-dd");
        return super.getAsString(facesContext, uiComponent, date);
    }
}
