package com.andersonlfeitosa.jencode;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Main {

	public static void main(String[] args) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
		short exitCode = 0;
		if (args.length >= 2) {
			String operation = args[0];
			String parameter = args[1];
			if ("e".equals(operation) || "encode".equals(operation)) {
				System.out.println(encode(parameter));
			} else if ("d".equals(operation) || "decode".equals(operation)) {
				System.out.println(decode(parameter));
			} else {
				showUsage();
				exitCode = 1;
			}
		} else {
			showUsage();
			exitCode = 1;
		}
		System.exit(exitCode);
	}

	private static void showUsage() {
		System.err.println("usage: java -jar jencode.jar param1 param2");
		System.err.println("param1: e|d or encode|decode");
		System.err.println("param2: password to encode or hash to decode");
	}

	private static String encode(String secret) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
			IllegalBlockSizeException {
		byte[] kbytes = "jaas is the way".getBytes();
		SecretKeySpec key = new SecretKeySpec(kbytes, "Blowfish");

		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(1, key);
		byte[] encoding = cipher.doFinal(secret.getBytes());
		BigInteger n = new BigInteger(encoding);
		return n.toString(16);
	}

	private static char[] decode(String secret) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
			IllegalBlockSizeException {
		byte[] kbytes = "jaas is the way".getBytes();
		SecretKeySpec key = new SecretKeySpec(kbytes, "Blowfish");

		BigInteger n = new BigInteger(secret, 16);
		byte[] encoding = n.toByteArray();

		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(2, key);
		byte[] decode = cipher.doFinal(encoding);
		return new String(decode).toCharArray();
	}

}
