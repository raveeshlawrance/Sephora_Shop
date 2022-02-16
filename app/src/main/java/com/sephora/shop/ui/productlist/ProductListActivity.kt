package com.sephora.shop.ui.productlist

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sephora.shop.R
import com.sephora.shop.base.BaseActivity
import com.sephora.shop.databinding.ActivityProductListBinding
import com.sephora.shop.listener.ProductSelectListener
import com.sephora.shop.model.productlist.ProductItem
import com.sephora.shop.model.productlist.ProductListResponse
import com.sephora.shop.model.productlist.ProductListViewModel
import com.sephora.shop.model.productlist.ProductListViewModelFactory
import com.sephora.shop.ui.adapter.ProductListAdapter
import com.sephora.shop.ui.details.ProductDetailActivity
import com.sephora.shop.util.Constants.PRODUCT_ITEM_KEY
import com.sephora.shop.util.Constants.PRODUCT_LIST_KEY

class ProductListActivity : BaseActivity<ActivityProductListBinding>(), ProductSelectListener {
    private lateinit var productViewModel: ProductListViewModel
    var productSelectListener : ProductSelectListener = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productViewModel = ViewModelProvider(this, ProductListViewModelFactory()).get(ProductListViewModel::class.java)

        productViewModel.getProductList()
        productViewModel._text.observe(this, Observer {
            var productListResponse : ProductListResponse = it
            binding.rvProductList.layoutManager =
                GridLayoutManager(applicationContext, 2)
            binding.rvProductList.adapter = ProductListAdapter(applicationContext, productListResponse, productSelectListener)
        })
    }

    override fun onSelectProduct(productListResponse : ProductListResponse?, productItem: ProductItem?) {
        var prodDetailsActivity = Intent(this, ProductDetailActivity::class.java)
        prodDetailsActivity.putExtra(PRODUCT_ITEM_KEY, productItem)
        prodDetailsActivity.putExtra(PRODUCT_LIST_KEY, productListResponse)
        startActivity(prodDetailsActivity)
    }

    override fun layoutId(): Int {
        return R.layout.activity_product_list
    }
}