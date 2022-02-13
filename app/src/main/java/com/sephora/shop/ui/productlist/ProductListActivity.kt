package com.sephora.shop.ui.productlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.sephora.shop.R
import com.sephora.shop.model.ProductListResponse
import com.sephora.shop.ui.adapter.ProductListAdapter
import com.sephora.shop.util.loadFromAsset

class ProductListActivity : AppCompatActivity() {
    var gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        var productList = loadFromAsset(applicationContext,"product_list_response.json")
        var productListResponse : ProductListResponse = gson.fromJson(productList, ProductListResponse::class.java)
        var rvProductList = findViewById<RecyclerView>(R.id.rv_product_list)
        rvProductList.layoutManager =
            GridLayoutManager(applicationContext, 2)
        rvProductList.adapter = ProductListAdapter(applicationContext, productListResponse)
    }
}