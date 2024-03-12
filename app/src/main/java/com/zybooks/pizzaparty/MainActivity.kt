/**  Package declaration */
package com.zybooks.pizzaparty

/**  Import statements */
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

/**  Constant declaration: Number of slices per pizza */

const val TAG = "MainActivity"

private const val KEY_TOTAL_PIZZAS = "totalPizzas"

/** MainActivity class declaration, extending AppCompatActivity */
class MainActivity : AppCompatActivity() {

    /** Declaring variables for UI elements */
    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup

    private var totalPizzas = 0
    /** onCreate method, called when the activity is starting */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** Set the layout for the activity */
        setContentView(R.layout.activity_main)

        /** Initialize UI elements */
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)

        Log.d(TAG, "onCreate was called")
        if (savedInstanceState != null) {
            totalPizzas = savedInstanceState.getInt(KEY_TOTAL_PIZZAS)
            displayTotal()
        }


        numAttendEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                numAttendEditText.setText("");
            }

            override fun afterTextChanged(s: Editable) {}
        })


    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TOTAL_PIZZAS, totalPizzas)
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    /**
     * Function to be called when the "Calculate" button is clicked from the app.
     * function reads the text input value of Number of People and Radio button's checked value.
     * it calculate the total pizzas using the formula in method and set the result text in
     * total pizza text view and display on the screen
    */
//    fun calculateClick(view: View) {
//        /** Get the text that was typed into the EditText */
//        val numAttendStr = numAttendEditText.text.toString()
//        Log.d(TAG,"number is $numAttendStr")
//        /** Convert the text into an integer */
//        val numAttend = numAttendStr.toInt()
//
//        /** Determine how many slices on average each person will eat*/
//        val slicesPerPerson = when (howHungryRadioGroup.checkedRadioButtonId) {
//            R.id.light_radio_button -> 2
//            R.id.medium_radio_button -> 3
//            else -> 4
//        }
//
//        /** Calculate the total number of pizzas needed to satisfy all attendees */
//        val totalPizzas = ceil(numAttend * slicesPerPerson /
//                SLICES_PER_PIZZA.toDouble()).toInt()
//
//        /** Display the total number of pizzas needed in the TextView */
//        numPizzasTextView.text = "Total pizzas: $totalPizzas"
//    }
    fun calculateClick(view: View) {

        // Get the text that was typed into the EditText
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.checkedRadioButtonId) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        val calc = PizzaCalculator(numAttend, hungerLevel)
        totalPizzas = calc.totalPizzas
        displayTotal()
        }
        // Place totalPizzas into the string resource and display
        private fun displayTotal() {
            val totalText = getString(R.string.total_pizzas_num, totalPizzas)
            numPizzasTextView.text = totalText

    }
}