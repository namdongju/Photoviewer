package com.example.st.myapplication

//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}
import android.util.Log
import android.os.Bundle
import android.annotation.SuppressLint
import io.reactivex.schedulers.Schedulers
import android.support.v7.app.AppCompatActivity
import com.example.st.myapplication.api.APIClient
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import io.reactivex.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lm = LinearLayoutManager(this)
        val mAdapter = Adapter(this)
        val apiservice = APIClient().getAPIService()


        recycleView.adapter = mAdapter
        recycleView.layoutManager = lm
        recycleView.setHasFixedSize(true)

        apiservice.getPhotos().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("KKK", "kkk=$it")
                    mAdapter.setData(it.photos)
                }, {
                })
    }
}