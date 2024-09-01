package br.com.fernandorubini.teste_fernando_rubini.base.network.domain

interface Mapper<in E, T> {
    fun mapFrom(from: E): T
}