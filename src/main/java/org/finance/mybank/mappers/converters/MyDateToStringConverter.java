package org.finance.mybank.mappers.converters;

import ma.glasnost.orika.converter.builtin.DateToStringConverter;
import org.springframework.stereotype.Component;

import java.util.Locale;

import static org.finance.mybank.util.Constants.DATE_FORMAT;

@Component
public class MyDateToStringConverter extends DateToStringConverter {
	public MyDateToStringConverter() {
		super(DATE_FORMAT, Locale.getDefault());
	}
}
