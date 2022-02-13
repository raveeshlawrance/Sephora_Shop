package com.sephora.shop.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sephora.shop.R
import com.sephora.shop.model.ProductItem
import com.sephora.shop.model.ProductListResponse

class ProductListAdapter (
    val context: Context?,
    val productListResponse: ProductListResponse?) : RecyclerView.Adapter<ProductListAdapter.UserListViewHolder>() {
    class UserListViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(productListResponse: ProductListResponse?, postition : Int) {

            itemView.apply {

                var prodIcon : AppCompatImageView =  findViewById(R.id.txtViewProdIcon)
                var prodName : AppCompatTextView =  findViewById(R.id.txtViewProdName)
                var prodBrand : AppCompatTextView =  findViewById(R.id.txtViewProdBrand)
                var prodRating : AppCompatRatingBar =  findViewById(R.id.ratingBarProductRating)
                var prodPrice : AppCompatTextView =  findViewById(R.id.txtViewPrice)
                var prodDiscount : AppCompatTextView =  findViewById(R.id.txtViewDiscount)
                var prodTypeCount : AppCompatTextView =  findViewById(R.id.txtViewProdTypeCount)

                var productItem : ProductItem? = productListResponse?.data?.get(postition)
                prodName.text = productItem?.attributes?.name
                prodBrand.text = getBrandName(productItem?.id, productListResponse)
                prodRating.rating = productItem?.attributes?.rating!!
                val discPrice : Int? = productItem?.attributes?.price
                val originalPrice = productItem?.attributes?.originalPrice
                var offerPrice = getOfferPrice(originalPrice, discPrice)

                prodPrice.text = if(discPrice == originalPrice) offerPrice else getSpannableText(offerPrice, originalPrice, discPrice)
                prodTypeCount.text = "6 shades"
                prodIcon.background
                Glide.with(this)
                    .load(productItem?.attributes?.defaultImageUrls?.get(0))
                    .into(prodIcon)

            }
        }

        private fun getOfferPrice(originalPrice: Int?, discPrice: Int?): String? {

            if(discPrice == originalPrice)
                return "$$originalPrice"
            var percentage : Float = ((originalPrice?.toFloat())!!.minus(discPrice!!.toFloat()))
            return "$" + originalPrice + "  $" + discPrice + "  (-" + (percentage?.div(originalPrice.toFloat()).times(100)) + "%)"
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

        private fun getSpannableText(offerPrice : String?, originalPrice: Int?, discPrice: Int?): Spannable? {
            val spannable = SpannableString(offerPrice)
            spannable.setSpan(
                StrikethroughSpan(),
                0, originalPrice.toString().length + 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(
                ForegroundColorSpan(Color.RED),
                originalPrice.toString().length + 4 + discPrice.toString().length,
                offerPrice?.length!!,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(
                StyleSpan(BOLD),
                originalPrice.toString().length + 2,
                originalPrice.toString().length + 2 + discPrice.toString().length + 2,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            return spannable
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_product, parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        if (productListResponse != null) {
            holder.bind(productListResponse, position)
        }
        holder.itemView.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return productListResponse?.data?.size?.let {
            productListResponse.data.size
        } ?: run {
            0
        }
    }

}
