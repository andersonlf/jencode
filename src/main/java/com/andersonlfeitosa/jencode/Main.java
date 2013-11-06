package com.andersonlfeitosa.jencode;

import com.andersonlfeitosa.jencode.command.Command;
import com.andersonlfeitosa.jencode.command.CommandRepository;
import com.andersonlfeitosa.jencode.command.Context;
import com.andersonlfeitosa.jencode.command.Result;
import com.andersonlfeitosa.jencode.exception.CommandNotFound;
import com.andersonlfeitosa.jencode.exception.OperationException;

public class Main {

	public static void main(String[] args) {
		if (args == null || args.length < 2) {
			exitWithError();
		}
		
		try {
			Command command = CommandRepository.getInstance().getCommand(args[0]);
			Context ctx = new Context();
			ctx.setParameter(args[1]);
			Result result = command.execute(ctx);
			showMessage(result.getResult().toString());
		} catch (CommandNotFound e) {
			exitWithError();
		} catch (OperationException e) {
			exitWithError(e.getMessage());
		}
		
		exit();
	}
	
	private static void exitWithError() {
		StringBuilder sb = new StringBuilder();
		sb.append("usage: java -jar jencode.jar param1 param2");
		sb.append("\n");
		sb.append("param1: e|d or encode|decode");
		sb.append("\n");
		sb.append("param2: password to encode or hash to decode");
		
		exitWithError(sb.toString());
	}

	private static void exitWithError(String message) {
		showMessage(message);
		exit(1);
	}

	private static void showMessage(String message) {
		System.out.println(message);
	}
	
	private static void exit() {
		exit(0);
	}
	
	private static void exit(int exitCode) {
		System.exit(exitCode);
	}
	
}
