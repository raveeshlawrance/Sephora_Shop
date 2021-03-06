package com.sephora.shop.model.productlist

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductListResponse(

	@field:SerializedName("data")
	val data: List<ProductItem?>? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("included")
	val included: List<ProductItem?>? = null
) : Parcelable

@Parcelize
data class Relationships(

	@field:SerializedName("product")
	val product: Product? = null,

	@field:SerializedName("option-type")
	val optionType: OptionType? = null,

	@field:SerializedName("option-values")
	val optionValues: OptionValues? = null
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable

@Parcelize
data class ProductItem(

	@field:SerializedName("relationships")
	val relationships: Relationships? = null,

	@field:SerializedName("attributes")
	val attributes: Attributes? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable

@Parcelize
data class Meta(

	@field:SerializedName("per-page")
	val perPage: Int? = null,

	@field:SerializedName("total-items")
	val totalItems: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("current-page")
	val currentPage: Int? = null,

	@field:SerializedName("total-pages")
	val totalPages: Int? = null
) : Parcelable

@Parcelize
data class Categories(
	val any: String? = null
) : Parcelable

@Parcelize
data class OptionValues(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
) : Parcelable

@Parcelize
data class OptionTypes(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
) : Parcelable

@Parcelize
data class Attributes(

	@field:SerializedName("sold-out")
	val soldOut: Boolean? = null,

	@field:SerializedName("available")
	val available: Boolean? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("benefits")
	val benefits: String? = null,

	@field:SerializedName("sale-text")
	val saleText: String? = null,

	@field:SerializedName("original-price")
	val originalPrice: Int? = null,

	@field:SerializedName("cart-image-urls")
	val cartImageUrls: List<String?>? = null,

	@field:SerializedName("inventory")
	val inventory: Int? = null,

	@field:SerializedName("sephora-id")
	val sephoraId: String? = null,

	@field:SerializedName("new-arrival")
	val newArrival: Boolean? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("image-urls")
	val imageUrls: List<String?>? = null,

	@field:SerializedName("available-platforms")
	val availablePlatforms: List<String?>? = null,

	@field:SerializedName("product-name")
	val productName: String? = null,

	@field:SerializedName("closeup-image-urls")
	val closeupImageUrls: List<String?>? = null,

	@field:SerializedName("under-sale")
	val underSale: Boolean? = null,

	@field:SerializedName("sticker")
	val sticker: Boolean? = null,

	@field:SerializedName("upc")
	val upc: String? = null,

	@field:SerializedName("icon-url")
	val iconUrl: String? = null,

	@field:SerializedName("featured-image-urls")
	val featuredImageUrls: List<String?>? = null,

	@field:SerializedName("brand-slug-url")
	val brandSlugUrl: String? = null,

	@field:SerializedName("labels")
	val labels: List<String?>? = null,

	@field:SerializedName("zoom-image-urls")
	val zoomImageUrls: List<String?>? = null,

	@field:SerializedName("icon-image-urls")
	val iconImageUrls: List<String?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: Float? = null,

	@field:SerializedName("brand-name")
	val brandName: String? = null,

	@field:SerializedName("default-image-urls")
	val defaultImageUrls: List<String?>? = null,

	@field:SerializedName("slug-url")
	val slugUrl: String? = null,

	@field:SerializedName("option-type-id")
	val optionTypeId: Int? = null,

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("mobile-app-banner-image")
	val mobileAppBannerImage: String? = null,

	@field:SerializedName("brand-limit")
	val brandLimit: String? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable

@Parcelize
data class Brand(

	@field:SerializedName("data")
	val data: Data? = null
) : Parcelable

@Parcelize
data class OptionType(
	val any: String? = null
) : Parcelable

@Parcelize
data class Seo(

	@field:SerializedName("meta-description")
	val metaDescription: String? = null,

	@field:SerializedName("page-title")
	val pageTitle: String? = null
) : Parcelable

@Parcelize
data class Variants(
	val any: String? = null
) : Parcelable

@Parcelize
data class FeaturedReview(
	val any: String? = null
) : Parcelable

@Parcelize
data class Product(

	@field:SerializedName("data")
	val data: Data? = null
) : Parcelable

@Parcelize
data class Links(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("last")
	val last: String? = null,

	@field:SerializedName("self")
	val self: String? = null
) : Parcelable

@Parcelize
data class FeaturedAd(

	@field:SerializedName("data")
	val data: String? = null
) : Parcelable

@Parcelize
data class FeaturedVariant(

	@field:SerializedName("data")
	val data: Data? = null
) : Parcelable
