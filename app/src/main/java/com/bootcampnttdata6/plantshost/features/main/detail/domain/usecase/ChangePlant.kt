package com.bootcampnttdata6.plantshost.features.main.detail.domain.usecase

import com.bootcampnttdata6.plantshost.features.main.detail.domain.repository.PlantRepository
import javax.inject.Inject

class ChangePlant @Inject constructor(
    private val repository: PlantRepository
) {
    suspend operator fun invoke(id: Int,isFavorite: Boolean) {
        repository.changeFav(id,isFavorite)
    }
}