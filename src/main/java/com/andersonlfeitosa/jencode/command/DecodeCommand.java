package com.andersonlfeitosa.jencode.command;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.andersonlfeitosa.jencode.exception.OperationException;

public class DecodeCommand implements Command {

	@Override
	public Result execute(Context ctx) throws OperationException {
		Result r = null;
		try {
			r = new Result();
			r.setResult(decode(ctx.getParameter()));
		} catch (InvalidKeyException | NoSuchPaddingException
				| NoSuchAlgorithmException | BadPaddingException
				| IllegalBlockSizeException | NumberFormatException e) {
			throw new OperationException(
					"was not possible to perform the operation: '"
							+ e.getMessage() + "'.");
		}

		return r;
	}

	private static String decode(String secret) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
			IllegalBlockSizeException {
		byte[] kbytes = "jaas is the way".getBytes();
		SecretKeySpec key = new SecretKeySpec(kbytes, "Blowfish");

		BigInteger n = new BigInteger(secret, 16);
		byte[] encoding = n.toByteArray();

		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(2, key);
		byte[] decode = cipher.doFinal(encoding);
		return new String(decode);
	}

}
