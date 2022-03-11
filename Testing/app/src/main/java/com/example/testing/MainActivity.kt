package com.example.testing

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.loader.content.AsyncTaskLoader

private lateinit var container: ConstraintLayout
private lateinit var clickBt: Button
private lateinit var fruitLv: ListView
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVariables()

        val fruitAdapter: ArrayAdapter<String> = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            arrayOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"))
        fruitLv.adapter = fruitAdapter

        clickBt.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

            }
        })
    }

    private fun initVariables() {
        container = findViewById(R.id.container)
        clickBt = findViewById(R.id.clickBt)
        fruitLv = findViewById(R.id.fruitLv)
    }

    class changeTask: AsyncTask<Void,Void,Void>() {

        fun waitForOneSec(): Unit {
            val endTime: Long = System.currentTimeMillis() + 1000
            while (true) {
                val currentTime: Long = System.currentTimeMillis()
                if (currentTime > endTime) {
                    break
                }
            }
        }

        fun waitForNSec(time: Int): Unit {
            for (i: Int in 1..time) {
                waitForOneSec()
            }
        }

        override fun doInBackground(vararg params: Void?): Void {
            waitForNSec(10)
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            container.setBackgroundColor(ge.getColor(R.color.purple_200)
        }
    }
}