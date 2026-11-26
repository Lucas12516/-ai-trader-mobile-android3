
package com.aitrader.mobile

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private val background = Color.rgb(8, 11, 18)
    private val green = Color.rgb(0, 217, 165)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(28, 50, 28, 28)
        layout.setBackgroundColor(background)

        val title = TextView(this)
        title.text = "AI TRADER MOBILE"
        title.textSize = 26f
        title.setTextColor(green)
        title.gravity = Gravity.CENTER
        title.setPadding(0, 30, 0, 12)
        layout.addView(title)

        val subtitle = TextView(this)
        subtitle.text = "Painel de controle • MetaTrader 5"
        subtitle.textSize = 14f
        subtitle.setTextColor(Color.LTGRAY)
        subtitle.gravity = Gravity.CENTER
        subtitle.setPadding(0, 0, 0, 35)
        layout.addView(subtitle)

        val label = TextView(this)
        label.text = "Endereço do servidor"
        label.textSize = 15f
        label.setTextColor(Color.WHITE)
        layout.addView(label)

        val urlInput = EditText(this)
        urlInput.hint = "https://seu-servidor.com"
        urlInput.setHintTextColor(Color.GRAY)
        urlInput.setTextColor(Color.WHITE)
        urlInput.setSingleLine(true)
        urlInput.setPadding(18, 14, 18, 14)
        urlInput.setBackgroundColor(Color.rgb(28, 34, 46))
        layout.addView(urlInput)

        val connectButton = Button(this)
        connectButton.text = "CONECTAR AO TRADER"
        connectButton.setTextColor(Color.BLACK)
        connectButton.setBackgroundColor(green)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.topMargin = 24
        layout.addView(connectButton, params)

        val info = TextView(this)
        info.text = "MODO DEMO\n\nO aplicativo é o painel móvel. O servidor conectado executa a estratégia e a comunicação com o MT5."
        info.textSize = 13f
        info.setTextColor(Color.LTGRAY)
        info.setPadding(0, 35, 0, 0)
        layout.addView(info)

        setContentView(layout)

        connectButton.setOnClickListener {
            val url = urlInput.text.toString().trim()

            if (url.isEmpty()) {
                Toast.makeText(this, "Informe o endereço do servidor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!url.startsWith("https://") && !url.startsWith("http://")) {
                Toast.makeText(this, "Informe um endereço HTTP ou HTTPS válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            abrirPainel(url)
        }
    }

    private fun abrirPainel(url: String) {
        val webView = WebView(this)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        setContentView(webView)
        webView.loadUrl(url)
    }
}
