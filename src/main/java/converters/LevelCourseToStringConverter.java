package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.LevelCourse;

@Component
@Transactional
public class LevelCourseToStringConverter implements Converter<LevelCourse, String> {

	@Override
	public String convert(LevelCourse levelCourse) {
		String result;

		if (levelCourse == null)
			result = null;
		else
			result = levelCourse.getValue();

		return result;
	}

}
