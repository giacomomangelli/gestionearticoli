package it.gestionearticolijspservletjpamaven.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class UtilityArticoloForm {

	public static boolean validateInput(String codiceInputParam, String descrizioneInputParam,
			String prezzoInputStringParam, String dataArrivoStringParam) {

		if (StringUtils.isBlank(codiceInputParam) || StringUtils.isBlank(descrizioneInputParam)
				|| !NumberUtils.isCreatable(prezzoInputStringParam) || StringUtils.isBlank(dataArrivoStringParam)) {
			return false;
		}
		return true;
	}

	public static Date parseDateArrivoFromString(String dataArrivoStringParam) {
		if (StringUtils.isBlank(dataArrivoStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataArrivoStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}