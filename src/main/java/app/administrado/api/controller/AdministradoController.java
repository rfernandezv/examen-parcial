package app.administrado.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.common.application.ApiResponseHandler;
import app.common.application.UnitOfWork;
import app.administrado.application.assembler.AdministradoCreateAssembler;
import app.administrado.application.dto.AdministradoCreateDto;
import app.administrado.application.dto.AdministradoDto;
import app.administrado.application.validation.AdministradoCreateValidation;
import app.administrado.domain.entity.Administrado;
import app.administrado.domain.repository.AdministradoRepository;
import app.common.application.sender.Sender;

@RestController
@RequestMapping("api/administrado")
public class AdministradoController {
	@Autowired
	UnitOfWork unitOfWork;
	
	@Autowired
	AdministradoRepository administradoRepository;

	@Autowired
	AdministradoCreateValidation administradoCreateValidation;
	
	@Autowired
	AdministradoCreateAssembler administradoCreateAssembler;
	
	@Autowired
	ApiResponseHandler apiResponseHandler;
        
        @Autowired
	Sender sender;
        
	@RequestMapping(
	    method = RequestMethod.POST,
	    path = "",
	    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
	    produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<Object> register(@RequestBody AdministradoCreateDto administradoCreateDto) throws Exception {
            boolean status = false;
            try {
                status = unitOfWork.beginTransaction();                
                Administrado administrado = administradoCreateAssembler.toEntity(administradoCreateDto);
                administradoRepository.create(administrado);
                unitOfWork.commit(status);
                sender.sendMessage(""+administrado.getId());
                AdministradoDto administradoResponse = administradoCreateAssembler.toDto(administrado);                
                return new ResponseEntity<Object>(administradoResponse, HttpStatus.CREATED);
            } catch(IllegalArgumentException ex) {
        	unitOfWork.rollback(status);
        	return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
            } catch(Exception ex) {
        	unitOfWork.rollback(status);
		return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        @RequestMapping(
	    method = RequestMethod.GET,
	    path = "/all"
	)
	public ResponseEntity<Object> getAll() throws Exception {
            boolean status = false;
            try {
                return new ResponseEntity<Object>(administradoCreateAssembler.toDtoList(administradoRepository.getAll()), HttpStatus.OK);
            } catch(IllegalArgumentException ex) {
        	unitOfWork.rollback(status);
        	return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
            } catch(Exception ex) {
        	unitOfWork.rollback(status);
		return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }        	
}
