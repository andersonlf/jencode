package com.andersonlfeitosa.jencode.command;

import java.util.HashMap;
import java.util.Map;

import com.andersonlfeitosa.jencode.exception.CommandNotFound;
import com.andersonlfeitosa.jencode.exception.JencodeError;
import com.andersonlfeitosa.jencode.util.StringUtils;

public class CommandRepository {
	
	private static final CommandRepository instance = new CommandRepository();
	
	@SuppressWarnings("rawtypes")
	private Map<String, Class> repository = new HashMap<>(); 
	
	private CommandRepository() {
		repository.put("e", EncodeCommand.class);
		repository.put("encode", EncodeCommand.class);
		
		repository.put("d", DecodeCommand.class);
		repository.put("decode", DecodeCommand.class);
	}

	public static CommandRepository getInstance() {
		return instance;
	}
	
	public Command getCommand(String operation) throws CommandNotFound {
		String cmd = StringUtils.toLowerCaseWithTrim(operation);
		Command command = null;
		
		if (!repository.containsKey(cmd)) {
			throw new CommandNotFound("command not found");
		}
		
		try {
			command = (Command) repository.get(cmd).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new JencodeError("ops! how are you?");
		}
		
		return command;
	}

}
