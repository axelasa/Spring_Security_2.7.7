package com.axel.springsec.entity

import com.axel.springsec.model.RoleModel
import javax.persistence.*

@Entity
@Table(name = "role")
class RoleEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Int?,
    @Column(unique = true)
    val name:String
){
    companion object{
        fun createRole(role:RoleModel):RoleEntity{
            val newRole = RoleEntity(null, name = role.name)
            return newRole
        }
    }
}