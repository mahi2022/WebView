package com.mahi.webview

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt.setOnClickListener (this@MainActivity)
        fb.setOnClickListener(this@MainActivity)
        gl.setOnClickListener(this@MainActivity)
        you.setOnClickListener(this@MainActivity)
        html.setOnClickListener(this@MainActivity)

        wview.webViewClient=WebViewClient()
        wview.settings.javaScriptEnabled=true
        wview.settings.builtInZoomControls=true

        val pDialog=ProgressDialog(this@MainActivity)
        pDialog.setTitle("Message")
        pDialog.setMessage("plz wait........")
        pDialog.setCancelable(false)

        wview.webViewClient=object :WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pDialog.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pDialog.dismiss()
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                Toast.makeText(this@MainActivity,view!!.url,Toast.LENGTH_LONG).show()
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    } // onCreate

    override fun onClick(v: View?) {
        if(v!=null)
            when(v.id){
                R.id.bt -> {
                    if (ed1.text.toString().startsWith("https://", false)) {
                        wview.loadUrl(ed1.text.toString())

                    } else {
                        wview.loadUrl("https://www." + ed1.text.toString()+".com")

                    }
                }
                R.id.gl -> { wview.loadUrl("http://www.google.com")}
                R.id.fb -> { wview.loadUrl("http://www.facebook.com") }
                R.id.you->{wview.loadUrl("http://www.youtube.com") }
                R.id.html-> {wview.loadUrl("https://www.facebook.com/mahesh.love.771") }
            }


    }

} // Main

