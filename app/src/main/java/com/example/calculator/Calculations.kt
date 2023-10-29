package com.example.calculator

class Calculations {
    companion object {
        fun calculateResults(workingsText: CharSequence): String {
            val digitsOperators = digitsOperators(workingsText)
            if (digitsOperators.isEmpty()) {
                return ""
            }

            val timesDivision = timesDivisionCalculate(digitsOperators)
            if (timesDivision.isEmpty()) {
                return ""
            }

            val result = addSubtractCalculate(timesDivision)
            return result.toString()
        }

        private fun addSubtractCalculate(passedList: MutableList<Any>): Float {
            var result = passedList[0] as Float

            for (i in passedList.indices) {
                if (passedList[i] is Char && i != passedList.lastIndex) {
                    val operator = passedList[i]
                    val nextDigit = passedList[i + 1] as Float

                    if (operator == '+') {
                        result += nextDigit
                    }
                    if (operator == '-') {
                        result -= nextDigit
                    }
                }
            }

            return result
        }

        private fun timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any> {
            var list = passedList
            while (list.contains('x') || list.contains('/')) {
                list = calcTimesDiv(list)
            }
            return list
        }

        private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any> {
            val newList = mutableListOf<Any>()
            var restartIndex = passedList.size

            for (i in passedList.indices) {
                if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex) {
                    val operator = passedList[i]
                    val prevDigit = passedList[i - 1] as Float
                    val nextDigit = passedList[i + 1] as Float

                    when (operator) {
                        'x' -> {
                            newList.add(prevDigit * nextDigit)
                            restartIndex = i + 1
                        }

                        '/' -> {
                            newList.add(prevDigit / nextDigit)
                            restartIndex = i + 1
                        }

                        else -> {
                            newList.add(prevDigit)
                            newList.add(operator)
                        }
                    }
                }

                if (i > restartIndex) {
                    newList.add(passedList[i])
                }
            }

            return newList
        }

        private fun digitsOperators(workingsText: CharSequence): MutableList<Any> {
            val list = mutableListOf<Any>()
            var currentDigit = ""

            for (character in workingsText) {
                if (character.isDigit() || character == '.') {
                    currentDigit += character
                }
                else {
                    list.add(currentDigit.toFloat())
                    currentDigit = ""
                    list.add(character)
                }
            }

            if(currentDigit != "") {
                list.add(currentDigit.toFloat())
            }

            return list
        }
    }
}