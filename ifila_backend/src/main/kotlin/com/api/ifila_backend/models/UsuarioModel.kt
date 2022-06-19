package com.api.ifila_backend.models

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "TB_USUARIO")
class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:UUID? = null

    @Column(nullable = false, length = 70)
    lateinit var nome:String

    @Column(nullable = false)
    lateinit var dataDeNascimento:LocalDate

    @Column(nullable = false, unique = true, length = 100)
    lateinit var email:String

    @Column(nullable = false, length = 20)
    lateinit var numeroCelular:String

    @Column(nullable = false, unique = true, length = 20)
    lateinit var cpf:String

    @Column(nullable = false)
    var dataDeCriacao: ZonedDateTime = ZonedDateTime.now()

    @Column(nullable = false, length = 100)
    var senha: String = ""
        get() = field
        set(value) {
            val senhaEncoder = BCryptPasswordEncoder()
            field = senhaEncoder.encode(value)
        }

    fun checarSenha(senha: String): Boolean {
        return BCryptPasswordEncoder().matches(senha, this.senha)
    }
}