package app.administrado.application.deserializer;

import app.administrado.application.dto.AdministradoDto;
import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.modelmapper.jackson.JsonNodeValueReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class AdministradoCreateDeserializer extends JsonDeserializer<AdministradoDto> {
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public AdministradoDto deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		AdministradoDto administradoCreateDto = null;
		String json = jsonParser.readValueAsTree().toString();
		JsonNode node = new ObjectMapper().readTree(json);
		modelMapper.getConfiguration()
		  .addValueReader(new JsonNodeValueReader());
		try {
                    administradoCreateDto = modelMapper.map(node, AdministradoDto.class);
		} catch(Exception ex) {
                    administradoCreateDto = new AdministradoDto();
		}
		return administradoCreateDto;
	}
}
