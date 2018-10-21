package app.arma.api.controller;

import app.administrado.domain.entity.Administrado;
import app.administrado.domain.repository.AdministradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.common.application.ApiResponseHandler;
import app.common.application.UnitOfWork;
import app.arma.application.assembler.ArmaCreateAssembler;
import app.arma.application.validation.ArmaCreateValidation;
import app.arma.domain.entity.Arma;
import app.arma.domain.repository.ArmaRepository;
import app.recibo.domain.entity.Recibo;
import app.recibo.domain.entity.ReciboAbstraction;
import app.recibo.domain.entity.ReciboNull;
import app.recibo.domain.repository.ReciboRepository;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/administrado/{administradoId}/armas")
public class ArmaController {
	@Autowired
	UnitOfWork unitOfWork;
	
	@Autowired
	ArmaRepository armaRepository;
        
        @Autowired
	AdministradoRepository administradoRepository;
        
        @Autowired
	ReciboRepository reciboRepository;

	@Autowired
	ArmaCreateValidation armaCreateValidation;
	
	@Autowired
	ArmaCreateAssembler armaCreateAssembler;
	
	@Autowired
	ApiResponseHandler apiResponseHandler;

        @RequestMapping(
	    method = RequestMethod.GET,
	    path = "/cese/{armaId}"
	)
	public ResponseEntity<Object> cesar(@PathVariable("administradoId") long administradoId, @PathVariable(value="armaId") Long armaId) throws Exception {
            boolean status = false;
            try {
                status = unitOfWork.beginTransaction();
                Administrado administrado = administradoRepository.read(administradoId);
                Arma arma = armaRepository.read(armaId);
                
                armaCreateValidation.validate(armaCreateAssembler.toDto(arma));
                ReciboAbstraction recibo = new ReciboNull();                        
                recibo.setAdministrado(administrado);
                arma.setIsLocked(true);
                armaRepository.update(arma);                
                unitOfWork.commit(status);
                
                return new ResponseEntity<Object>(arma, HttpStatus.OK);
            } catch(IllegalArgumentException ex) {
        	unitOfWork.rollback(status);
        	return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
            } catch(Exception ex) {
                ex.printStackTrace();
        	unitOfWork.rollback(status);
		return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        @RequestMapping(
	    method = RequestMethod.GET,
	    path = "/all"
	)
	public ResponseEntity<Object> getAll(@PathVariable("administradoId") long administradoId) throws Exception {
            boolean status = false;
            try {
                List<Arma> lista = armaRepository.getAll(administradoId);
                return new ResponseEntity<Object>(armaCreateAssembler.toDtoList(lista), HttpStatus.OK);
            } catch(IllegalArgumentException ex) {
        	unitOfWork.rollback(status);
        	return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
            } catch(Exception ex) {
        	unitOfWork.rollback(status);
		return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
	
}
