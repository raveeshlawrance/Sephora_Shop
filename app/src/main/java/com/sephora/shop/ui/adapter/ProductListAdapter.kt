package com.sephora.shop.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sephora.shop.R
import com.sephora.shop.listener.ProductSelectListener
import com.sephora.shop.model.productlist.ProductItem
import com.sephora.shop.model.productlist.ProductListResponse
import com.sephora.shop.util.getOfferPrice
import kotlinx.android.synthetic.main.list_item_product.view.*

class ProductListAdapter(
    appContext: Context?,
    private val productListResponse: ProductListResponse?,
    private val productSelectListener: ProductSelectListener
) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    class ProductListViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(productListResponse: ProductListResponse?, postition : Int) {

            itemView.apply {

                var productItem : ProductItem? = productListResponse?.data?.get(postition)
                txtViewProdName.text = productItem?.attributes?.name
                txtViewProdBrand.text = getBrandName(productItem?.id, productListResponse)
                ratingBarProductRating.rating = productItem?.attributes?.rating!!
                val discPrice : Int? = productItem?.attributes?.price
                val originalPrice = productItem?.attributes?.originalPrice
                var offerPrice = getOfferPrice(originalPrice, discPrice)

                txtViewPrice.text = if(discPrice == originalPrice) offerPrice else getSpannableText(offerPrice, originalPrice, discPrice)
                txtViewProdTypeCount.text = "6 shades"
                txtViewProdIcon.background
                Glide.with(this)
                    .load(productItem?.attributes?.defaultImageUrls?.get(0))
                    .into(txtViewProdIcon)

            }
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
                StyleSpan(Typeface.BOLD),
                originalPrice.toString().length + 2,
                originalPrice.toString().length + 2 + discPrice.toString().length + 2,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            return spannable
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_product, parent, false)
        return ProductListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        if (productListResponse != null) {
            holder.bind(productListResponse, position)
        }
        holder.itemView.setOnClickListener {
            productSelectListener.onSelectProduct(productListResponse, productListResponse?.data?.get(position))
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
