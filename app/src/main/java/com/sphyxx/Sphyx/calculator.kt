package com.sphyxx.Sphyx

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*

class calculator : AppCompatActivity()
{
    private var canaddoperation = false
    private var canadddecimal = true

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
    }

    fun operationAction(view: View) {
        if(view is Button && canaddoperation)
        {workout.append(view.text)
            canaddoperation = false
        canadddecimal = true}

    }

    fun allClearAction(view: View) {
        workout.text = ""
        results.text = "" }

    fun backspaceAction(view: View){
        val length = workout.length()
        if(length > 0)
            workout.text = workout.text.subSequence(0, length - 1) }

    fun equalsAction(view: View) {
        results.text = calculateresults() }

private fun calculateresults(): String
{
    val digitsoperators = digitsoperators()
    if (digitsoperators.isEmpty()) return ""

    val  timesdivision = timesDivisionCalculate(digitsoperators)
    if (timesdivision.isEmpty()) return ""

    val result = addsubractcalc(timesdivision)
    return result.toString()
}

private fun addsubractcalc(passedList: MutableList<Any>): Float {
    var result = passedList[0] as Float

    for (i in passedList.indices) {
        if (passedList[i] is Char && i != passedList.lastIndex) {
            val operator = passedList[i]
            val nextDigit = passedList[i + 1] as Float
            if (operator == '+')
                result += nextDigit
            if (operator == '-')
                result -= nextDigit
        }
    }
    return result
}
private fun timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any>
{
    var list = passedList
    while (list.contains('x') || list.contains('/'))
    {
        list = calcTimesDiv(list)
    }
return list
}
    private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any>
    {
    val newlist = mutableListOf<Any>()
    var restartIndex = passedList.size

    for (i in passedList.indices)
    {
        if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex) {
            val operator = passedList[i]
            val prevDigit = passedList[i - 1] as Float
            val nextDigit = passedList[i + 1] as Float
            when (operator) {
                'x' -> {
                    newlist.add(prevDigit * nextDigit)
                    restartIndex = i + 1
                }
                '/' -> {
                    newlist.add(prevDigit / nextDigit)
                }
                else -> {
                    newlist.add(prevDigit)
                    newlist.add(operator)
                }
            }
        }
    if(i > restartIndex)
        newlist.add(passedList[i])
}
    return newlist

}

private fun digitsoperators(): MutableList<Any>
{
    val list = mutableListOf<Any>()
    var currentdigit = ""
    for (character in workout.text)
    {
        if(character.isDigit() || character == '.')
            currentdigit += character
        else {
            list.add(currentdigit.toFloat())
            currentdigit = ""
            list.add(character)
        }
    }

    if (currentdigit != "")
        list.add(currentdigit.toFloat())
    return list
}

fun numberAction(view: View)
{
        if (view is Button)
        {
            if (view.text == ".")
            {
                if (canadddecimal)
                    workout.append(view.text)

                canadddecimal = false
            }
            else
            workout.append(view.text)

            canaddoperation = true
        }
    }
}