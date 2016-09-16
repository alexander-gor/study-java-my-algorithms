package io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/**
 * compression decorator 
 * @author Administrator
 *
 */
public class MyCompressorOutputStream extends OutputStream {
	//output stream
	private DataOutputStream out;
	/**
	 * c'tor 
	 * @param out output stream
	 */
	public MyCompressorOutputStream(OutputStream out) {
		this.out = new DataOutputStream(out);
	}
	
	@Override
	/**
	 * writes an int to the output stream
	 */
	public void write(int b) throws IOException {
		//we need to write the int as it is, it will be always the first one in the array
		out.writeInt(b);
	}
	
	@Override
	/**
	 * writes a byte array to the output stream
	 */
	public void write(byte[] arr) throws IOException {
		byte currByte = arr[0];
		int count = 1;
		
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != currByte) {
				while (count >= 255) {
					out.write(255);
					out.write(currByte);
					count -= 255;
				}
				out.write(count);
				out.write(currByte);
				currByte = arr[i];
				count = 1;
			}
			else {
				count++;
			}
		}
		out.write(count);
		out.write(currByte);
		
	}

}
