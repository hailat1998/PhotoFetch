package com.hd1998.photofetch

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
const val TAG="MainActivity"
class MainActivity : AppCompatActivity()  {
    private var photoRepo : PhotoRepo = PhotoRepo()


   @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch{
          val response = photoRepo.photoFetch()
            Log.d(TAG , "$response")
        }

}


}