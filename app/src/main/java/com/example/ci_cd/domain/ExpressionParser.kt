package com.example.ci_cd.domain

class ExpressionParser(
	private val calculation: String
) {
	fun parse(): List<ExpressionPart> {

		val result = mutableListOf<ExpressionPart>()
		var i = 0
		while (i < calculation.length) {
			val curChar = calculation[i]
			when {
				curChar in operationSymbols -> {
					result.add(ExpressionPart.Op(parseExpressionOperations(curChar)))
					i++
				}
				curChar.isDigit() -> {
					i = parseNumber(i, result)
					continue
				}
				curChar in "()" -> {
					parseParenthesis(curChar, result)
					i++
				}
				else -> {
					throw  IllegalArgumentException("invalid symbol")
				}
			}
		}
		return result
	}

	private fun parseNumber(i: Int, result: MutableList<ExpressionPart>): Int {
		var at = i
		val builder = StringBuilder()
		while (at < calculation.length && (calculation[at].isDigit() || calculation[at] == '.')) {
			builder.append(calculation[at])
			at++
		}
		result.add(ExpressionPart.Number(builder.toString().toDouble()))
		return at
	}

	private fun parseParenthesis(curChar: Char, result: MutableList<ExpressionPart>) {
		if (curChar == '(')
			result.add(ExpressionPart.Parentheses(ParenthesesType.OPENING))
		else
			result.add(ExpressionPart.Parentheses(ParenthesesType.CLOSING))
	}
}
