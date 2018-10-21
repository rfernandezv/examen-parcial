package app.arma.application.validation;

import org.springframework.stereotype.Component;
import app.common.application.notification.Notification;
import app.arma.application.dto.ArmaCreateDto;

@Component
public class ArmaCreateValidation {
	public void validate(ArmaCreateDto armaCreateDto) {
            Notification notification = this.validateData(armaCreateDto);
            if (notification.hasErrors()) {
                throw new IllegalArgumentException(notification.errorMessage());
            }
	}
	
	public Notification validateData(ArmaCreateDto armaCreateDto) {
            Notification notification = new Notification();
            if (armaCreateDto == null) {
                    notification.addError("Missing gun parameters");
                    return notification;
            }
            if (armaCreateDto.getSerie().trim().isEmpty()) {
                    notification.addError("Missing serie parameter");
            }
            if (armaCreateDto.getBrand().trim().isEmpty()) {
                    notification.addError("Missing brand parameter");
                    return notification;
            }
            if (armaCreateDto.getModel().trim().isEmpty()) {
                    notification.addError("Missing model parameter");
                    return notification;
            }
            return notification;
	}
}
