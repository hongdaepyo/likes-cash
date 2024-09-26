package com.dphong.likescash.api.posts.service

import com.dphong.likescash.api.posts.model.ReactionPostStatus
import com.dphong.likescash.fixtures.MemberFixture
import com.dphong.likescash.fixtures.PostFixture
import com.dphong.likescash.mock.TestContainer
import com.dphong.likescash.repository.member.MemberRepository
import com.dphong.likescash.repository.posts.PostRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ReactionPostServiceTest {

    private lateinit var testContainer: TestContainer
    private lateinit var service: ReactionPostService
    private lateinit var memberRepository: MemberRepository
    private lateinit var postRepository: PostRepository

    @BeforeEach
    fun setUp() {
        testContainer = TestContainer().build()
        memberRepository = testContainer.memberRepository
        postRepository = testContainer.postRepository
        service = testContainer.reactionPostService
    }

    @Test
    fun `게시물에 좋아요를 추가한다`() {
        // given
        val member = memberRepository.save(MemberFixture.user())
        val post = postRepository.save(PostFixture.create(member = member))

        // when
        val result = service.likes(member.id!!, post.id!!)

        // then
        assertThat(result.status).isEqualTo(ReactionPostStatus.SUCCESS)
    }

    @Test
    fun `회원 아이디가 유효하지 않으면 MEMBER_NOT_FOUND 상태를 응답한다`() {
        // given
        val member = memberRepository.save(MemberFixture.user())
        val post = postRepository.save(PostFixture.create(member = member))
        val invalidMemberId = -1L

        // when
        val result = service.likes(invalidMemberId, post.id!!)

        // then
        assertThat(result.status).isEqualTo(ReactionPostStatus.MEMBER_NOT_FOUND)
    }

    @Test
    fun `게시물 아이디가 유효하지 않으면 POST_NOT_FOUND 상태를 응답한다`() {
        // given
        val member = memberRepository.save(MemberFixture.user())
        val post = postRepository.save(PostFixture.create(member = member))
        val invalidPostId = -1L

        // when
        val result = service.likes(member.id!!, invalidPostId)

        // then
        assertThat(result.status).isEqualTo(ReactionPostStatus.POST_NOT_FOUND)
    }

    @Test
    fun `이미 좋아요한 게시물에 좋아요를 누르면 ALREADY_LIKED를 응답한다`() {
        // given
        val member = memberRepository.save(MemberFixture.user())
        val post = postRepository.save(PostFixture.create(member = member))

        // when
        service.likes(member.id!!, post.id!!)
        val result = service.likes(member.id!!, post.id!!)

        // then
        assertThat(result.status).isEqualTo(ReactionPostStatus.ALREADY_LIKED)
    }

    @Test
    fun `좋아요를 취소한다`() {
        val member = memberRepository.save(MemberFixture.user())
        val post = postRepository.save(PostFixture.create(member = member))
        service.likes(member.id!!, post.id!!)
        val before = post.reaction.likesCount

        val result = service.cancelLikes(member.id!!, post.id!!)
        assertThat(result.status).isEqualTo(ReactionPostStatus.SUCCESS)
        assertThat(result.data!!.likesCount).isEqualTo(before - 1)
    }

    @Test
    fun `좋아요 하지 않은 게시물의 좋아요를 취소하면 LIKES_NOT_FOUND를 응답한다`() {
        val member = memberRepository.save(MemberFixture.user())
        val post = postRepository.save(PostFixture.create(member = member))
        val before = post.reaction.likesCount

        val result = service.cancelLikes(member.id!!, post.id!!)
        assertThat(result.status).isEqualTo(ReactionPostStatus.LIKES_NOT_FOUND)
        assertThat(result.data!!.likesCount).isEqualTo(before)
    }
}
