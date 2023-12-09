package com.axel.springsec.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.*


@Entity
@Table(name = "users")
class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
      val id:Int?,
    @Column(unique = true, nullable = false)
      val username:String,
     @ManyToOne
     val role:RoleEntity

){
    companion object{
        fun createNew(username: String,role:RoleEntity):UserEntity{
            val newUser = UserEntity(null, username = username, role = role)
            return newUser
        }
    }
}