package Chapter15_test02;
//15�� ����
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerEx02{

	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedWriter out = null;
		ServerSocket listener = null;
		Socket socket = null;
		Scanner scan = new Scanner(System.in);

		try {
			listener = new ServerSocket(9999);// ���� ���� ����
			System.out.println("������ ��ٸ��� �ֽ��ϴ�...");
			socket = listener.accept();// Ŭ���̾�Ʈ�κ��� ���� ��û ���
			System.out.println("����Ǿ����ϴ�.");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while (true) {
				String inputMessage = in.readLine();// Ŭ���̾�Ʈ�κ��� �� �� �б�
				if (inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("Ŭ���̾�Ʈ���� bye�� ������ �����Ͽ���");
					break;
				}
				System.out.println("Ŭ���̾�Ʈ : " + inputMessage);
				System.out.println("������>>");// ������Ʈ
				String outputMessage = scan.nextLine();
				out.write(outputMessage + "\n");
				out.flush();// out�� ��Ʈ�� ���ۿ� �ִ� ��� ���ڿ� ����
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				scan.close();
				socket.close();
				listener.close();
			} catch (IOException e) {
				System.out.println("Ŭ���̾�Ʈ�� ä�� �� ������ �߻��߽��ϴ�.");
			}
		}

	}

}
