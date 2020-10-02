package org.finance.mybank.mappers;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperService {
	private final MapperFactory mapperFactory = (new DefaultMapperFactory.Builder()).build();

	public <S, D> D map(S source, Class<D> destinationClass) {
		return mapperFactory.getMapperFacade().map(source, destinationClass);
	}

	@Autowired
	public void setCustomConverters(List<CustomConverter<?, ?>> converters) {
		converters.forEach(mapperFactory.getConverterFactory()::registerConverter);
	}
}
