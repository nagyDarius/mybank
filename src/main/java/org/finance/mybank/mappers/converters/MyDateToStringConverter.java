package org.finance.mybank.mappers.converters;

import ma.glasnost.orika.converter.builtin.DateToStringConverter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MyDateToStringConverter extends DateToStringConverter {
	public MyDateToStringConverter() {
		super("yyyy-MM-dd", Locale.getDefault());
	}
}
