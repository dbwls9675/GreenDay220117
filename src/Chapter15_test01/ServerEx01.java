package Chapter15_test01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerEx01 {

	public static void main(String[] args) {
		// �������� Ŭ���̾�Ʈ ��û ���
		byte[] by = new byte[65535];
		DatagramPacket packet = null;
		DatagramSocket socket = null;
		InetAddress ia = null;
		
		
		try {
			ia = InetAddress.getLocalHost();
			//System.out.println("LocalHost >>> "+ia.toString()); //ip�ּ� �ҷ�����
			socket = new DatagramSocket(7777,ia);
			packet = new DatagramPacket(by, by.length);
			System.out.println("���� �غ�...");
			socket.receive(packet);
			packet.setData(by);
			System.out.println(new String(by).trim());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
