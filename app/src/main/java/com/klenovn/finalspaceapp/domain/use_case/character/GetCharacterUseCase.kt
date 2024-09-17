package com.klenovn.finalspaceapp.domain.use_case.character

import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.domain.repository.CharacterRepository
import com.klenovn.finalspaceapp.util.ResourceState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Flow<ResourceState<Character>> {
        return repository.getCharacter(id)
    }
}
