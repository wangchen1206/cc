package com.hp.cc.util;

import java.time.LocalDate;

public class DateHelper {
	public static LocalDate getDate(Integer preMonths) {
		if (preMonths != null && preMonths > 0) {
			LocalDate today = LocalDate.now();
			return today.minusMonths(preMonths);
		} else {
			return null;
		}
	}
}
