package app.recibo.application.assembler;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.common.domain.valueobject.Money;
import app.common.domain.valueobject.MoneyAbstraction;
import app.common.infrastructure.persistence.hibernate.UnitOfWorkHibernate;
import app.administrado.domain.entity.Administrado;
import app.recibo.application.dto.ReciboCreateDto;
import app.recibo.application.dto.ReciboListDto;
import app.recibo.domain.entity.Recibo;

@Component
public class ReciboCreateAssembler {
	@Autowired
	protected UnitOfWorkHibernate unitOfWork;
	
	public Recibo toEntity(ReciboCreateDto reciboCreateDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		Recibo project = modelMapper.map(reciboCreateDto, Recibo.class);
		return project;
	}
	
	public List<Recibo> toEntityList(List<ReciboCreateDto> reciboCreateListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<Recibo> recibotListDto = modelMapper.map(reciboCreateListDto, new TypeToken<List<Recibo>>() {}.getType());
		return recibotListDto;
	}       
        
	private Converter<ReciboCreateDto, Recibo> getConverter() {
		Converter<ReciboCreateDto, Recibo> converter = new Converter<ReciboCreateDto, Recibo>() {
		    @Override
		    public Recibo convert(MappingContext<ReciboCreateDto, Recibo> context) {
		    	ReciboCreateDto reciboCreateDto =  ReciboCreateDto.class.cast(context.getSource());
		    	MoneyAbstraction balance = new Money(reciboCreateDto.getBalance(), reciboCreateDto.getCurrency());
		        Recibo recibo = new Recibo();
		        recibo.setCode(reciboCreateDto.getCode());
                        recibo.setProcess(reciboCreateDto.getProcess());
		        recibo.setBalance(balance);
                        
		        Administrado administrado = new Administrado();
		        administrado.setId(reciboCreateDto.getAdministradoId());
		        recibo.setAdministrado(administrado);
		    	return recibo;
		    }
		};
		return converter;
	}
	
	public ReciboCreateDto toDto(Recibo recibo) {
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Recibo, ReciboCreateDto> map = new PropertyMap<Recibo, ReciboCreateDto>() {
		  protected void configure() {
			map().setCode(source.getCode());
                        map().setProcess(source.getProcess());
                        map().setBalance(source.getBalance().getAmount());
                        map().setCurrency(source.getBalance().getCurrencyAsString());
                        map().setLocked(source.getIsLocked());
                        map().setAdministradoId(source.getAdministrado().getId());
		  }
		};
		modelMapper.addMappings(map);
		ReciboCreateDto reciboCreateDto = modelMapper.map(recibo, ReciboCreateDto.class);
		return reciboCreateDto;
	}
        
        public List<ReciboListDto> toDtoList(List<Recibo> reciboList) {
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Recibo, ReciboListDto> map = new PropertyMap<Recibo, ReciboListDto>() {
                    protected void configure() {
                          map().setCode(source.getCode());
                          map().setProcess(source.getProcess());
                          map().setBalance(source.getBalance().getAmount());
                    }
		};
		modelMapper.addMappings(map);                
                
                List<ReciboListDto> reciboInListDto = modelMapper.map(reciboList, new TypeToken<List<ReciboListDto>>() {}.getType());
		return reciboInListDto;
	}
        
}
