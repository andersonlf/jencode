package com.andersonlfeitosa.jencode.command;

import com.andersonlfeitosa.jencode.exception.OperationException;

public interface Command {
	
	Result execute(Context ctx) throws OperationException;

}
