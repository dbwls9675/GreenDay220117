package Chapter15_test03;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerEx03 extends Thread {
	// ���� �����ڵ��� ���� ������ �����ϴ� �÷��� �غ�
	// ������ ���̵��� ������ �����ϰų� ���� ��Ʈ������ �����Ѵ�.
	static Hashtable<String, PrintWriter> map = new Hashtable<String, PrintWriter>();
	BufferedReader br;
	String userId;

	public ServerEx03(String userId, BufferedReader br) {
		this.userId = userId;
		this.br = br;
	}

	// main �����忡���� �����ڸ� ��ٸ��� ������ �Ѵ�.
	// �����ڰ� ���ӵǸ� �������� ��������� map�� �����ϰ�
	// �������� �����带 ���� ��Ų��.
	// ������� Ŭ���̾�Ʈ�� ���� �����͸� ����ϴ� ������ �Ѵ�.
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		String userId = "";

		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("�������� Ŭ���̾�Ʈ ���� �����...");
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		while (true) {
			try {
				Socket socket = serverSocket.accept(); // ���� ���ö����� �����
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				userId = br.readLine();
				if ("no-user".equals(userId)) {
					System.out.println("����� ������ �����ϴ�!");
					throw new SocketException();
				}
				System.out.println(userId + "���� �����Ͽ����ϴ�.");
				pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
				map.put(userId, pw);
				// ���� ���� �� ������ ����
				new ServerEx03(userId, br).start();
			} catch (SocketException e) {
				try {
					if (br != null)
						br.close();
					if (pw != null)
						pw.close();
					System.out.println(userId + "Ŭ���̾�Ʈ ������ ���������ϴ�.");

				} catch (IOException e1) {
					System.out.println("Ŭ���̾�Ʈ ���� ��ü ����!");
				}
			} catch (IOException e) {
				System.out.println(userId + "������ ����� ����");
			}
		} // end of While...
	}

	@Override
	public void run() {
		while (true) {
			String line = null;
			try {
				line = br.readLine();
				Enumeration<String> keys = map.keys(); // userId�� key
				while (keys.hasMoreElements()) {
					String key = keys.nextElement();
					PrintWriter pw = map.get(key);
					pw.println(userId + " : " + line);
					pw.flush();
				}
			} catch (SocketException e) {
				try {
					if (br != null)
						br.close();
					System.out.println(userId+"Ŭ���̾�Ʈ ������ ���������ϴ�.");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;

			} catch (IOException e) {
				System.out.println("Ŭ���̾�Ʈ ������ ���������ϴ�.");
				break;
			}
		}
	}
}
