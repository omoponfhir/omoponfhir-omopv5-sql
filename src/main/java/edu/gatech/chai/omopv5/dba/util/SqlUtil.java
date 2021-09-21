package edu.gatech.chai.omopv5.dba.util;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

public class SqlUtil {
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(SqlUtil.class);

	public static String getSqlColumnName(Class<?> clazz, String columnVariable) {
		try {
			Field field = clazz.getDeclaredField(columnVariable);

			// We have Column and JoinColumn annotation to get the name of SQL Column Name.
			Column columnAnnotation = field.getAnnotation(Column.class);
			JoinColumn joinColumnAnnotation = field.getAnnotation(JoinColumn.class);
			if (columnAnnotation != null) {
				return columnAnnotation.name();
			}

			if (joinColumnAnnotation != null) {
				return joinColumnAnnotation.name();
			}
		} catch (NoSuchFieldException e) {
			// It's OK to not having this field if the table class has some relation mapping
			// (like f_person)
			// In this case, its parent may have it.
			e.printStackTrace();

			Class<?> parentClazz = clazz.getSuperclass();
			if (parentClazz != null) {
				try {
					// We update clazz to parent one.
					clazz = parentClazz;

					Field field = parentClazz.getDeclaredField(columnVariable);
					field.getAnnotation(Column.class);

					Column columnAnnotation = field.getAnnotation(Column.class);
					JoinColumn joinColumnAnnotation = field.getAnnotation(JoinColumn.class);
					if (columnAnnotation != null) {
						return columnAnnotation.name();
					}

					if (joinColumnAnnotation != null) {
						return joinColumnAnnotation.name();
					}
				} catch (NoSuchFieldException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				}
			}

			// If exception leads to here, then there is no recovery method.
			logger.error(columnVariable + " is missing in " + clazz.getCanonicalName());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String getTableName(Class<?> clazz) {
		Table tableAnnotation = clazz.getDeclaredAnnotation(Table.class);
		if (tableAnnotation == null) {
			clazz = clazz.getSuperclass();
			if (clazz == null) {
				logger.error("Annontation for Table class is null, and there is no parent class either");
				return null;
			}
			tableAnnotation = clazz.getDeclaredAnnotation(Table.class);
			if (tableAnnotation == null) {
				logger.warn("Annotation for Table class: " + clazz.getCanonicalName() + " is null");
				return null;
			}
		}

		return tableAnnotation.name();
	}

	public static Date string2Date(String dateString) {
		Date date = null;

		if (dateString != null && !dateString.isEmpty()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = dateFormat.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return date;
	}

	public static Date string2DateTime(String dateString) {
		Date date = null;

		if (dateString != null && !dateString.isEmpty()) {
			List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>();

			dateFormats.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			dateFormats.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

			for (SimpleDateFormat dateFormat: dateFormats) {
				try {
					date = dateFormat.parse(dateString);
					return date;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

		return date;
	}

}
