package app.arma.application.assembler;

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
import app.arma.application.dto.ArmaCreateDto;
import app.arma.application.dto.ArmaListDto;
import app.arma.domain.entity.Arma;

@Component
public class ArmaCreateAssembler {
	@Autowired
	protected UnitOfWorkHibernate unitOfWork;
	
	public Arma toEntity(ArmaCreateDto armaCreateDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		Arma project = modelMapper.map(armaCreateDto, Arma.class);
		return project;
	}
	
	public List<Arma> toEntityList(List<ArmaCreateDto> armaCreateListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<Arma> armaListDto = modelMapper.map(armaCreateListDto, new TypeToken<List<Arma>>() {}.getType());
		return armaListDto;
	}       
        
	private Converter<ArmaCreateDto, Arma> getConverter() {
		Converter<ArmaCreateDto, Arma> converter = new Converter<ArmaCreateDto, Arma>() {
		    @Override
		    public Arma convert(MappingContext<ArmaCreateDto, Arma> context) {
		    	ArmaCreateDto armaCreateDto =  ArmaCreateDto.class.cast(context.getSource());
		        Arma arma = new Arma();
		        arma.setSerie(armaCreateDto.getSerie());
                        arma.setBrand(armaCreateDto.getBrand());
		        arma.setModel(armaCreateDto.getModel());
                        
		        Administrado administrado = new Administrado();
		        administrado.setId(armaCreateDto.getAdministradoId());
		        arma.setAdministrado(administrado);
		    	return arma;
		    }
		};
		return converter;
	}
	
	public ArmaCreateDto toDto(Arma arma) {
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Arma, ArmaCreateDto> map = new PropertyMap<Arma, ArmaCreateDto>() {
		  protected void configure() {
			map().setSerie(source.getSerie());
                        map().setBrand(source.getBrand());
                        map().setModel(source.getModel());
                        map().setLocked(source.getIsLocked());
                        map().setAdministradoId(source.getAdministrado().getId());
		  }
		};
		modelMapper.addMappings(map);
		ArmaCreateDto armaCreateDto = modelMapper.map(arma, ArmaCreateDto.class);
		return armaCreateDto;
	}
        
        public List<ArmaListDto> toDtoList(List<Arma> armaList) {
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Arma, ArmaListDto> map = new PropertyMap<Arma, ArmaListDto>() {
                    protected void configure() {
                          map().setId(source.getId());
                          map().setSerie(source.getSerie());
                          map().setBrand(source.getBrand());
                          map().setModel(source.getModel());
                          map().setLocked(source.getIsLocked());
                    }
		};
		modelMapper.addMappings(map);                
                
                List<ArmaListDto> armaInListDto = modelMapper.map(armaList, new TypeToken<List<ArmaListDto>>() {}.getType());
		return armaInListDto;
	}
        
}
