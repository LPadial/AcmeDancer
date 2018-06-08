package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.StatusApplication;

@Component
@Transactional
public class StatusApplicationToStringConverter implements Converter<StatusApplication, String> {

	@Override
	public String convert(StatusApplication statusApplication) {
		String result;
		if (statusApplication == null)
			result = null;
		else
			result = statusApplication.getValue().toString();

		return result;
	}

}