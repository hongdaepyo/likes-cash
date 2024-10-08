package com.dphong.likescash.domain.member

import com.dphong.likescash.domain.posts.Likes
import com.dphong.likescash.domain.posts.Post
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Table(name = "Users")
@Entity
@DiscriminatorValue("USER")
class User(
    id: Long? = null,
    username: String,
    password: String,
    name: String?
) : Member(id, username, password, name, MemberRole.USER) {

    fun likes(post: Post): Likes {
        return Likes(memberId = this.id!!, postId = post.id!!)
    }
}
