package com.sephora.shop.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.sephora.shop.R
import com.sephora.shop.base.BaseActivity
import com.sephora.shop.databinding.ActivityProductDetailBinding
import com.sephora.shop.model.productlist.ProductItem
import com.sephora.shop.model.productlist.ProductListResponse
import com.sephora.shop.util.Constants
import com.sephora.shop.util.getOfferPrice
import kotlinx.android.synthetic.main.activity_product_detail.*
import java.lang.Exception


class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var productItem : ProductItem? = intent.getParcelableExtra(Constants.PRODUCT_ITEM_KEY)
        displayLargeImage(productItem?.attributes?.closeupImageUrls?.get(0))

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
        var prodBrand : String? = getBrandName(productItem?.id, intent.getParcelableExtra(Constants.PRODUCT_LIST_KEY))
        var iconList : List<String?>? = productItem?.attributes?.cartImageUrls
        displayThumpImages(iconList, productItem)
        supportActionBar?.title = prodBrand
        txtViewProdBrand.text = prodBrand
        txtViewProdName.text = productItem?.attributes?.name
        ratingBarProductRating.rating = productItem?.attributes?.rating!!
        val discPrice : Int? = productItem?.attributes?.price
        val originalPrice = productItem?.attributes?.originalPrice
        var offerPrice = getOfferPrice(originalPrice, discPrice)

        txtViewPrice.text = if(discPrice == originalPrice) offerPrice else getSpannableText(offerPrice, originalPrice, discPrice)

        txtDesc.text = productItem?.attributes?.description
        txtUsage.text = productItem?.attributes?.benefits
    }

    private fun displayThumpImages(iconList: List<String?>?, productItem : ProductItem?) {
        for(imageName in iconList!!) {
            var layoutThumpImages =
                LayoutInflater.from(applicationContext).inflate(R.layout.layout_thump_images, binding.linearHorizontalThumps, false)
            Glide.with(this)
                .load(imageName)
                .into(layoutThumpImages as AppCompatImageView)
            binding.linearHorizontalThumps.addView(layoutThumpImages)

            layoutThumpImages.setOnClickListener {
                try {
                    var postion: Int = binding.linearHorizontalThumps?.indexOfChild(it)
                    displayLargeImage(productItem?.attributes?.closeupImageUrls?.get(postion))
                } catch (exception : Exception) {
                    Log.e("Exception", exception.message!!)
                }
            }
        }
    }

    private fun displayLargeImage(imageUrl: String?) {
        Glide.with(this)
            .load(imageUrl)
            .into(binding.imgViewProdThump)
    }

    override fun layoutId(): Int {
        return R.layout.activity_product_detail
    }

    private fun getBrandName(id: String?, productListResponse: ProductListResponse?): String? {
        var included  = productListResponse?.included
        for(productItem in included!!) {
            if(productItem?.relationships?.product?.data?.id.equals(id)) {
                return productItem?.attributes?.brandName
            }
        }
        return "--"
    }
}