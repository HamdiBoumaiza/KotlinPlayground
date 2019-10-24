package com.hamdi.kotlinplayground

import java.io.Serializable

data class User(val name: String = "", val age: Int = 0)

data class UserWithoutInit(val name: String, val age: Int)

data class UserExcludeVariables(val name: String) {
    var age: Int = 0
}

data class UserModel(val id: String, val name: String, val email: String, val image: String) :
    Serializable {
    constructor() : this("", "", "", "")
}


