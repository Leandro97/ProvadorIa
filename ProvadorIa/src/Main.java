import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	static ArrayList<Character> retorno = new ArrayList<Character>();
	public static boolean testa(String texto) {
		//String atomos = "[ \\t\\n\\x0B\\f\\r]?[a-zA-Z][ \\t\\n\\x0B\\f\\r]?";
		String atomo = "[a-zA-Z]";
		String expressao = "[a-zA-Z]->[a-zA-Z]";
		
		if(Pattern.matches(atomo, texto) || Pattern.matches(expressao, texto)) {
			return true;
		}
		
		return false;
	}
	
	public static void geraConhecimento(ArrayList<String> atomos, ArrayList<String> expressoes) {
		for(int i = 0; i < atomos.size(); i++) {
			String a = (String)atomos.get(i);
			for(int j = 0; j < expressoes.size(); j++) {
				String e = (String)expressoes.get(j);
				
				if(a.charAt(0) == e.charAt(0)) {
					retorno.add(e.charAt(3));
					atomos.remove(i);
					expressoes.remove(j);
					atomos.add(Character.toString(e.charAt(3)));
					i--;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		ArrayList<String> atomos = new ArrayList<String>();
		ArrayList<String> expressoes = new ArrayList<String>();
		String conclusao;
		
		System.out.println("Digite as premissas (uma por linha). Digite # para terminar.");
		for(;;) {
			String texto = read.nextLine();
			String temp1 = texto.trim();
			texto = temp1.replace(" ", "");
			
			if(texto.equals("#")) break;
			if(!testa(texto)) {
				System.out.println("Expressão inválida! Informe uma expressão do tipo \"A\" ou \"A->B\"");
			} else if(texto.length() == 1){	
				atomos.add(texto);
			} else {
				expressoes.add(texto);
			}
		}
		
		System.out.println("Digite a conclusão");
		conclusao = read.nextLine().trim();

		Collections.sort(atomos.subList(0, atomos.size()));
		Collections.sort(expressoes.subList(0, expressoes.size()));
//		System.out.println(atomos);
//		System.out.println(expressoes);
		
		geraConhecimento(atomos, expressoes);
		
		if(retorno.size() != 0) {
			System.out.println("A partir das informações dadas podemos concluir que: " + retorno);
		} else {
			System.out.println("Não é possível chegar a uma conclusão com os dados informados.");
		}
		
		int aux = 0;
		for(int i = 0; i < retorno.size(); i++) {
			if(retorno.get(i).toString().equals(conclusao)) {
				System.out.println("É possível concluir que " + conclusao + " é válido.");
				aux++;
				break;
			} 
		}
		
		if(aux == 0) {
			System.out.println("Não é possível concluir " + conclusao);
		}
		
	}

}
