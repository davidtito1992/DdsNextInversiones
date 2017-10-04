package converter;

import java.time.Year;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, Integer> {

	@Override
    public Integer convertToDatabaseColumn(Year anio) {
        return anio.getValue();
    }
 
    @Override
    public Year convertToEntityAttribute(Integer anio) {
        return Year.of(anio);
    }
}

