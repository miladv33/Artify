package com.example.artify.domain.usecase.metobject

import com.example.artify.data.repository.Repository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class MetObjectUseCaseImpl @Inject constructor(
    private val metObjectDataRepository: Repository.MetObjectDataRepository
) : MetObjectUseCase {
    override fun getMetObjectData(metObjectId: Int) = flow {
        emit(metObjectDataRepository.getMetObjectData(metObjectId))
    }
}