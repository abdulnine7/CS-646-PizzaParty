/**  Package declaration */
package com.zybooks.pizzaparty

/**  Import statements */
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

/**  Constant declaration: Number of slices per pizza */
const val SLICES_PER_PIZZA = 8

/** MainActivity class declaration, extending AppCompatActivity */
class MainActivity : AppCompatActivity() {

    /** Declaring variables for UI elements */
    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup

    /** onCreate method, called when the activity is starting */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** Set the layout for the activity */
        setContentView(R.layout.activity_main)
        /** Initialize UI elements */
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)
    }

    /**
     * Function to be called when the "Calculate" button is clicked from the app.
     * function reads the text input value of Number of People and Radio button's checked value.
     * it calculate the total pizzas using the formula in method and set the result text in
     * total pizza text view and display on the screen
    */
    fun calculateClick(view: View) {
        /** Get the text that was typed into the EditText */
        val numAttendStr = numAttendEditText.text.toString()

        /** Convert the text into an integer */
        val numAttend = numAttendStr.toInt()

        /** Determine how many slices on average each person will eat*/
        val slicesPerPerson = when (howHungryRadioGroup.checkedRadioButtonId) {
            R.id.light_radio_button -> 2
            R.id.medium_radio_button -> 3
            else -> 4
        }

        /** Calculate the total number of pizzas needed to satisfy all attendees */
        val totalPizzas = ceil(numAttend * slicesPerPerson /
                SLICES_PER_PIZZA.toDouble()).toInt()

        /** Display the total number of pizzas needed in the TextView */
        numPizzasTextView.text = "Total pizzas: $totalPizzas"
    }
}