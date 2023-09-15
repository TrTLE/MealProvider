package com.example.mealprovider

import android.os.Bundle
import android.os.Handler
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import com.example.mealprovider.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_picker.number_picker
import kotlinx.android.synthetic.main.activity_picker_preview.*
import java.lang.reflect.InvocationTargetException
import kotlin.random.Random


class PickerActivity : AppCompatActivity() {

    private lateinit var mBinder: ActivityMainBinding
    private val maxPickerValue: Int
        get() {
            return number_picker.displayedValues.size
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picker_preview)
        setSupportActionBar(findViewById(R.id.toolbar))
        mBinder = ActivityMainBinding.inflate(layoutInflater)

        initNumberPicker()

        /* DEBUG
        println("nb_Picker size: " + number_picker.size)
        println("dataMeal:")
        dataMeal.forEach { println(it) }
        */

        initWheelButton()
    }

    private fun initNumberPicker() {
        number_picker.setBackgroundColor(0);

        val dataMeal = App.db.getAllMeal().map { it.NAME }.toTypedArray()
        number_picker.minValue = 0
        number_picker.maxValue = dataMeal.size - 1
        number_picker.displayedValues = dataMeal
    }

    private fun initWheelButton() {
        goto_btn.setOnClickListener {
            rollWheel(maxPickerValue * 3)
            val incrementValue = getRandomIncrement()
            rollWheel(incrementValue)
        }
    }

    private fun rollWheel(increment: Int) {
        val increasePicker2 = IncreaseValue(number_picker, increment)
        increasePicker2.run(0)
    }

    private fun getRandomIncrement(): Int {
        val value = number_picker.value
        val nextValue = Random.nextInt(0, maxPickerValue)
        val incrementValue = when (value > nextValue) {
            true -> nextValue + (maxPickerValue - value)
            false -> nextValue - value
        }

        /* DEBUG
        println("current Value $value : " + number_picker.displayedValues[value])
        println("max Picker Value: $maxPickerValue")
        println("id Next Meal: $nextValue")
        println("increment Value: $incrementValue")
        println("nextMeal name: " + number_picker.displayedValues[nextValue])
        */

        return incrementValue
    }

    internal class IncreaseValue(private val picker: NumberPicker, incrementValue: Int) {
        private val fire = Runnable { fire() }
        private val handler: Handler = Handler()
        private val increment: Increment
        private var counter = 0
        private var delay = 0L

        /*public*/
        fun run(startDelay: Long) {
            // This will execute the runnable passed to it (fire)
            handler.postDelayed(fire, startDelay)
            // after [startDelay in milliseconds], ASYNCHRONOUSLY.
        }

        private fun fire() {
            ++counter
            if (counter > increment.value) {
                return
            }
            try {
                // refelction call for
                // picker.changeValueByOne(true);
                val method = picker.javaClass.getDeclaredMethod(
                    "changeValueByOne",
                    Boolean::class.javaPrimitiveType
                )
                method.isAccessible = true
                method.invoke(picker, increment.isRoll)

            } catch (e: NoSuchMethodException) {

                e.printStackTrace()
            } catch (e: InvocationTargetException) {

                e.printStackTrace()
            } catch (e: IllegalAccessException) {

                e.printStackTrace()
            } catch (e: java.lang.IllegalArgumentException) {

                e.printStackTrace()
            }

            if (increment.isRoll) {

                val remainingValue = (increment.value - counter)
                // Increase roll delay when close to the end
                delay += when (remainingValue) {
                    in 4..5 -> 100
                    in 3..4 -> 150
                    in 2..3 -> 200
                    in 1..2 -> 250
                    in 0..1 -> 300
                    else -> delay
                }
            }
            // This will execute the runnable passed to it (fire)
            handler.postDelayed(fire, delay)
        }

        /*public*/
        init {
            increment = Increment(incrementValue)
        }

        private class Increment(incrementValue: Int) {
            val value: Int
            val isRoll: Boolean

            init {
                if (incrementValue > 0) {
                    isRoll = true
                    value = incrementValue
                } else {
                    isRoll = false
                    value = -incrementValue
                }
            }
        }
    }
}