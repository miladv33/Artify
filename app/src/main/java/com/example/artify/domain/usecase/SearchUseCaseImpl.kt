package com.example.artify.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.artify.data.paging.SearchPagingSource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
@Module
@InstallIn(ViewModelComponent::class)
class SearchUseCaseImpl @Inject constructor(
    private val pagingSource: SearchPagingSource,
    private val pagingConfig: PagingConfig
) :SearchUserCase{
    override fun search(query: String): Flow<PagingData<Int>> {
        pagingSource.setQuery(query)
         return Pager(
            config = pagingConfig,
            pagingSourceFactory = { pagingSource }
        ).flow
    }

}