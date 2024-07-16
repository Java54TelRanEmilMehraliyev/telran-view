package terlan.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class SystemInputOutput implements InputOutput{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    PrintStream writer = System.out;
    
	@Override
	public String readString(String prompt) {
		writeLine(prompt);
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeString(String str) {
		writer.print(str);
		
	}
	public static void main(String[] args) {
        SystemInputOutput io = new SystemInputOutput();
        
        
        Integer number = io.readInt("Введите целое число: ", "Ошибка!");
        io.writeLine("Вы ввели целое число: " + number);

        Long longNum = io.readLong("Введите длинное целое число: ", "Ошибка!");
        io.writeLine("Вы ввели длинное целое число: " + longNum);

        Double doubleNum = io.readDouble("Введите дробное число: ", "Ошибка!");
        io.writeLine("Вы ввели дробное число: " + doubleNum);
        
        Double numberInRange = io.readNumberRange("Введите число в диапазоне от 10 до 20: ", "Ошибка! Число не в диапазоне.", 10, 20);
	    io.writeLine("Вы ввели число в диапозоне: " + numberInRange);
	}
}
