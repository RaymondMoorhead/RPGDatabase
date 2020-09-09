//import * as math from "math.min.js";

function roll(input) {
	var lastIndex = 0;
	var nextDice = input.indexOf('d');
	var resultString = "";
	var resultDice;
	while(nextDice != -1)
	{
		var startDice = nextDice - 1;
		if(startDice < 0)
			startDice = 0;
		else {
			while(startDice >= 0 &&
					input.charAt(startDice) >= '0' &&
					input.charAt(startDice) <= '9')
				--startDice;
			++startDice
		}
		
		var endDice = nextDice + 1;
		if(endDice == input.length)
			endDice = -1;
		else {
			while(endDice < input.length &&
					input.charAt(endDice) >= '0' &&
					input.charAt(endDice) <= '9')
				++endDice;
		}
		
		if(endDice != -1) {
			resultDice = parseDice(input.substring(startDice, endDice));
			if(resultDice !== false)
				resultString = resultString.concat(resultDice);
			else
				resultString = resultString.concat(input.substring(startDice, endDice));
			lastIndex = endDice;
		}
		else
		  lastIndex = nextDice;
		++nextDice;
		nextDice = input.indexOf('d', nextDice);
	}
	resultString = resultString.concat(input.substr(lastIndex));
	
	return eval(resultString);//math.evaluate(resultString);
};

function parseDice(input) {
	var quantity;
	var size;
	// parse die rolls
	var dIndex = input.indexOf('d');
		
	if((dIndex == -1) && ((dIndex = input.indexOf('D')) == -1))
		return false;
			
		
	// parse quantity
	// this means that "d20" defaults to "1d20"
	quantity = 1;
	if(dIndex > 0)
		quantity = parseInt(input.substring(0, dIndex));
		
	// parse size
	size = parseInt(input.substring(++dIndex));
		
	// final safety checks
	if(quantity < 0 || size < 0)
		return false;
		
	// lock-in the result
	var result = 0;
	for(var i = 0; i < quantity; ++i)
		result += Math.floor(Math.random() * size) + 1;

	return result;//result;
};