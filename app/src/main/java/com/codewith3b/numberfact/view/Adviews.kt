package com.codewith3b.numberfact.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.codewith3b.numberfact.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize

@Composable
fun AdView(type:Int) {
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = {
            com.google.android.gms.ads.AdView(it).apply {
                adSize = AdSize.BANNER
                adUnitId = it.getString(if(type == 1) R.string.ad_id_banner_top else R.string.ad_id_banner_bottom)
                loadAd(AdRequest.Builder().build())
            }
        })
}

@Composable
fun AdViewInterstitial(type:Int) {
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = {
            com.google.android.gms.ads.AdView(it).apply {
                adSize = AdSize.FULL_BANNER
                adUnitId = it.getString(if(type == 1) R.string.ad_id_banner_top else R.string.ad_id_banner_bottom)
                loadAd(AdRequest.Builder().build())
            }
        })
}