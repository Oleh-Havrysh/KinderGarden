package ua.nure.havrysh.kindergarten.model

data class LoginRequest(var email: String, var password: String)

data class LoginResponse(var accessToken: String)