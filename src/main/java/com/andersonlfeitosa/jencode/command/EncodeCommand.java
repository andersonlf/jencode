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

public class EncodeCommand implements Command {

	@Override
	public Result execute(Context ctx) throws OperationException {
		Result r = null;
		try {
			r = new Result();
			r.setResult(encode(ctx.getParameter()));
		} catch (InvalidKeyException | NoSuchPaddingException
				| NoSuchAlgorithmException | BadPaddingException
				| IllegalBlockSizeException | NumberFormatException e) {
			throw new OperationException("was not possible to perform the operation: '" + e.getMessage() + "'.");
		}
		
		return r;
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

}
