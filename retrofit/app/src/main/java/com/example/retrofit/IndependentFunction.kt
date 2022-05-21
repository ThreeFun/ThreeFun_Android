package com.example.retrofit

class IndependentFunction {
    fun isValidEmail(email: String?): Boolean {
        if (email == null) return false
        val regex = """[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,64}""".toRegex()
        return regex.matches(email)
    }
}