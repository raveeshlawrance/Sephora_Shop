package com.sephora.shop.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.sephora.shop.R
import com.sephora.shop.model.productlist.ProductItem

class ProductDetailActivity : AppCompatActivity() {
    lateinit var imgViewProdThump : AppCompatImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        imgViewProdThump = findViewById(R.id.imgViewProdThump)
        var productItem : ProductItem? = intent.getParcelableExtra("productItem")
        displayLargeImage(productItem?.attributes?.closeupImageUrls?.get(0))

        var iconList : List<String?>? = productItem?.attributes?.cartImageUrls
        var linearLayout : LinearLayout = findViewById(R.id.linearHorizontalThumps)
        for(imageName in iconList!!) {
            var layoutThumpImages =
                LayoutInflater.from(applicationContext).inflate(R.layout.layout_thump_images, linearLayout, false)
            Glide.with(this)
                .load(imageName)
                .into(layoutThumpImages as AppCompatImageView)
            linearLayout.addView(layoutThumpImages)

            layoutThumpImages.setOnClickListener({
                var postion : Int = linearLayout?.indexOfChild(it)
                displayLargeImage(productItem?.attributes?.closeupImageUrls?.get(postion))
            })
        }
    }

    private fun displayLargeImage(imageUrl: String?) {
        Glide.with(this)
            .load(imageUrl)
            .into(imgViewProdThump)
    }
}