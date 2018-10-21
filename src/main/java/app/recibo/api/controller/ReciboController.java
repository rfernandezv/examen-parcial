package app.recibo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.common.application.ApiResponseHandler;
import app.common.application.UnitOfWork;
import app.recibo.application.assembler.ReciboCreateAssembler;
import app.recibo.application.validation.ReciboCreateValidation;
import app.recibo.domain.repository.ReciboRepository;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/administrado/{administradoId}/recibos")
public class ReciboController {
	@Autowired
	UnitOfWork unitOfWork;
	
	@Autowired
	ReciboRepository reciboRepository;

	@Autowired
	ReciboCreateValidation reciboCreateValidation;
	
	@Autowired
	ReciboCreateAssembler reciboCreateAssembler;
	
	@Autowired
	ApiResponseHandler apiResponseHandler;
                
        @RequestMapping(
	    method = RequestMethod.GET,
	    path = "/all"
	)
	public ResponseEntity<Object> getAll(@PathVariable("administradoId") long administradoId) throws Exception {
            boolean status = false;
            try {
                return new ResponseEntity<Object>(reciboCreateAssembler.toDtoList(reciboRepository.getAll(administradoId)), HttpStatus.OK);
            } catch(IllegalArgumentException ex) {
        	unitOfWork.rollback(status);
        	return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
            } catch(Exception ex) {
        	unitOfWork.rollback(status);
		return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
	
}
