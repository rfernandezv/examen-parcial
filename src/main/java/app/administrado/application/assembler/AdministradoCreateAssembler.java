package app.administrado.application.assembler;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import app.common.domain.valueobject.Money;
import app.common.domain.valueobject.MoneyAbstraction;
import app.administrado.application.dto.AdministradoCreateDto;
import app.administrado.application.dto.AdministradoDto;
import app.administrado.application.dto.AdministradoListDto;
import app.administrado.domain.entity.Administrado;
import app.recibo.domain.entity.Recibo;
import app.arma.domain.entity.Arma;
import java.util.HashSet;
import java.util.Set;

@Component
public class AdministradoCreateAssembler {
	
	public Administrado toEntity(AdministradoCreateDto administradoCreateDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		Administrado customer = modelMapper.map(administradoCreateDto, Administrado.class);
		return customer;
	}
	
	public List<Administrado> toEntityList(List<AdministradoCreateDto> administradoCreateListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<Administrado> administradoList = modelMapper.map(administradoCreateListDto, new TypeToken<List<Administrado>>() {}.getType());
		return administradoList;
	}       
        
	private Converter<AdministradoCreateDto, Administrado> getConverter() {
		Converter<AdministradoCreateDto, Administrado> converter = new Converter<AdministradoCreateDto, Administrado>() {
		    @Override
		    public Administrado convert(MappingContext<AdministradoCreateDto, Administrado> context) {
		    	AdministradoCreateDto administradoCreateDto =  AdministradoCreateDto.class.cast(context.getSource());
		        Administrado administrado = new Administrado();
		        administrado.setFirstName(administradoCreateDto.getFirstName());
		        administrado.setLastName(administradoCreateDto.getLastName());
                        administrado.setIsActive(true);
                        administrado.setIdentityDocument(administradoCreateDto.getIdentityDocument());
                        administrado.setRecibos(new HashSet<Recibo>());
                        administrado.setArmas(new HashSet<Arma>());
                        
                        Recibo recibo = new Recibo();
                        MoneyAbstraction balance = new Money(administradoCreateDto.getBalance(), administradoCreateDto.getCurrency());
                        recibo.setCode(administradoCreateDto.getCode());
                        recibo.setProcess(administradoCreateDto.getProcess());
                        recibo.setBalance(balance);                        
                        recibo.setAdministrado(administrado);
                        administrado.getRecibos().add(recibo);
                        
                        Arma arma = new Arma();
		        arma.setSerie(administradoCreateDto.getSerie());
		        arma.setBrand(administradoCreateDto.getBrand());
                        arma.setModel(administradoCreateDto.getModel());
                        arma.setIsLocked(administradoCreateDto.getIsActive());
                        arma.setAdministrado(administrado);
                        administrado.getArmas().add(arma);
		    	return administrado;
		    }
		};
		return converter;
	}
	
	public AdministradoDto toDto(Administrado administrado) {
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Administrado, AdministradoDto> map = new PropertyMap<Administrado, AdministradoDto>() {
                    protected void configure() {
                          map().setId(source.getId());
                          map().setFirstName(source.getFirstName());
                          map().setLastName(source.getLastName());
                          map().setIdentityDocument(source.getIdentityDocument());
                          map().setIsActive(source.getIsActive());
                    }
		};
		modelMapper.addMappings(map);
		AdministradoDto customeDto = modelMapper.map(administrado, AdministradoDto.class);
		return customeDto;
	}
        
        public List<AdministradoListDto> toDtoList(List<Administrado> administradoList) {
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Administrado, AdministradoListDto> map = new PropertyMap<Administrado, AdministradoListDto>() {
                    protected void configure() {
                          map().setFirstName(source.getFirstName());
                          map().setLastName(source.getLastName());
                          map().setIdentityDocument(source.getIdentityDocument());
                          map().setIsActive(source.getIsActive());                        
                    }
		};
		modelMapper.addMappings(map);                
                
                List<AdministradoListDto> administradoInListDto = modelMapper.map(administradoList, new TypeToken<List<AdministradoListDto>>() {}.getType());
		return administradoInListDto;
	}
        
}
