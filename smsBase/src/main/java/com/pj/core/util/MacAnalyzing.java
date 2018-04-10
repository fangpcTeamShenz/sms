package com.pj.core.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 
 * @author Linxs
 *
 */
public class MacAnalyzing {

	private String ip;
	private int iRemotePort = 137;
	private byte[] buffer = new byte[1024];
	private DatagramSocket datagramSocket = null;

	public MacAnalyzing(String ip) throws Exception {
		this.ip = ip;
		datagramSocket = new DatagramSocket();
	}

	protected final DatagramPacket send(final byte[] bytes) throws IOException {
		DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(ip), iRemotePort);
		datagramSocket.send(dp);
		return dp;
	}

	protected final DatagramPacket receive() throws Exception {
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		datagramSocket.receive(dp);
		return dp;
	}

	protected byte[] getQueryCmd() throws Exception {
		byte[] t_ns = new byte[50];
		t_ns[0] = 0x00;
		t_ns[1] = 0x00;
		t_ns[2] = 0x00;
		t_ns[3] = 0x10;
		t_ns[4] = 0x00;
		t_ns[5] = 0x01;
		t_ns[6] = 0x00;
		t_ns[7] = 0x00;
		t_ns[8] = 0x00;
		t_ns[9] = 0x00;
		t_ns[10] = 0x00;
		t_ns[11] = 0x00;
		t_ns[12] = 0x20;
		t_ns[13] = 0x43;
		t_ns[14] = 0x4B;

		for (int i = 15; i < 45; i++) {
			t_ns[i] = 0x41;
		}

		t_ns[45] = 0x00;
		t_ns[46] = 0x00;
		t_ns[47] = 0x21;
		t_ns[48] = 0x00;
		t_ns[49] = 0x01;
		
		return t_ns;
	}

	protected final String getMacAddr(byte[] brevdata) throws Exception {

		int i = brevdata[56] * 18 + 56;
		String sAddr = "";
		StringBuffer sb = new StringBuffer(17);

		for (int j = 1; j < 7; j++) {
			sAddr = Integer.toHexString(0xFF & brevdata[i + j]);
			if (sAddr.length() < 2) {
				sb.append(0);
			}
			sb.append(sAddr.toUpperCase());
			if (j < 6) {
				sb.append(':');
			}
		}
		return sb.toString();
	}

	public final void close() {
		try {
			datagramSocket.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public final String getRemoteMacAddress() throws Exception {
		byte[] bqcmd = getQueryCmd();
		send(bqcmd);
		DatagramPacket dp = receive();
		String smac = getMacAddr(dp.getData());
		close();

		return smac;
	}
	 
}
