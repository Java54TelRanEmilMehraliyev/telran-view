package terlan.view;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
	String readString(String prompt);

	void writeString(String str);

	default void writeLine(Object obj) {
		writeString(obj.toString() + "\n");
	}

	default <T> T readObject(String prompt, String errorPrompt, Function<String, T> mapper) {
		T res = null;
		boolean running = false;
		do {
			String str = readString(prompt);
			running = false;
			try {
				res = mapper.apply(str);
			} catch (RuntimeException e) {
				writeLine(errorPrompt + " " + e.getMessage());
				running = true;
			}

		} while (running);
		return res;
	}
    
	default <T> T readNumber( String prompt, String errorPromt,Function<String, T> parser) {
		while(true) {
			String str = readString(prompt);
			try {
				return parser.apply(str);
			} catch (RuntimeException e) {
				writeLine(errorPromt);
			}
		}
	}
	/**
	 * 
	 * @param prompt
	 * @param errorPrompt
	 * @return Integer number
	 */
	default Integer readInt(String prompt, String errorPrompt) {
		// DONE
		// Entered string must be a number otherwise, errorPrompt with cycle
		return readNumber(prompt, errorPrompt + "Эмиль, пожалуйста введи цифры типа INT", Integer::parseInt); 	
		}
	

	default Long readLong(String prompt, String errorPrompt) {
		// DONE
		// Entered string must be a number otherwise, errorPrompt with cycle
		 return readNumber(prompt, errorPrompt + "Эмиль, пожалуйста введи цифры типа Long", Long::parseLong);
		}

	default Double readDouble(String prompt, String errorPrompt) {
		// DONE
		// Entered string must be a number otherwise, errorPrompt with cycle
		return readNumber(prompt, errorPrompt + "Эмиль, пожалуйста введи цифры типа Double", Double::parseDouble);
	}

	default Double readNumberRange(String prompt, String errorPrompt, double min, double max) {
	    return readNumber(prompt, errorPrompt + " Число должно быть в диапазоне от " + min + " до " + (max - 1), str -> {
	        double number = Double.parseDouble(str);
	        if (number >= min && number < max) {
	            return number;
	        } else {
	            throw new RuntimeException("Число не входит в указанный диапазон.");
	        }
	    });
	}
	default String readStringPredicate(String prompt, String errorPrompt,
			Predicate<String> predicate) {
		//TODO
		//Entered String must match a given predicate
		return null;
	}
	default String readStringOptions(String prompt, String errorPrompt,
			HashSet<String> options) {
		//TODO
		//Entered String must be one out of a given options
		return null;
	}
	default LocalDate readIsoDate(String prompt, String errorPrompt) {
		//TODO
		//Entered String must be a LocalDate in format (yyyy-mm-dd)
		return null;
	}
	default LocalDate readIsoDateRange(String prompt, String errorPrompt, LocalDate from,
			LocalDate to) {
		//Entered String must be a LocalDate in format (yyyy-mm-dd) in the (from, to)(after from and before to)
		return null;
	}
	

}
