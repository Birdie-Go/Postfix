import java.io.*;
import java.util.*;

/**
 * Parser
 */
class Parser {
	/**
	 * lookahead
	 */
	static int lookahead;
	/**
	 * 读取指针，用来找到error的位置
	 */
	static int point;
	/**
	 * 错误信息数组
	 */
	static ArrayList<String> errorMessages;
	/**
	 * 纠正的输入
	 */
	static String rightIn;

	/**
	 * 初始化Parser：
	 * lookahead读取第一个字符，指针指向0号位置，初始化空错误信息
	 * @throws IOException IOException
	 */
	public Parser() throws IOException {
		lookahead = System.in.read();
		point = 0;
		errorMessages = new ArrayList<String>();
		rightIn = new String();
	}

	/**
	 * 判断lookahead是否属于字符集[0-9,+,-]
	 * @param lookahead lookahead
	 * @return true-属于，false-不属于
	 */
	boolean isLegal(int lookahead) {
		return (Character.isDigit((char)lookahead) || lookahead == '+' || lookahead == '-');
	}

	/**
	 * 过滤器：过滤掉不属于字符集的字符
	 * @throws IOException IOException
	 */
	void filter() throws IOException {
		while (!isLegal(lookahead) && lookahead != '\r' && lookahead != '\n') {
			if (lookahead == ' ')
				errorMessages.add("Error at " + String.valueOf(point) + " : 多余空格输入");
			else
				errorMessages.add("Error at " + String.valueOf(point) + " : 不合法字符输入");
			match(lookahead);
		}
	}

	/**
	 * Parser：parser入口
	 * @throws IOException IOException
	 */
	void expr() throws IOException {
		term();
		rest();
	}

	/**
	 * 打印纠正信息和错误信息
	 * @throws IOException IOException
	 */
	void printErrorMessages() throws IOException {
		if (errorMessages.size() == 0)
			return;
		System.out.println("");
		System.out.print("纠正输入：");
		System.out.println(rightIn);
		System.out.println("下划线表示：原本这个位置应有一个数字或者运算符，但没有。");
		System.out.println(String.valueOf(errorMessages.size()) + "个错误:");
		for (String errorMessage : errorMessages) {
            System.out.println(errorMessage);
        }
	}

	/**
	 * 匹配运算符+-，并匹配下一个term
	 * @throws IOException IOException
	 */
	void rest() throws IOException {
		while (true) {
			filter();

			if (lookahead == '+') {
				rightIn += '+';
				match('+');
				term();
				System.out.write('+');
			} else if (lookahead == '-') {
				rightIn += '-';
				match('-');
				term();
				System.out.write('-');
			} else if (lookahead == '\r' || lookahead == '\n') {
				printErrorMessages();
				break;
			} else {
				rightIn += '_';
				errorMessages.add("Error at " + String.valueOf(point) + " : 缺少运算符");
				term();
				System.out.write('_');
			}
		}
	}

	/**
	 * 匹配一个数字
	 * @throws IOException IOException
	 */
	void term() throws IOException {
		filter();

		if (Character.isDigit((char)lookahead)) {
			rightIn += (char)lookahead;
			System.out.write((char)lookahead);
			match(lookahead);
		} else {
			rightIn += '_';
			System.out.write('_');
			errorMessages.add("Error at " + String.valueOf(point) + " : 缺少左运算量");
		}
	}

	/**
	 * 匹配字符t，并读取下一个lookahead
	 * @param t 待匹配字符
	 * @throws IOException IOException
	 */
	void match(int t) throws IOException {
		point++;
		lookahead = System.in.read();
	}
}

/**
 * Parser UI
 */
public class Postfix {
	/**
	 * Parser UI
	 * @param args None
	 * @throws IOException IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Input an infix expression and output its postfix notation:");
		// long startTime = System.currentTimeMillis();
		new Parser().expr();
		// long endTime = System.currentTimeMillis();
		// System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
		System.out.println("\nEnd of program.");
	}
}
