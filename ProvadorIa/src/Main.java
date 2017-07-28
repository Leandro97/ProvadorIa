import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	public static boolean testaP(String texto) {
		//String atomos = "[ \\t\\n\\x0B\\f\\r]?[a-zA-Z][ \\t\\n\\x0B\\f\\r]?";
		String atomo = "[a-zA-Z]";
		String expressao = "[a-zA-Z]->[a-zA-Z]";
		
		if(Pattern.matches(atomo, texto) || Pattern.matches(expressao, texto)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean testaC(String texto) {
		//String atomos = "[ \\t\\n\\x0B\\f\\r]?[a-zA-Z][ \\t\\n\\x0B\\f\\r]?";
		String atomo = "[a-zA-Z]";
		
		if(Pattern.matches(atomo, texto)) {
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		ArrayList atomos = new ArrayList<String>();
		ArrayList expressoes = new ArrayList<String>();
		ArrayList conclusoes = new ArrayList<String>();
		ArrayList retorno = new ArrayList<String>();
		
		System.out.println("Digite as premissas (uma por linha). Digite # para terminar.");
		for(;;) {
			String texto = read.nextLine();
			if(texto.equals("#")) break;
			if(!testaP(texto)) {
				System.out.println("Expressão inválida! Informe uma expressão do tipo \"A\" ou \"A->B\"");
			} else if(texto.length() == 1){	
				atomos.add(texto);
			} else {
				expressoes.add(texto);
			}
		}
		
		System.out.println("Digite as conclusões (uma por linha). Digite # para terminar.");
		for(;;) {
			String texto = read.nextLine();
			if(texto.equals("#")) break;
			if(!testaC(texto)) {
				System.out.println("Expressão inválida! Informe uma expressão do tipo \"A\" ou \"A->B\"");
			} else {	
				conclusoes.add(texto);
			}	
				
		}

		Collections.sort(atomos.subList(0, atomos.size()));
		Collections.sort(expressoes.subList(0, expressoes.size()));
		Collections.sort(conclusoes.subList(0, conclusoes.size()));
		System.out.println(atomos);
		System.out.println(expressoes);
		System.out.println(conclusoes);
	}

}
