package io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

	private DataOutputStream out;
	
	public MyCompressorOutputStream(OutputStream out) {
		this.out = new DataOutputStream(out);
	}
	
	@Override
	public void write(int b) throws IOException {
		out.writeInt(b);
	}
	
	@Override
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
