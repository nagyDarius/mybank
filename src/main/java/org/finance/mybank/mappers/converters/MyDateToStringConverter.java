package org.finance.mybank.mappers.converters;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.finance.mybank.exception.MyInvalidDateException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.finance.mybank.util.Constants.DATE_FORMAT;

@Component
public class MyDateToStringConverter extends BidirectionalConverter<Date, String> {

	private final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);

	@Override
	public String convertTo(Date source, Type<String> destinationType) {
		return format.format(source);
	}

	@Override
	public Date convertFrom(String source, Type<Date> destinationType) {
		try {
			return format.parse(source);
		} catch (ParseException e) {
			throw new MyInvalidDateException(source);
		}
	}
}
