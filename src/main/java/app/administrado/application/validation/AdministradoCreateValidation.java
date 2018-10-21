package app.administrado.application.validation;

import org.springframework.stereotype.Component;
import app.common.application.notification.Notification;
import app.administrado.application.dto.AdministradoCreateDto;

@Component
public class AdministradoCreateValidation {
	public void validate(AdministradoCreateDto administradoCreateDto) {
            Notification notification = this.validateData(administradoCreateDto);
            if (notification.hasErrors()) {
                throw new IllegalArgumentException(notification.errorMessage());
            }
	}
	
	public Notification validateData(AdministradoCreateDto administradoCreateDto) {
            Notification notification = new Notification();
            if (administradoCreateDto == null) {
                    notification.addError("Missing administrado parameters");
                    return notification;
            }
            if (administradoCreateDto.getFirstName().trim().isEmpty()) {
                    notification.addError("Missing firstname parameter");
            }
            if (administradoCreateDto.getLastName().trim().isEmpty()) {
                    notification.addError("Missing lastname parameter");
                    return notification;
            }
            if (administradoCreateDto.getIdentityDocument().trim().isEmpty()) {
                    notification.addError("Missing identity document parameter");
                    return notification;
            }            
            if (administradoCreateDto.getCode().trim().isEmpty()) {
                    notification.addError("Missing gun's code parameter");
                    return notification;
            }
            if (administradoCreateDto.getBrand().trim().isEmpty()) {
                    notification.addError("Missing gun's brand parameter");
                    return notification;
            }
            if (administradoCreateDto.getModel().trim().isEmpty()) {
                    notification.addError("Missing gun's model parameter");
                    return notification;
            }
            if (administradoCreateDto.getCurrency() == null) {
                    notification.addError("Missing currency parameter");
                    return notification;
            }
            
            return notification;
	}
}
