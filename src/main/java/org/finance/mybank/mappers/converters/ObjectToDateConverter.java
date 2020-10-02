package org.finance.mybank.mappers.converters;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;
import org.finance.mybank.exception.MyInvalidDateException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.finance.mybank.util.Constants.DATE_FORMAT;

@Component
public class ObjectToDateConverter extends CustomConverter<Object, Date> {

	@Override
	public Date convert(Object source, Type<? extends Date> destinationType) {
		if (source instanceof String) {
			try {
				return new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse((String) source);
			} catch (ParseException e) {
				throw new MyInvalidDateException(source.toString());
			}
		}
		return null;
	}
}
