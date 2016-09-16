package io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * decompressor decorator
 * @author Administrator
 *
 */
public class MyDecompressorInputStream extends InputStream {
	//input stream
	private DataInputStream in;
	/**
	 * c'tor
	 * @param in input stream
	 */
	public MyDecompressorInputStream(InputStream in) {
		this.in = new DataInputStream(in);
	}

	@Override
	/**
	 * reads an int from the input stream
	 */
	public int read() throws IOException {		
		//first bytes are always an int, so we read it like so
		return in.readInt();
	}
	
	@Override
	/**
	 * read from input stream to byte array
	 */
	public int read(byte[] arr) throws IOException {
		int k = 0;
		while (k < arr.length) {
			//need to convert the byte from signed to unsigned
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
