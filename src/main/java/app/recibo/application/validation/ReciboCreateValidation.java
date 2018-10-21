package app.recibo.application.validation;

import org.springframework.stereotype.Component;
import app.common.application.notification.Notification;
import app.recibo.application.dto.ReciboCreateDto;

@Component
public class ReciboCreateValidation {
	public void validate(ReciboCreateDto reciboCreateDto) {
            Notification notification = this.validateData(reciboCreateDto);
            if (notification.hasErrors()) {
                throw new IllegalArgumentException(notification.errorMessage());
            }
	}
	
	public Notification validateData(ReciboCreateDto reciboCreateDto) {
		Notification notification = new Notification();
		if (reciboCreateDto == null) {
			notification.addError("Missing project parameters");
			return notification;
		}
		if (reciboCreateDto.getCode().trim().isEmpty()) {
			notification.addError("Missing Number parameter");
		}
                if (reciboCreateDto.getProcess().trim().isEmpty()) {
			notification.addError("Missing name parameter");
		}
                if (reciboCreateDto.getCurrency() == null) {
			notification.addError("Missing currency parameter");
			return notification;
		}
		if (reciboCreateDto.getBalance() == null) {
			notification.addError("Missing Balance parameter");
			return notification;
		}
		if (reciboCreateDto.getBalance().doubleValue() <= 0.0) {
			notification.addError("Balance must be grater than zero");
		}
		return notification;
	}
}
