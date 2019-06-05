package com.example.stani.asynctaskexample

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

class MainActivity : Activity() {

    //instance vars
    internal lateinit var progressBar: ProgressBar
    internal lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get reference to widgets
        progressBar = findViewById(R.id.progressBar)
        startButton = findViewById(R.id.button)

        //handle on click event
        startButton.setOnClickListener {
            //create an instance of Downloader class and exectue
            Downloader().execute()
        }

    }

    //inner class Downloader
    //AsyncTask runs in its own thread in the background void because we don't want to pass it
    internal inner class Downloader : AsyncTask<Void, Int, Int>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Toast.makeText(applicationContext, "Toast- onPreExecute", Toast.LENGTH_LONG).show()
            //set max value to 100
            progressBar.max = 100
        }


        override fun onProgressUpdate(values: Array<Int>) {
            super.onProgressUpdate(*values)
            Toast.makeText(applicationContext, "Toast- onProgressUpdate", Toast.LENGTH_LONG).show()


            //update progress bar
            progressBar.progress = values[0]
        }

        override fun doInBackground(vararg voids: Void): Int? {

            //simulate a time consuming task
            for (i in 0..99) {
                publishProgress(i)


                try {
                    Thread.sleep(100)

                } catch (ie: InterruptedException) {
                    ie.printStackTrace()
                }

            }

            return null
        }


        override fun onPostExecute(result: Int?) {
            super.onPostExecute(result)
            Toast.makeText(applicationContext, "Task Completed", Toast.LENGTH_LONG).show()

        }
    }
}
