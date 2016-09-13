package io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
	private DataInputStream in;
	
	public MyDecompressorInputStream(InputStream in) {
		this.in = new DataInputStream(in);
	}

	@Override
	public int read() throws IOException {		
		return in.readInt();
	}
	
	@Override
	public int read(byte[] arr) throws IOException {
		int k = 0;
		while (k < arr.length) {
			byte temp = (byte) in.read();
			int count = temp & 0xFF;
			byte b = (byte) in.read();
			
			for (int j = 0; j < count; j++) {
				arr[k++] = b;
			}
		}
		return arr.length;		
	}
}
