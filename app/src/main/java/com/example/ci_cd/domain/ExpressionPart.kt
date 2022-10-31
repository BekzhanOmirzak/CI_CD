package com.example.ci_cd.domain

sealed interface ExpressionPart {
	data class Number(val number: Double) : ExpressionPart
	data class Op(val operation: Operation) : ExpressionPart
	data class Parentheses(val type: ParenthesesType) : ExpressionPart
}

enum class Operation(val operation: Char) {
	ADD('+'), MULTIPLY('*'), DIVISION('/'), SUBTRACT('-')
}

fun parseExpressionOperations(op: Char): Operation {
	for (enum in Operation.values()) {
		if (enum.operation == op)
			return enum
	}
	throw IllegalAccessException("Operation not found")
}

val operationSymbols = Operation.values().map { it.operation }.joinToString("")

enum class ParenthesesType {
	OPENING, CLOSING
}







