package com.bootcampnttdata6.plantshost.features.main.detail.domain.model

data class Plant (val name:String,
                  val price:String,
                  val picture: String,
                  val description: String,
                  val status: String,
                  var isfavorite: Boolean)
