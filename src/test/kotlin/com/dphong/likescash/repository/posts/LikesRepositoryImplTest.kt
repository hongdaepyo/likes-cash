package com.dphong.likescash.repository.posts

import com.dphong.likescash.RepositoryTest
import com.dphong.likescash.domain.posts.Likes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LikesRepositoryImplTest(
    private val likesJpaRepository: LikesJpaRepository,
) : RepositoryTest() {

    @Test
    fun `회원 아이디와 게시물 아이디로 좋아요 정보를 찾는다`() {
        // given
        val repository = LikesRepositoryImpl(likesJpaRepository)
        val likes = repository.save(Likes(memberId = 1, postId = 1))

        // when
        val foundLikes = repository.findByMemberIdAndPostId(likes.memberId, likes.postId)

        // then
        assertThat(likes).isEqualTo(foundLikes)
    }
}
