package com.example.testappdevandroid.Models

import com.google.gson.annotations.SerializedName

data class ReviewsModels(
    @SerializedName("id"            ) var id           : Int?               = null,
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<ResultsReviews> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null
)
data class AuthorDetails (

    @SerializedName("name"        ) var name       : String? = null,
    @SerializedName("username"    ) var username   : String? = null,
    @SerializedName("avatar_path" ) var avatarPath : String? = null,
    @SerializedName("rating"      ) var rating     : Int?    = null

)
data class ResultsReviews (

    @SerializedName("author"         ) var author        : String?        = null,
    @SerializedName("author_details" ) var authorDetails : AuthorDetails? = AuthorDetails(),
    @SerializedName("content"        ) var content       : String?        = null,
    @SerializedName("created_at"     ) var createdAt     : String?        = null,
    @SerializedName("id"             ) var id            : String?        = null,
    @SerializedName("updated_at"     ) var updatedAt     : String?        = null,
    @SerializedName("url"            ) var url           : String?        = null

)
