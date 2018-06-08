package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.LevelCourse;

@Component
@Transactional
public class StringToLevelCourseConverter implements Converter<String, LevelCourse> {

	@Override
	public LevelCourse convert(String text) {
		LevelCourse result;

		try {
			result = new LevelCourse();
			result.setValue(text);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
