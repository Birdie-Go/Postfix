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
	 * ��ȡָ�룬�����ҵ�error��λ��
	 */
	static int point;
	/**
	 * ������Ϣ����
	 */
	static ArrayList<String> errorMessages;
	/**
	 * ����������
	 */
	static String rightIn;

	/**
	 * ��ʼ��Parser��
	 * lookahead��ȡ��һ���ַ���ָ��ָ��0��λ�ã���ʼ���մ�����Ϣ
	 * @throws IOException IOException
	 */
	public Parser() throws IOException {
		lookahead = System.in.read();
		point = 0;
		errorMessages = new ArrayList<String>();
		rightIn = new String();
	}

	/**
	 * �ж�lookahead�Ƿ������ַ���[0-9,+,-]
	 * @param lookahead lookahead
	 * @return true-���ڣ�false-������
	 */
	boolean isLegal(int lookahead) {
		return (Character.isDigit((char)lookahead) || lookahead == '+' || lookahead == '-');
	}

	/**
	 * �����������˵��������ַ������ַ�
	 * @throws IOException IOException
	 */
	void filter() throws IOException {
		while (!isLegal(lookahead) && lookahead != '\r' && lookahead != '\n') {
			if (lookahead == ' ')
				errorMessages.add("Error at " + String.valueOf(point) + " : ����ո�����");
			else
				errorMessages.add("Error at " + String.valueOf(point) + " : ���Ϸ��ַ�����");
			match(lookahead);
		}
	}

	/**
	 * Parser��parser���
	 * @throws IOException IOException
	 */
	void expr() throws IOException {
		term();
		rest();
	}

	/**
	 * ��ӡ������Ϣ�ʹ�����Ϣ
	 * @throws IOException IOException
	 */
	void printErrorMessages() throws IOException {
		if (errorMessages.size() == 0)
			return;
		System.out.println("");
		System.out.print("�������룺");
		System.out.println(rightIn);
		System.out.println("�»��߱�ʾ��ԭ�����λ��Ӧ��һ�����ֻ������������û�С�");
		System.out.println(String.valueOf(errorMessages.size()) + "������:");
		for (String errorMessage : errorMessages) {
            System.out.println(errorMessage);
        }
	}

	/**
	 * ƥ�������+-����ƥ����һ��term
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
				errorMessages.add("Error at " + String.valueOf(point) + " : ȱ�������");
				term();
				System.out.write('_');
			}
		}
	}

	/**
	 * ƥ��һ������
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
			errorMessages.add("Error at " + String.valueOf(point) + " : ȱ����������");
		}
	}

	/**
	 * ƥ���ַ�t������ȡ��һ��lookahead
	 * @param t ��ƥ���ַ�
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
		// System.out.println("��������ʱ�䣺" + (endTime - startTime) + "ms");
		System.out.println("\nEnd of program.");
	}
}
