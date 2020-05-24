package app.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.math.BigDecimal;

public class ValidatorBean {
    public static final double[] AVAILABLE_Y = new double[]{-3, 3};
    public static final String[] AVAILABLE_R = new String[]{"1", "1.5", "2", "2.5", "3"};

    public void validateY(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return;
        }

        try {
            final BigDecimal val = new BigDecimal(value.toString().replace(',', '.'));

            if (val.compareTo(BigDecimal.valueOf(AVAILABLE_Y[0])) <= 0 ||
                    val.compareTo(BigDecimal.valueOf(AVAILABLE_Y[1])) >= 0) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, (String) component.getAttributes()
                        .getOrDefault("validatorMessage", "Y must be in (" + AVAILABLE_Y[0] + ", " + AVAILABLE_Y[1] + ")"), null));
            }
        } catch (NumberFormatException e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, (String) component
                    .getAttributes().getOrDefault("converterMessage", "Y must be a number"), null), e);
        }
    }
}
